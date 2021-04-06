package kz.edu.nu.nurbakarinaelzhan.seniorproject2.repository

import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.db.AppDatabase
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.Api
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.NetworkUser
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.UserCredentials
import timber.log.Timber

class AppRepository(private val database: AppDatabase) {
    val currentUser = Transformations.map(database.userDao().getCurrentUser()) {
        it?.asDomainModel()
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
                val receivedUser = Api.backend.login(credentials)
                insertUser(receivedUser)
            } catch (e: Exception) {

            }

        }
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) {
            database.userDao().delete()
        }
    }
}