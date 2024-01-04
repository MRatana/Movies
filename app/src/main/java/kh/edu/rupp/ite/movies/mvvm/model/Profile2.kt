package kh.edu.rupp.ite.movies.mvvm.model

class Profile2 {

    private lateinit var name: String
    private lateinit var gender: String
    private var age = 0
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var imgProfile: String

    fun setName(name: String) {
        this.name = name
    }
    fun getName(): String {
        return name
    }

    fun getGender(): String? {
        return gender
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getAge(): Int {
        return age
    }

    fun setAge(age: Int) {
        this.age = age
    }

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getImgProfile(): String {
        return imgProfile
    }

    fun setImgProfile(imgProfile: String) {
        this.imgProfile = imgProfile
    }

}