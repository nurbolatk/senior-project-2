package kz.edu.nu.nurbakarinaelzhan.seniorproject2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.domain.User

@Entity(tableName = "users")
data class DBUser(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val gender: String,
    val age: Int
){
    fun asDomainModel() = User(
            id,
            name,
            email, gender, age
        )
}

@Entity(tableName = "predictions")
data class DBPrediction(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "created_at") var createdAt: Long? = null,
    val value: Int,
    val percents: Double
)
