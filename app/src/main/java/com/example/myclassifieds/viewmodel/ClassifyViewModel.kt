package com.example.myclassifieds.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myclassifieds.App
import com.example.myclassifieds.network.ApiService
import com.example.myclassifieds.util.Resource
import kotlinx.coroutines.Dispatchers

class ClassifyViewModel(val apiService: ApiService = App.api!!
): ViewModel(){
    fun getClassifieds() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getClassifieds()))
        } catch (exception: Exception) {
            Log.e("Error",exception.toString());
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
