package com.amrhishammahmoud.uncleJacks.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName



//response
@JsonClass(generateAdapter = true)
@Serializable
@Parcelize
@Entity(tableName = "fruit_table")
data class Fruits(
    @PrimaryKey (autoGenerate = true) var id: Int,
    var Name: String,
    var Type:String,
    var Price : String,
    var Image : String
) : Parcelable

/*****************************************************************************/
@JsonClass(generateAdapter = true)
@Serializable
@Parcelize
@Entity(tableName = "juice_table")
data class Juices(
    @PrimaryKey (autoGenerate = true) var id: Int,
    var Name: String,
    var Type:String,
    var Price : String,
    var Image : String
) : Parcelable


@JsonClass(generateAdapter = true)
@Serializable
@Parcelize
@Entity(tableName = "jacksMixes_table")
data class JacksMixes(
    @PrimaryKey (autoGenerate = true) var id: Int,
    var Name: String,
    var Type:String,
    var Price : String,
    var Image : String
) : Parcelable


@JsonClass(generateAdapter = true)
@Serializable
@Parcelize
@Entity(tableName = "services_table")
data class Services(
    @PrimaryKey (autoGenerate = true) var id: Int,
    var Service: String,
    var Delivery:String,
    var VAT : String
) : Parcelable



/************************************************************************************************/

@Serializable
@Parcelize
@Entity(tableName = "final_table")
data class CartProductsFinal(
    @Serializable
    var itemPicFinal: String,
    @Serializable
    var itemNameFinal: String,
    @Serializable
    @PrimaryKey var itemPriceFinal:String,
    @Serializable
    var itemTypeFinal :String,
    @Serializable
    var itemQuantityFinal :String,
    @Serializable
    var timeDate: String,
    @Serializable
    var namePerson: String,
    @Serializable
    var telephonePerson:String,
    @Serializable
    var emailPerson:String
) : Parcelable


@Serializable
@Parcelize
@Entity(tableName = "favorite_table")
data class FavoriteProducts(
    @PrimaryKey var itemPicFavorite: String,
    var itemNameFavorite: String,
    var itemTypeFinal :String,
    var itemPriceFavorite:String
    ) : Parcelable




/*************************************************************************************************/


@Serializable
@Parcelize
@Entity(tableName = "finalCart_table")
data class FinalCartData(
    @Serializable
    @PrimaryKey var timeDate: String,
    @Serializable
    var namePerson: String,
    @Serializable
    var telephonePerson:String,
    @Serializable
    var itemNameFinal :String,
    @Serializable
    var itemPriceFinal :String,
    @Serializable
    var itemTypeFinal :String,
    @Serializable
    var itemQuantityFinal :String
) : Parcelable


/*
@Serializable
@Parcelize
@Entity(tableName = "cart_final_table")
data class CartFinal(
    @PrimaryKey (autoGenerate = true) var id: Int,
    var NameItem: String,
    var TypeItem:String,
    var PriceItem : String,
    var quantity : String
) : Parcelable
*/

@Serializable
@Parcelize
@Entity(tableName = "bill_final_table")
data class BillFinal(
    var id: Int,
    var namePerson : String,
    var phoneNumber : String,
    var emailPerson : String,
    var totalPrice: String,
    var deliveryFee:String,
    var serviceFee : String,
    var vat : String,
    var totalFinalPrice:String,
    var longitude : String,
    var latitude : String,
    @PrimaryKey var timeDate : String
) : Parcelable

