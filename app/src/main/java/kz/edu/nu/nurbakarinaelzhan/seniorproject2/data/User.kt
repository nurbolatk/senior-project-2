package kz.edu.nu.nurbakarinaelzhan.seniorproject2.data

data class User(
    val id: String,
    val name: String,
    val email: String,
    val gender: String,
    val age: Int
)

data class UserCredential(
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