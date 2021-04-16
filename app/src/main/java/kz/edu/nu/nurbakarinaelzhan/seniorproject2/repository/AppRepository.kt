package kz.edu.nu.nurbakarinaelzhan.seniorproject2.repository

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.MainActivity
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.MainApplication
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.R
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.db.AppDatabase
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.*
import timber.log.Timber
import javax.inject.Inject

class AppRepository
@Inject constructor(
    private val database: AppDatabase,
    private val service: ApiService,
    private val application: MainApplication
) {
    val currentUser = Transformations.map(database.userDao().getCurrentUser()) {
        it?.asDomainModel()
    }
    val predictions = Transformations.map(database.predictionsDao().getAll()) {
        it ?: null
    }

    suspend fun insertUser(user: NetworkUser) {
        withContext(Dispatchers.IO) {
            Timber.d("Start blocking")
            val id = database.userDao().insert(user.asDatabaseModel())
            Timber.d("New row $id")
            Timber.d("WOOOHOOO")
        }
    }

    suspend fun login(credentials: UserCredentials) {
        withContext(Dispatchers.IO) {
            try {
                val receivedUser = service.login(credentials)
                insertUser(receivedUser)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    suspend fun register(user: NewUser) {
        withContext(Dispatchers.IO) {
            try {
                val receivedUser = service.register(user)
                insertUser(receivedUser)
            } catch(e: Exception) {
                Timber.e(e)
            }
        }
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) {
            database.userDao().delete()
        }
    }

    val symptomsStatus = MutableLiveData<ApiStatus>(ApiStatus.IDLE)

    var moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    var mapJsonAdapter: JsonAdapter<Map<String, Int>> =
        moshi.adapter<Map<String, Int>>(Map::class.java)

    val symptomJsonAdapter = moshi.adapter(Symptoms::class.java)

    suspend fun sendSymptoms(symptomsMap: Map<String, Int>) {
        currentUser.value?.let { user ->
            val json = mapJsonAdapter.toJson(symptomsMap)
            withContext(Dispatchers.IO) {
                val symptoms = symptomJsonAdapter.fromJson(json)
                symptoms?.let {
                    val payload = SymptomsPayload(user.id, it)
                    try {
                        symptomsStatus.postValue(ApiStatus.LOADING)
                        service.sendSymptoms(payload)
                        symptomsStatus.postValue(ApiStatus.SUCCESS)
                    } catch (e: Exception) {
                        Timber.e(e)
                        symptomsStatus.postValue(ApiStatus.ERROR)
                    }
                }
            }
        }
    }
    private val CHANNEL_ID = "222"

    suspend fun fetchPrediction(id: String) {
        withContext(Dispatchers.IO) {
            try {
                val prediction = service.getPrediction(id)
                Timber.d("Prediction $prediction")
                if(prediction.value != 0) {
                    sendNotification("Prediction ready!", "You are ${prediction.value}")
                    database.predictionsDao().insert(prediction.toDatabaseModel())
                } else {
                    sendNotification("Prediction not ready yet", "Please, try again later")
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }


    private fun sendNotification(title: String, body: String) {
        val contentIntent = Intent(application, MainActivity::class.java)
        // TODO: Step 1.12 create PendingIntent
        val contentPendingIntent = PendingIntent.getActivity(
            application,
            222,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat
            .Builder(application.applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_bubble_chart_24)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)

        with(NotificationManagerCompat.from(application)) {
            // notificationId is a unique int for each notification that you must define
            notify(222, builder.build())
        }

    }

    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Prediction channel"
            val descriptionText = "Prediction channel"
            val importance = NotificationManager.IMPORTANCE_MAX
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(application.applicationContext, NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    val statusStatus = MutableLiveData(ApiStatus.IDLE)
    val predictionStatus = MutableLiveData<PredictionStatus>(null)
    
    suspend fun fetchStatus(id: String) {
        withContext(Dispatchers.IO) {
            try {
                Timber.d("aloha start status fetching")
                val gotStatus = service.getStatus(id)
                Timber.d("aloha end status fetching")
                Timber.d("aloha ${gotStatus}")
                predictionStatus.postValue(gotStatus)
            } catch(e: Exception) {
                Timber.e(e)
            }
        }
    }

}