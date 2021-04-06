package kz.edu.nu.nurbakarinaelzhan.seniorproject2.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.MainApplication
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.db.getDatabase
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.repository.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainApplication {
        return app as MainApplication
    }

    @Singleton
    @Provides
    fun provideString(): String {
        return "RANDOM STRING"
    }


    @Singleton
    @Provides
    fun provideRepository(@ApplicationContext app: Context): AppRepository {
        return AppRepository(getDatabase(app))
    }
}