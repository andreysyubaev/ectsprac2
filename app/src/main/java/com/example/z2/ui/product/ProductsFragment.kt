package com.example.z2.ui.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.z2.databinding.FragmentProductsBinding
import com.example.z2.ui.adapters.ProductAdapter
import com.example.z2.ui.models.Product

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding

    private lateinit var products: List<Product>
    private lateinit var adapter: ProductAdapter
    private lateinit var context: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        products = listOf(
            Product(1, "Рыжик"),
            Product(2, "Барсик"),
            Product(3, "Мурзик"),
            Product(4, "Васька"),
            Product(5, "Кот"),
            Product(6, "Кот"),
            Product(7, "Кот"),
            Product(8, "Кот"),
            Product(9, "Кот"),
            Product(10, "Пушок"),
        )

        context = this.requireContext()
        adapter = ProductAdapter.create()

        binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.rvProducts.layoutManager = LinearLayoutManager(context)
        adapter.refreshProducts(products)
        binding.rvProducts.adapter = adapter

        val dashboardViewModel =
            ViewModelProvider(this).get(ProdcutsViewModel::class.java)

        return binding.root
    }


}