package com.example.laboratorio8.Product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")

class Product (
    var name: String,
    var cant: Int
    ){
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0

        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
        var photo: ByteArray? = null
}