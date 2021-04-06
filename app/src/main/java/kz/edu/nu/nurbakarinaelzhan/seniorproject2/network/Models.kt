package kz.edu.nu.nurbakarinaelzhan.seniorproject2.network

import com.squareup.moshi.Json
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