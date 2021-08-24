package com.example.fakestore.data.api

class ApiHelper (private val apiService: ApiService) {
    suspend fun getProducts() = apiService.getProducts()
    suspend fun getByCategory() = apiService.getByCategory()
    suspend fun getjewelery() = apiService.getjewelery()
    suspend fun getWomenCloths() = apiService.getWomenCloths()
    suspend fun getMenCloths() = apiService.getMenCloths()

}