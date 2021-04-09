package kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.ApiStatus
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



}