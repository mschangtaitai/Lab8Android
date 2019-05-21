package com.example.laboratorio8

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio8.Product.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var productViewModel: ProductViewModel

    companion object {
        const val SAVED_PRODUCT_ID = "savedProductId"
        const val SAVED_PRODUCT_NAME = "savedProductName"
        const val SAVED_PRODUCT_CANT = "savedProductCant"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAddProduct.setOnClickListener {
            val intent = Intent(this, AddProduct::class.java)
            startActivity(intent)
        }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        val adapter = ProductAdapter()

        recycler_view.adapter = adapter


        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

        productViewModel.getAllProducts().observe(this, Observer<List<Product>> {
            adapter.submitList(it)
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                productViewModel.delete(adapter.getProductAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "Producto Eliminado...", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view)


        adapter.setOnItemClickListener(object : ProductAdapter.OnItemClickListener {

            override fun onItemClick(product: Product) {
                val intent = Intent(baseContext, ViewProduct::class.java)
                intent.putExtra(SAVED_PRODUCT_ID, product.id)
                intent.putExtra(SAVED_PRODUCT_NAME, product.name)
                intent.putExtra(SAVED_PRODUCT_CANT, product.cant)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_products -> {
                productViewModel.deleteAllProducts()
                Toast.makeText(this, "All Products Deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val newProduct = Product(SAVED_PRODUCT_NAME,0)
        productViewModel.insert(newProduct)
    }
}

