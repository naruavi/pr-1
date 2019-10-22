package com.example.projectavi001

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by abhilashgupta on Sep, 2019
 */

class PaymentDetailRepository(application: Application) {
    private var paymentDetailDao: PaymentDetailDao
    val actionResult = BaseDatabaseAdapterFactory(false, "")

    init {
        val database: AppDatabase = AppDatabase.getInstance(application)
        paymentDetailDao = database.paymentDetailDao()
    }

    fun getAllPaymentDetailsByDate(date: String): LiveData<List<PaymentDetail>> {
        return paymentDetailDao.getAllPaymentDetailsByDate(date)
    }

    @SuppressLint("CheckResult")
    fun insert(paymentDetail: PaymentDetail) {
        paymentDetailDao.insert(paymentDetail).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribe(
                {
                    actionResult.success = true
                    Timber.i("Insert")
                },
                {
                    actionResult.success = false
                    actionResult.error = it.message.toString()
                    Timber.d(it)
                }
            )
    }

    @SuppressLint("CheckResult")
    fun delete(paymentDetail: PaymentDetail) {
        paymentDetailDao.delete(paymentDetail).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    actionResult.success = true
                    Timber.i("delete")
                },
                {
                    actionResult.success = false
                    actionResult.error = it.message.toString()
                    Timber.d(it)
                }
            )
    }

    @SuppressLint("CheckResult")
    fun update(paymentDetail: PaymentDetail) {
        paymentDetailDao.update(paymentDetail).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    actionResult.success = true
                    Timber.i("update")
                },
                {
                    actionResult.success = false
                    actionResult.error = it.message.toString()
                    Timber.d(it)
                }
            )
    }

}
