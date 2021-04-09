package kz.edu.nu.nurbakarinaelzhan.seniorproject2.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.MainApplication
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.db.AppDatabase
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.ApiService
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.createRetrofit
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
    fun provideRepository(
        service: ApiService,
        database: AppDatabase,
        application: MainApplication
    ): AppRepository {
        return AppRepository(database, service, application)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(
                app.applicationContext,
                AppDatabase::class.java,
                "app_db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideService(): ApiService {
        val backend : ApiService by lazy { createRetrofit().create(ApiService::class.java) }
        return backend
    }
}