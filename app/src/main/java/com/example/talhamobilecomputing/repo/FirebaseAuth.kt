package com.example.talhamobilecomputing.repo

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthRepo {

    fun login(
        firebaseAuth: FirebaseAuth,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
    ) {

        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(it.exception)
                }
            }
    }

    fun logout(
        firebaseAuth: FirebaseAuth
    ){
        firebaseAuth.signOut()
    }

    fun signUp(
        firebaseAuth: FirebaseAuth,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
    ) {

        firebaseAuth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(it.exception)
                }
            }
    }
}