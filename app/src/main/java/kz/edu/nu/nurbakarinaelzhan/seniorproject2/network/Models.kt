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

data class SensorSymptom(
    val value: Int? = null,
    val percents: Int? = null
)
data class PulseoximeterSensor(
    val value: Int? = null,
    val fatigue: SensorSymptom? = null
)

data class ThermometerSensor(
    val value: Int? = null,
    val fever: SensorSymptom? = null
)

data class SpirometerSensor(
    val value: Int? = null,
    val pneumonia: Int? = null,
    val difficult_breathing: Int? = null
)

data class SurveyStatus(
    val covid_infected: SensorSymptom? = null,
    val submitted: Boolean? = null,
    val sputum: Int? = null,
    val muscle_pain: Int? = null,
    val sore_throat: Int? = null,
    val pneumonia: Int? = null,
    val cold: Int? = null,
    val fever: Int? = null,
    val sneeze: Int? = null,
    val reflux: Int? = null,
    val diarrhea: Int? = null,
    val runny_nose: Int? = null,
    val difficult_breathing: Int? = null,
    val chest_pain: Int? = null,
    val cough: Int? = null,
    val joint_pain: Int? = null,
    val fatigue: Int? = null,
    val flu: Int? = null,
    val headache: Int? = null,
    val vomiting: Int? = null,
    val loss_appetite: Int? = null,
    val chills: Int? = null,
    val nausea: Int? = null,
    val physical_discomfort: Int? = null,
    val abdominal_pain: Int? = null,
    val _id: String? = null,
    val _userID: String? = null,
)
data class SensorsStatus(
    val pulseoximeter: PulseoximeterSensor? = null,
    val thermometer: ThermometerSensor? = null,
    val spirometer: SpirometerSensor? = null,
)

data class PredictionStatus(
    val sensors: SensorsStatus? = null,
    val survey: SurveyStatus? = null
)