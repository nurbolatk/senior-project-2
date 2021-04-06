package kz.edu.nu.nurbakarinaelzhan.seniorproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.main.MainNavController
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.main.MyApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val testViewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        Timber.d("Aloha ${testViewModel.randomString}")
        setContent{
            MyApp {
                MainNavController()
            }
        }
    }
}

