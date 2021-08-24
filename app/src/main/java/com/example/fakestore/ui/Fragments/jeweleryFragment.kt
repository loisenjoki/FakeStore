package com.example.fakestore.ui.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.ProductDetailActivity
import com.example.fakestore.R
import com.example.fakestore.data.api.ApiHelper
import com.example.fakestore.data.api.RetrofitBuilder
import com.example.fakestore.data.model.AllProductsModel
import com.example.fakestore.ui.Adapters.ProductsByCatAdapter
import com.example.fakestore.ui.Viewmodel.AllProductsViewModel
import com.example.fakestore.ui.Viewmodel.ViewModelFactory
import com.example.fakestore.utils.Status
import com.example.fakestore.utils.callbacks.ProductsListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [jeweleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class jeweleryFragment : Fragment(), ProductsListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var viewModel: AllProductsViewModel
    private lateinit var adapter: ProductsByCatAdapter
    private lateinit var recyclerview: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvoldprice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jewelery, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment jeweleryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                jeweleryFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerview = view.findViewById(R.id.recyclerviewByCategory)
        progressBar = view.findViewById(R.id.progressBar1)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(AllProductsViewModel::class.java)
    }


    private fun setupUI() {
        recyclerview.layoutManager = GridLayoutManager(activity, 2)

        adapter = ProductsByCatAdapter(this,arrayListOf())

        recyclerview.setHorizontalScrollBarEnabled(false);

        recyclerview.getLayoutManager()?.setMeasurementCacheEnabled(false);


        recyclerview.adapter = adapter
/*        btnbuy.setOnClickListener{
            Toast.makeText(activity, "Item Added to card", Toast.LENGTH_SHORT).show()

        }*/

    }

    private fun setupObservers() {
        viewModel.getjewelery().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerview.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { products -> retrieveList(products) }!!
                        Log.e(">>>>>MKK", resource.data.toString())

                    }
                    Status.ERROR -> {
                        recyclerview.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                        it.message?.let { it1 -> Log.e(">>>>>>>>>>", it1) }
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerview.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(products: List<AllProductsModel>) {
        adapter.apply {
            addproducts(products)
            notifyDataSetChanged()
        }
    }

    override fun onCellClickListener(allProductsModel: AllProductsModel) {
        val intent = Intent(activity, ProductDetailActivity::class.java).apply {
            putExtra("image", allProductsModel.image)
            putExtra("title", allProductsModel.title)
            putExtra("price", allProductsModel.price)
            putExtra("description", allProductsModel.description)
        }
        startActivity(intent)

    }
}