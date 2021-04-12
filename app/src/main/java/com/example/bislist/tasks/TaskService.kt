package com.example.bislist.tasks

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.bislist.MainActivity
import com.example.bislist.tasks.data.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class TaskService{

    private lateinit var auth: FirebaseAuth
    private val TAG:String = "bisList:TaskService"

    fun signIn(){

        auth = Firebase.auth
        signInAnon()
    }

    private fun signInAnon(){
        auth.signInAnonymously().addOnSuccessListener {
            Log.d(TAG,"Login success ${it.user.toString()}")
        }.addOnFailureListener {
            Log.e(TAG, "Login failed", it)
        }

    }

    fun upload(file:Uri){
        val ref = FirebaseStorage.getInstance().reference.child("list/${file.lastPathSegment}")
        var uploadTask = ref.putFile(file)

        uploadTask.addOnSuccessListener {
            Log.d(TAG, "Lagret fil ${it.toString()}")
        }.addOnFailureListener {
            Log.e(TAG, "Error i lagring", it)
        }
    }

    companion object {
        val instance = TaskService()
    }
}