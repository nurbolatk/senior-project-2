package kz.edu.nu.nurbakarinaelzhan.seniorproject2.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: DBUser): Long

    @Insert
    fun insertAll(vararg users: DBUser)

    @Query("DELETE FROM users")
    fun delete()

    @Query("SELECT * FROM users")
    fun getAll(): List<DBUser>

    @Query("SELECT * FROM users LIMIT 1")
    fun getCurrentUser(): LiveData<DBUser>
}

@Database(entities = [DBUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}