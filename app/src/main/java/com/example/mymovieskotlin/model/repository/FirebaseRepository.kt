package com.example.mymovieskotlin.model.repository

import com.example.mymovieskotlin.data.User

interface FirebaseRepository {
    fun updateDB(uid: String,firebaseUser: User)
}