package com.example.projectavi001

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * Created by abhilashgupta on Oct, 2019
 */

class LendAndBorrowViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LendAndBorrowRepository = LendAndBorrowRepository(application)

    fun insert(lendAndBorrowDetail: LendAndBorrowDetail) {
        repository.insert(lendAndBorrowDetail)
    }

    fun update(lendAndBorrowDetail: LendAndBorrowDetail) {
        repository.update(lendAndBorrowDetail)
    }

    fun delete(lendAndBorrowDetail: LendAndBorrowDetail) {
        repository.delete(lendAndBorrowDetail)
    }
}