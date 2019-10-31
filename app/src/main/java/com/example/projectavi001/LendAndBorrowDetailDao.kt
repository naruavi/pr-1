package com.example.projectavi001

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable

/**
 * Created by abhilashgupta on Oct, 2019
 */
@Dao
interface LendAndBorrowDetailDao {
    @Insert
    fun insert(transaction: LendAndBorrowDetail): Completable

    @Delete
    fun delete(transaction: LendAndBorrowDetail): Completable

    @Update
    fun update(transaction: LendAndBorrowDetail): Completable

    @Query("DELETE FROM lend_and_borrow_detail")
    fun deleteAllFromPaymentDetail()

    @Query("SELECT * FROM lend_and_borrow_detail")
    fun getAllEntryList(): LiveData<List<LendAndBorrowDetail>>
}