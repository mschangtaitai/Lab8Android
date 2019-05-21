package com.example.laboratorio8.Product

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)


    @Query("DELETE FROM product_table")
    fun deleteAllProducts()

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): LiveData<List<Product>>
}