package com.example.projectavi001

/**
 * Created by abhilashgupta on Oct, 2019
 */
enum class TransactionType(i: Int) {
    DEBIT(1),
    CREDIT(2),
    PENDING(3),
    UNKOWN(4);

    var type: Int = 0
        internal set

    init {
        type = i
    }

    fun getTransactionType(): Int {
        return type
    }
}