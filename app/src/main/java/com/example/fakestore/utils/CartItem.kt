package com.example.fakestore.utils

import com.example.fakestore.data.model.AllProductsModel

data class CartItem (var product: AllProductsModel, var quantity: Int = 0)
