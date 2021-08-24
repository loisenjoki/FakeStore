package com.example.fakestore.utils.callbacks

import com.example.fakestore.data.model.AllProductsModel

 interface ProductsListener {
    fun onCellClickListener(allProductsModel: AllProductsModel)

}