package com.example.z2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.z2.databinding.ProductItemBinding
import com.example.z2.ui.models.Product

class ProductAdapter(): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var selectedPosition: Int = -1
    private var selectedProduct: ProductAdapter? = null
    private lateinit var items: List<Product>

    companion object Factory{
        fun create(): ProductAdapter{
            return ProductAdapter()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvProductName.text = item.name
        holder.itemView.isSelected = selectedPosition == holder.layoutPosition
    }

    override fun getItemCount(): Int{
        return items.size
    }

    fun getSelectedCourier(): ProductAdapter?{
        return selectedProduct
    }

    class ViewHolder(binding: ProductItemBinding) :RecyclerView.ViewHolder(binding.root){
        val tvProductName = binding.productName
    }

    fun refreshProducts(items: List<Product>)
    {
        this.items = items
    }
}