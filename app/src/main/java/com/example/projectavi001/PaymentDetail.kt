package com.example.projectavi001

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by abhilashgupta on Sep, 2019
 */

@Parcelize
@Entity(tableName = "payment_detail")
data class PaymentDetail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "reason")
    var reason: String,
    @ColumnInfo(name = "price")
    var price: Double,
    @ColumnInfo(name = "transaction_type")
    var transactionType: String,
    @ColumnInfo(name = "payment_method")
    var paymentMethod: String
) : Parcelable