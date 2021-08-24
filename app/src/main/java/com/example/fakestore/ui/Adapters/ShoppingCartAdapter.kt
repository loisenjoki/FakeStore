package com.example.fakestore.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.R
import com.example.fakestore.R.layout.cart_list_item
import com.example.fakestore.utils.CartItem
import kotlinx.android.synthetic.main.cart_list_item.view.*

class ShoppingCartAdapter (var context: Context, var cartItems: List<CartItem>) :
        RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ShoppingCartAdapter.ViewHolder {

        // The layout design used for each list item
        val layout = LayoutInflater.from(context).inflate(cart_list_item, parent, false)

        return ViewHolder(layout)
    }

    // This returns the size of the list.
    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ShoppingCartAdapter.ViewHolder, position: Int) {

        //we simply call the `bindItem` function here
        viewHolder.bindItem(cartItems[position])
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(cartItem: CartItem) {

            // This displays the cart item information for each item
            //Picasso.get().load(cartItem.product.photos[0].filename).fit().into(itemView.product_image)
            Glide.with(itemView.product_name.context)
                    .load(cartItem.product.image)
                    .into(itemView.product_image)
            itemView.product_name.text = cartItem.product.title

            itemView.product_price.text = "$${cartItem.product.price}"

            itemView.product_quantity.text = cartItem.quantity.toString()

        }
    }

}