package com.example.projectavi001

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable

/**
 * Created by abhilashgupta on Sep, 2019
 */
@Dao
interface PaymentDetailDao {

    @Insert
    fun insert(paymentDetail: PaymentDetail): Completable

    @Delete
    fun delete(paymentDetail: PaymentDetail): Completable

    @Update
    fun update(paymentDetail: PaymentDetail): Completable

    @Query("DELETE FROM payment_detail")
    fun deleteAllFromPaymentDetail()

    @Query("SELECT * FROM payment_detail WHERE date = (:date)")
    fun getAllPaymentDetailsByDate(date: String): LiveData<List<PaymentDetail>>
}