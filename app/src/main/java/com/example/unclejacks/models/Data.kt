package com.example.unclejacks.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "fruit_table")
data class Fruits(
    @PrimaryKey var itemPic: Int,
    var itemName: String,
    var itemPrice:String
) : Parcelable

@Parcelize
@Entity(tableName = "juice_table")
data class Juices(
    @PrimaryKey var itemPic: Int,
    var itemName: String,
    var itemPrice:String
) : Parcelable


@Parcelize
@Entity(tableName = "final_table")
data class CartProductsFinal(
    @PrimaryKey var itemPicFinal: Int,
    var itemNameFinal: String,
    var itemPriceFinal:String,
    var itemQuantityFinal :String
) : Parcelable


@Parcelize
@Entity(tableName = "favorite_table")
data class FavoriteProducts(
    @PrimaryKey var itemPicFavorite: Int,
    var itemNameFavorite: String,
    var itemPriceFavorite:String
    ) : Parcelable
