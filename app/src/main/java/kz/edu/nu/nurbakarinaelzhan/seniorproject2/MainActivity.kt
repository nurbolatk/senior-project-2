package kz.edu.nu.nurbakarinaelzhan.seniorproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AuthViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.main.MainNavController
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.main.MyApp
import timber.log.Timber
import timber.log.Timber.DebugTree


class MainActivity : ComponentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        setContent{
            MyApp {
                MainNavController(authViewModel)
            }
        }
    }
}

