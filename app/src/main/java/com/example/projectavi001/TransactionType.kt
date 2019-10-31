package com.example.projectavi001

import java.util.HashMap

/**
 * Created by abhilashgupta on Oct, 2019
 */
enum class TransactionType(i: String) {
    DEBIT("DEBIT"),
    CREDIT("CREDIT"),
    PENDING("PENDING"),
    UNKOWN("UNKNOW");

    var type: String = ""
        internal set

    init {
        type = i
    }

    fun getTransactionType(): String {
        return type
    }
}