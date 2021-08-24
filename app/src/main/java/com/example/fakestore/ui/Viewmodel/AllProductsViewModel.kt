package com.example.fakestore.ui.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fakestore.data.repository.MainRepository
import com.example.fakestore.utils.Resource
import kotlinx.coroutines.Dispatchers

class AllProductsViewModel (private val mainRepository: MainRepository): ViewModel() {
    fun getproducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getProducts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getByCategory() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = mainRepository.getByCategory()))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message?: "Error loading data"))
        }

    }

    fun getjewelery() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = mainRepository.getjewelery()))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message?: "Error loading data"))
        }

    }

    fun getWomenCloths() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = mainRepository.getWomenCloths()))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message?: "Error loading data"))
        }

    }
 fun getMenCloths() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = mainRepository.getMenCloths()))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message?: "Error loading data"))
        }

    }


}