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
    val covid_infected: SensorSymptom? = null,
    val fatigue: SensorSymptom? = null,
    val fever: SensorSymptom? = null,
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
) {
    fun toDatabaseModel(): DBPrediction = DBPrediction(
        covid_value = covid_infected?.value,
        covid_percents = covid_infected?.percents,
        fever_value = fever?.value,
        fever_percents = fever?.percents,
        fatigue_value = fatigue?.value,
        fatigue_percents = fatigue?.percents,
        pneumonia = pneumonia,
        difficult_breathing = difficult_breathing,
        submitted = submitted,
        sputum = sputum,
        muscle_pain = muscle_pain,
        sore_throat = sore_throat,
        cold = cold,
        sneeze = sneeze,
        reflux = reflux,
        diarrhea = diarrhea,
        runny_nose = runny_nose,
        chest_pain = chest_pain,
        cough = cough,
        joint_pain = joint_pain,
        flu = flu,
        headache = headache,
        vomiting = vomiting,
        loss_appetite = loss_appetite,
        chills = chills,
        nausea = nausea,
        physical_discomfort = physical_discomfort,
        abdominal_pain = abdominal_pain,
        _userID = _userID,
        pulseoximeter = pulseoximeter,
        thermometer = thermometer,
        spirometer = spirometer
    )
}

data class SensorSymptom(
    val value: Int? = null,
    val percents: Double? = null
)
data class PulseoximeterSensor(
    val value: Int? = null,
    val fatigue: SensorSymptom? = null
)

data class ThermometerSensor(
    val value: Double? = null,
    val fever: SensorSymptom? = null
)

data class SpirometerSensor(
    val value: Double? = null,
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
) {
    fun getSymptoms(): Map<String, Int> {
        val map = mutableMapOf<String, Int>()
        map["sputum"] = sputum ?: 0
        map["muscle_pain"] = muscle_pain ?: 0
        map["sore_throat"] = sore_throat ?: 0
        map["pneumonia"] = pneumonia ?: 0
        map["cold"] = cold ?: 0
        map["fever"] = fever ?: 0
        map["sneeze"] = sneeze ?: 0
        map["reflux"] = reflux ?: 0
        map["diarrhea"] = diarrhea ?: 0
        map["runny_nose"] = runny_nose ?: 0
        map["difficult_breathing"] = difficult_breathing ?: 0
        map["chest_pain"] = chest_pain ?: 0
        map["cough"] = cough ?: 0
        map["joint_pain"] = joint_pain ?: 0
        map["fatigue"] = fatigue ?: 0
        map["flu"] = flu ?: 0
        map["headache"] = headache ?: 0
        map["vomiting"] = vomiting ?: 0
        map["loss_appetite"] = loss_appetite ?: 0
        map["chills"] = chills ?: 0
        map["nausea"] = nausea ?: 0
        map["physical_discomfort"] = physical_discomfort ?: 0
        map["abdominal_pain"] = abdominal_pain ?: 0
        return map
    }
}
data class SensorsStatus(
    val pulseoximeter: PulseoximeterSensor? = null,
    val thermometer: ThermometerSensor? = null,
    val spirometer: SpirometerSensor? = null,
)

data class PredictionStatus(
    val sensors: SensorsStatus? = null,
    val survey: SurveyStatus? = null
)


enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<out T>(val status: Status,val data: T?,val message: String?) {

    companion object{
        fun<T> success(data:T):Resource<T> = Resource(status= Status.SUCCESS,data = data,message = null)
        fun<T> error(data:T?,message: String?):Resource<T> = Resource(status= Status.ERROR,data = data,message = message)
        fun<T> loading(data:T?):Resource<T> = Resource(status= Status.LOADING,data = data,message = null)
    }


}