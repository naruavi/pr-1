package com.example.projectavi001

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by abhilashgupta on Oct, 2019
 */

class LendAndBorrowRepository(application: Application) {
    private var lendAndBorrowDetailDao: LendAndBorrowDetailDao
    val actionResult = BaseDatabaseAdapterFactory(false, "")

    init {
        val database: AppDatabase = AppDatabase.getInstance(application)
        lendAndBorrowDetailDao = database.lendAndBorrowDoa()
    }

    fun getAllPaymentDetailsByDate(date: String): LiveData<List<LendAndBorrowDetail>> {
        return lendAndBorrowDetailDao.getAllEntryList()
    }

    @SuppressLint("CheckResult")
    fun insert(lendAndBorrowDetail: LendAndBorrowDetail) {
        lendAndBorrowDetailDao.insert(lendAndBorrowDetail).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(
                Schedulers.io()
            )
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
    fun delete(lendAndBorrowDetail: LendAndBorrowDetail) {
        lendAndBorrowDetailDao.delete(lendAndBorrowDetail).subscribeOn(Schedulers.io())
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
    fun update(lendAndBorrowDetail: LendAndBorrowDetail) {
        lendAndBorrowDetailDao.update(lendAndBorrowDetail).subscribeOn(Schedulers.io())
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