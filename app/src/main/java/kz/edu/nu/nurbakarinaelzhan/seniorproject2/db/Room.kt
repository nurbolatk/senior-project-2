package kz.edu.nu.nurbakarinaelzhan.seniorproject2.db

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

@Dao
interface PredictionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWithoutTimestamp(data: DBPrediction)

    fun insert(data: DBPrediction) {
        insertWithoutTimestamp(data.apply{
            createdAt = System.currentTimeMillis()
        })
    }

    @Query("select * from predictions")
    fun getAll(): LiveData<List<DBPrediction>>

    @Query("delete from predictions")
    fun deleteAll()

    @Query("select * from predictions where id = :id")
    fun get(id: Long): DBPrediction
}

@Database(entities = [DBUser::class, DBPrediction::class], version = 7)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun predictionsDao(): PredictionsDao
}

