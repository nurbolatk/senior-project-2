package kz.edu.nu.nurbakarinaelzhan.seniorproject2.network

import com.squareup.moshi.Json
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.db.DBPrediction
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.db.DBUser
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.domain.User

data class NetworkUser(
    @Json(name = "_id")
    val id: String,
    val name: String,
    val email: String,
    val gender: String,
    val age: Int
) {
    fun asDomainModel() = User(
        id, name, email, gender, age
    )
    fun asDatabaseModel() = DBUser(
        id, name, email, gender, age
    )
}

data class UserCredentials (
    val email: String,
    val password: String
)

data class NewUser(
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val age: Int
)

data class Symptoms(
    var sputum: Int? = null,
    var muscle_pain: Int? = null,
    var sore_throat: Int? = null,
    var pneumonia: Int? = null,
    var cold: Int? = null,
    var fever: Int? = null,
    var sneeze: Int? = null,
    var reflux: Int? = null,
    var diarrhea: Int? = null,
    var runny_nose: Int? = null,
    var difficult_breathing: Int? = null,
    var chest_pain: Int? = null,
    var cough: Int? = null,
    var joint_pain: Int? = null,
    var fatigue: Int? = null,
    var flu: Int? = null,
    var headache: Int? = null,
    var vomiting: Int? = null,
    var loss_appetite: Int? = null,
    var chills: Int? = null,
    var nausea: Int? = null,
    var physical_discomfort: Int? = null,
    var abdominal_pain: Int? = null,
) {
//    operator fun set(key: String, value: Int) {
//
//    }
}

data class SymptomsPayload(
    val id: String,
    val symptoms: Symptoms
)

data class PredictionGet(
    val value: Int
) {
    fun toDatabaseModel(): DBPrediction = DBPrediction(
        value = value
    )
}