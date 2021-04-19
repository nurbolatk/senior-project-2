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
    val covid_value: Int? = null,
    val covid_percents: Double? = null,
    val fatigue_value: Int? = null,
    val fatigue_percents: Double? = null,
    val fever_value: Int? = null,
    val fever_percents: Double? = null,
    val pneumonia: Int? = null,
    val difficult_breathing: Int? = null,
    val submitted: Boolean? = null,
    val sputum: Int? = null,
    val muscle_pain: Int? = null,
    val sore_throat: Int? = null,
    val cold: Int? = null,
    val sneeze: Int? = null,
    val reflux: Int? = null,
    val diarrhea: Int? = null,
    val runny_nose: Int? = null,
    val chest_pain: Int? = null,
    val cough: Int? = null,
    val joint_pain: Int? = null,
    val flu: Int? = null,
    val headache: Int? = null,
    val vomiting: Int? = null,
    val loss_appetite: Int? = null,
    val chills: Int? = null,
    val nausea: Int? = null,
    val physical_discomfort: Int? = null,
    val abdominal_pain: Int? = null,
    val _userID: String? = null,
    val pulseoximeter: Int? = null,
    val thermometer: Double? = null,
    val spirometer: Double? = null,
)
