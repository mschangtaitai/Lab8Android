package com.example.laboratorio8.Product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio8.*
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter: ListAdapter<Product, ProductAdapter.ProductHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.name == newItem.name && oldItem.cant == newItem.cant
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val currentProduct: Product = getItem(position)

        holder.textViewName.text = currentProduct.name
        holder.textViewCant.text = currentProduct.cant.toString()

        holder.addButton.setOnClickListener {
            currentProduct.cant = currentProduct.cant + 1
            holder.textViewCant.text = currentProduct.cant.toString()
        }

        holder.subButton.setOnClickListener {
            if(currentProduct.cant > 0) {
                currentProduct.cant = currentProduct.cant - 1
                holder.textViewCant.text = currentProduct.cant.toString()
            }
        }
    }

    fun getProductAt(position: Int): Product{
        return getItem(position)
    }

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewName: TextView = itemView.item_tittle
        var textViewCant: TextView = itemView.cant_item

        internal var addButton: Button = itemView.findViewById(R.id.addButton)
        internal var subButton: Button = itemView.findViewById(R.id.subButton)
    }

    interface OnItemClickListener{
        fun onItemClick(product: Product)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

}