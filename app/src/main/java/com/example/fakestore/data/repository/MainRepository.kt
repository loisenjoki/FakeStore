package com.example.fakestore.data.repository

import com.example.fakestore.data.api.ApiHelper
import com.example.fakestore.data.api.ApiService

class MainRepository(private val apiHelper:ApiHelper) {
    suspend fun getProducts() = apiHelper.getProducts()
    suspend fun getByCategory() = apiHelper.getByCategory()
    suspend fun getjewelery() = apiHelper.getjewelery()
    suspend fun getWomenCloths() = apiHelper.getWomenCloths()
    suspend fun getMenCloths() = apiHelper.getMenCloths()


}