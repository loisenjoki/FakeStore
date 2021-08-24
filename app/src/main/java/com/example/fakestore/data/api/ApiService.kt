package com.example.fakestore.data.api

import com.example.fakestore.data.model.AllProductsModel
import com.example.fakestore.ui.Fragments.AllProducts
import retrofit2.http.GET

interface ApiService{
    @GET("products")
    suspend fun getProducts():  List<AllProductsModel>

    @GET("products/category/electronics")
    suspend fun getByCategory():List<AllProductsModel>

    @GET("products/category/jewelery")
    suspend fun getjewelery():List<AllProductsModel>

    @GET("products/category/women clothing")
    suspend fun getWomenCloths():List<AllProductsModel>

    @GET("products/category/men clothing")
    suspend fun getMenCloths():List<AllProductsModel>


}