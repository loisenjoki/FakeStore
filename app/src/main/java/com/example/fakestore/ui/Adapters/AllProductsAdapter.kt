package com.example.fakestore.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.HomeActivity
import com.example.fakestore.R
import com.example.fakestore.data.model.AllProductsModel
import com.example.fakestore.utils.CartItem
import com.example.fakestore.utils.ShoppingCart
import com.example.fakestore.utils.callbacks.ProductsListener
import com.google.android.material.snackbar.Snackbar

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.all_products_row_items.view.*


class AllProductsAdapter(private val listenadfer: ProductsListener,private val products: ArrayList<AllProductsModel>
) : RecyclerView.Adapter<AllProductsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(allProductsModel: AllProductsModel) {
            itemView.apply {
                val itemtitle = itemView.findViewById<TextView>(R.id.itemtitle)
                val itemprice = itemView.findViewById<TextView>(R.id.itemprice)
                val imageitem = itemView.findViewById<ImageView>(R.id.imageitem)
                itemtitle.text = allProductsModel.title
                itemprice.text = allProductsModel.price.toString()
                Glide.with(imageitem.context)
                        .load(allProductsModel.image)
                        .into(imageitem)

                Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {

                    itemView.btncardAdd.setOnClickListener { view ->

                        val item = CartItem(allProductsModel)

                        ShoppingCart.addItem(item)
                        //notify users
                        Toast.makeText(
                            itemView.context as HomeActivity,
                            "${allProductsModel.title} added to your cart",
                            Toast.LENGTH_LONG
                        ).show()

                        it.onNext(ShoppingCart.getCart())

                    }
                }).subscribe { cart ->
                    var quantity = 0

                    cart.forEach { cartItem ->
                        quantity += cartItem.quantity
                    }

                 //  Toast.makeText (itemView.context as HomeActivity, quantity.toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
                }
            }
            }
        }


override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.all_products_row_items, parent, false))

    override fun getItemCount(): Int = products.size

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



