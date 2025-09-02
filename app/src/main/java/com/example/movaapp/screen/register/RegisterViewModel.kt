package com.example.movaapp.screen.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): ViewModel() {
    val isSuccess = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()


    fun registerUser(email: String, password: String){

        viewModelScope.launch {
            try {
                val response = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
                if(!response.user?.email.isNullOrEmpty()){
                    isSuccess.value = true
                }
            }catch (e:Exception){
                isError.value = e.localizedMessage
            }
        }
    }
}