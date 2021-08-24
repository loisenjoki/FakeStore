package com.example.fakestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        var bundle: Bundle? = intent.extras
        var titles = bundle!!.getString("title") // 1
        var prices= bundle!!.getDouble("price") // 1
        var desc= bundle!!.getString("description") // 1
        var img= bundle!!.getString("image") // 1
        supportActionBar?.apply {
            if (titles != null) {
                title = titles.take(10)+ "..."
            }

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }




        tvprice.setText("Ksh"+prices.toString())
        tvtitle.setText(titles)
        tvdescription.setText(desc)
        Glide.with(applicationContext)
                .load(img)
                .into(imagedetails)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_details, menu)
        return true
    }

    // this event will enable the back
    // function to the button on press
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}