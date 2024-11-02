package com.example.simpleuserprofilefetcher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleuserprofilefetcher.model.User
import com.example.simpleuserprofilefetcher.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Loading state LiveData
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchUserProfile() {
        _loading.value = true  // Start loading
        viewModelScope.launch {
            try {
                val response = repository.getUserProfile()
                if (response.isSuccessful) {
                    _user.postValue(response.body())
                } else {
                    _error.postValue("Error loading user data")
                }
            } catch (e: Exception) {
                _error.postValue("Network error: ${e.message}")
            } finally {
                _loading.value = false  // Stop loading after response
            }
        }
    }
}
