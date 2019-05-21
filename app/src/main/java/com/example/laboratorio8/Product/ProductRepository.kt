package com.example.laboratorio8.Product

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ProductRepository (application: Application){

    private var productDao: ProductDao

    private var allProducts: LiveData<List<Product>>

    init {
        val database: ProductDatabase = ProductDatabase.getInstance(
            application.applicationContext
        )!!
        productDao = database.contactDao()
        allProducts = productDao.getAllProducts()
    }

    fun insert(contact: Product){
        val insertProductAsyncTask = InsertContactAsyncTask(productDao).execute(contact)
    }
    fun update(contact: Product){
        val updateProductAsyncTask = UpdateContactAsyncTask(productDao).execute(contact)
    }
    fun delete(contact: Product){
        val deleteProductAsyncTask = DeleteContactAsyncTask(productDao).execute(contact)
    }
    fun deleteAllContacts(){
        val deleteAllProductsAsyncTaks = DeleteAllContactsAsyncTask(
            productDao
        ).execute()
    }
    fun getAllProducts(): LiveData<List<Product>> {
        return allProducts
    }

    companion object {
        private class InsertContactAsyncTask(productDao: ProductDao) : AsyncTask<Product, Unit, Unit>(){
            val productDao = productDao

            override fun doInBackground(vararg p0: Product?) {
                productDao.insert(p0[0]!!)
            }
        }

        private class UpdateContactAsyncTask(productDao: ProductDao) : AsyncTask<Product, Unit, Unit>(){
            val productDao = productDao
            override fun doInBackground(vararg p0: Product?) {
                productDao.update(p0[0]!!)
            }
        }

        private class DeleteContactAsyncTask(productDao: ProductDao) : AsyncTask<Product, Unit, Unit>(){
            val productDao = productDao
            override fun doInBackground(vararg p0: Product?) {
                productDao.delete(p0[0]!!)
            }
        }

        private class  DeleteAllContactsAsyncTask(productDao: ProductDao) : AsyncTask<Unit, Unit, Unit>() {
            val productDao = productDao
            override fun doInBackground(vararg p0: Unit?) {
                productDao.deleteAllProducts()
            }
        }

    }
}