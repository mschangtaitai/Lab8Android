package com.example.laboratorio8.Product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ProductViewModel (application: Application): AndroidViewModel(application){
    private var repository: ProductRepository =
        ProductRepository(application)
    private var allProducts: LiveData<List<Product>> = repository.getAllProducts()

    fun insert(product: Product){
        repository.insert(product)
    }
    fun update(product: Product){
        repository.update(product)
    }
    fun delete(product: Product){
        repository.delete(product)
    }
    fun deleteAllProducts(){
        repository.deleteAllContacts()
    }
    fun getAllProducts(): LiveData<List<Product>> {
        return allProducts
    }
}