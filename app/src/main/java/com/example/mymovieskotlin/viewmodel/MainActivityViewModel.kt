package com.example.mymovieskotlin.viewmodel

import com.example.mymovieskotlin.data.User
import com.example.mymovieskotlin.model.repository.FirebaseRepository
import com.example.mymovieskotlin.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {

    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateUserData(uid: String, firebaseUser: User) {
        mFirebaseRepository.updateDB(uid, firebaseUser)
    }
}