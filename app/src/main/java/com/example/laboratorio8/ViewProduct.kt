package com.example.laboratorio8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.laboratorio8.Product.ProductViewModel
import kotlinx.android.synthetic.main.activity_view_product.*

class ViewProduct : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

        text_view_product_name.text = intent.getStringExtra("savedProductName")
        text_view_product_cant.text = intent.getStringExtra("savedProductCant").toString()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}