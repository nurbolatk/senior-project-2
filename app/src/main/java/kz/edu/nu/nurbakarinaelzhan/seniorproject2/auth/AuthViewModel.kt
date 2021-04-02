package kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.data.NewUser
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.data.User
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.data.UserCredential
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.Api
import timber.log.Timber

enum class ApiStatus {
    INIT,
    LOADING,
    ERROR,
    DONE
}

class AuthViewModel : ViewModel() {
    var currentUser by mutableStateOf<User?>(null)
    var status by mutableStateOf(ApiStatus.INIT)

    fun login(credentials: UserCredential) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                currentUser = Api.backend.login(credentials)
                Timber.d(currentUser?.email)
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
                currentUser = Api.backend.register(user)
                Timber.d(currentUser?.email)
                status = ApiStatus.DONE
            } catch(e: Exception) {
                Timber.e(e)
                status = ApiStatus.ERROR
            }
        }
    }
}