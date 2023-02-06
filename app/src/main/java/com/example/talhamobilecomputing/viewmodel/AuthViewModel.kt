package com.example.talhamobilecomputing.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.talhamobilecomputing.repo.FirebaseAuthRepo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {

    private val _userLoginStatus = MutableStateFlow<UserLoginStatus?>(null)
    private val _userSignUpStatus = MutableStateFlow<UserSignUpStatus?>(null)

    val userLoginStatus = _userLoginStatus.asStateFlow()
    val userSignUpStatus = _userSignUpStatus.asStateFlow()

    private val firebaseAuth = FirebaseAuth.getInstance()

//    init {
//        createNewUser(username = "talha123@gmail.com", password = "1234567")
//    }

    fun loginToApp(username: String, password: String){
        FirebaseAuthRepo.login(
            firebaseAuth, username, password,
            onSuccess = {
                 _userLoginStatus.value = UserLoginStatus.Successful
            },
            onFailure = {
                _userLoginStatus.value = UserLoginStatus.Failure(it)
            }
        )
    }

    fun logOut(){
        FirebaseAuthRepo.logout(firebaseAuth)
    }

    fun createNewUser(username: String, password: String){
        FirebaseAuthRepo.signUp(
            firebaseAuth, username, password,
            onSuccess = {
                _userSignUpStatus.value = UserSignUpStatus.Successful
            },
            onFailure = {
                _userSignUpStatus.value = UserSignUpStatus.Failure(it)
            }
        )
    }
}

sealed class  UserLoginStatus {
    object Successful: UserLoginStatus()
    class Failure(val exception: Exception?): UserLoginStatus()
}

sealed class  UserSignUpStatus {
    object Successful: UserSignUpStatus()
    class Failure(val exception: Exception?): UserSignUpStatus()
}