package com.example.projectavi001

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AddPaymentDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PaymentDetailRepository = PaymentDetailRepository(application)
    var repoCall: MutableLiveData<Boolean> = MutableLiveData(false)
    var byDate: MutableLiveData<String> = MutableLiveData(getCurrentDate())
    var addPaymentBtn: MutableLiveData<Boolean> = MutableLiveData(false)
    var updatePaymentDetail: MutableLiveData<PaymentDetail> = MutableLiveData()

    fun insert(paymentDetail: PaymentDetail) {
        repository.insert(paymentDetail)
        if (repository.actionResult.success) {
            repoCall.value = repository.actionResult.success
        } else {
            repoCall.value = repository.actionResult.success
        }
    }

    fun update(paymentDetail: PaymentDetail) {
        repository.update(paymentDetail)
    }

    fun delete(paymentDetail: PaymentDetail) {
        repository.delete(paymentDetail)
    }

    fun getAllPaymentsByDate(date: String): LiveData<List<PaymentDetail>> {
        return repository.getAllPaymentDetailsByDate(date)
    }
}
