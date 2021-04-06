package kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.db.getDatabase
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.Api
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.NewUser
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.UserCredentials
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.repository.AppRepository
import timber.log.Timber

enum class ApiStatus {
    INIT,
    LOADING,
    ERROR,
    DONE
}


class AuthViewModel constructor(
    private val app: Application
) : AndroidViewModel(app) {

    private val repository = AppRepository(getDatabase(getApplication()))

    var currentUser = repository.currentUser
    var status by mutableStateOf(ApiStatus.INIT)

    private var id: Int = (100..999).random()
    init {
        Timber.d("Ma Id is $id")
    }

    fun login(credentials: UserCredentials) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val receivedUser = Api.backend.login(credentials)
                repository.insertUser(receivedUser)
                status = ApiStatus.DONE
            } catch(e: Exception) {
                Timber.e(e)
                status = ApiStatus.ERROR
            }
        }
    }

    fun register(user: NewUser) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val receivedUser = Api.backend.register(user)
                repository.insertUser(receivedUser)
                status = ApiStatus.DONE
            } catch(e: Exception) {
                Timber.e(e)
                status = ApiStatus.ERROR
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                repository.logout()
            } catch (e: Exception) {

            }
        }
    }
}