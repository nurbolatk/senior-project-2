package kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.ApiService
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.ApiStatus
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.NewUser
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.UserCredentials
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.repository.AppRepository
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.roundToInt


@HiltViewModel
class AppViewModel
@Inject constructor(
    val randomString: String,
    private val repository: AppRepository,
    val service: ApiService
): ViewModel() {

    val currentUser = repository.currentUser
    val predictions = repository.predictions

    init {
        Timber.d("$randomString ${(100..999).random()}")
        repository.createNotificationChannel()
    }

    var status by mutableStateOf(ApiStatus.IDLE)

    fun login(credentials: UserCredentials) {
        viewModelScope.launch {
            repository.login(credentials)
        }
    }

    fun register(user: NewUser) {
        viewModelScope.launch {
            repository.register(user)
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                repository.logout()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    val symptomsStatus = repository.symptomsStatus

    fun sendSymptoms(symptomsMap: Map<String, Int>) {
        viewModelScope.launch {
            repository.sendSymptoms(symptomsMap)
        }
    }

    fun resetSymptomsStatus() {
        symptomsStatus.value = ApiStatus.IDLE
    }

    fun fetchPrediction() {
        currentUser.value?.let {
            viewModelScope.launch {
                repository.fetchPrediction(it.id)
            }
        }
    }

    val statusStatus = repository.statusStatus
    val predictionStatus = repository.predictionStatus
    fun fetchStatus() {
        currentUser.value?.let{
            viewModelScope.launch {
                repository.fetchStatus(it.id)
            }
        }
    }

    fun submitSpo2(spo2: String) {
        currentUser.value?.let {
            val intSpo2 = spo2.toInt()
            viewModelScope.launch {
                repository.submitSpo2(intSpo2, it.id)
            }
        }
    }
    fun submitTemperature(spo2: String) {
        currentUser.value?.let {
            val intSpo2 = spo2.toDouble()
            val data = (intSpo2*100).roundToInt()
            viewModelScope.launch {
                repository.submitTemperature(data, it.id)
            }
        }
    }
    fun submitSpirometer(spo2: String) {
        currentUser.value?.let {
            val intSpo2 = spo2.toDouble()
            val data = (intSpo2*100).roundToInt()
            viewModelScope.launch {
                repository.submitSpirometer(data, it.id)
            }
        }
    }

    val prediction = repository.prediction

    fun getPredictionById(id: Long) {
        viewModelScope.launch {
            repository.getPredictionById(id)
        }
    }

//    fun getUsers() = liveData(Dispatchers.IO) {
//        currentUser.value?.let {
//            emit(Resource.loading(data = null))
//            try {
//                emit(Resource.success(data=service.getStatus(it.id)))
//            }catch (exception: Exception){
//                emit(Resource.error(data=null,message = exception.message?:"Error occured"))
//            }
//        }
//    }


}