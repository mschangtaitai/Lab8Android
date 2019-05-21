package com.example.laboratorio8

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.laboratorio8.Product.ProductViewModel
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProduct : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    private var currentId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_product_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_product -> {
                saveProduct()
                true
            }
            R.id.close -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun saveProduct(){
        if (text_view_add_product.text.toString().isNotEmpty()){
            val data = Intent().apply {
                putExtra("savedProductId", currentId)
                putExtra("savedProductName", text_view_add_product.text.toString())

            }
            setResult(Activity.RESULT_OK, data)
            finish()
        } else {
            Toast.makeText(this, "Por favor ingrese el nombre del producto", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
