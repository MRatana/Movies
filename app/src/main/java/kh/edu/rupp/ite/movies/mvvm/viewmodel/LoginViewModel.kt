package kh.edu.rupp.ite.movies.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.movies.mvvm.model.Profile2

class LoginViewModel(
    private var user: Profile2? = Profile2(),
    private var isLoginSuccess: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
) : ViewModel() {
    fun getIsLoginSuccessful(): MutableLiveData<Boolean>? {
        return isLoginSuccess
    }

    fun setIsLoginSuccessful(isLoginSuccessful: Boolean) {
        isLoginSuccess!!.postValue(isLoginSuccessful)
    }

    fun login(userName: String, password: String) {
        if (userName == "Po" && password == "1234") {
            user!!.setName(userName)
            user!!.setPassword(password)
            setIsLoginSuccessful(true)
        } else {
            setIsLoginSuccessful(false)
        }
    }

}