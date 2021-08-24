package com.example.fakestore.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.R
import com.example.fakestore.data.model.AllProductsModel
import com.example.fakestore.utils.callbacks.ProductsListener

class ProductsByCatAdapter(
        private val listenadfer: ProductsListener,private val products: ArrayList<AllProductsModel>
) : RecyclerView.Adapter<ProductsByCatAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(allProductsModel: AllProductsModel) {
            itemView.apply {
                val itemtitle = itemView.findViewById<TextView>(R.id.titlecat)
                val itemprice = itemView.findViewById<TextView>(R.id.pricecat)
                val imageitem = itemView.findViewById<ImageView>(R.id.imagebyCat)
                itemtitle.text = allProductsModel.title
                itemprice.text = "Ksh"+allProductsModel.price.toString()
                Glide.with(imageitem.context)
                        .load(allProductsModel.image)
                        .into(imageitem)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
            ProductsByCatAdapter.DataViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.pro_cat_row_item, parent, false))


    override fun getItemCount(): Int  = products.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(products[position])
        holder.itemView.setOnClickListener {
            listenadfer.onCellClickListener(products[position])
        }
    }
             fun addproducts(prods: List<AllProductsModel>) {
            this.products.apply {
                clear()
                addAll(prods)
            }

}
}