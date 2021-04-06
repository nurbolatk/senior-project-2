package kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.Api
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.NewUser
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.UserCredentials
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.repository.AppRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AppViewModel
@Inject constructor(
    val randomString: String,
    private val repository: AppRepository
): ViewModel() {

    val currentUser = repository.currentUser

    init {
        Timber.d("$randomString ${(100..999).random()}")
    }

    var status by mutableStateOf(ApiStatus.INIT)

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