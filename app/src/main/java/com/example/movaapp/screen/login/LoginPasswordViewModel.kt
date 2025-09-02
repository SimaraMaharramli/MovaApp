package com.example.movaapp.screen.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class LoginPasswordViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
):ViewModel() {
    val isSuccess = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()


    fun loginUser(email: String, password: String){

        viewModelScope.launch {
            try {
                val response = firebaseAuth.signInWithEmailAndPassword(email,password).await()
                if(!response.user?.email.isNullOrEmpty()){
                    isSuccess.value = true
                }
            }catch (e:Exception){
                isError.value = e.localizedMessage
            }
            }
        }
}