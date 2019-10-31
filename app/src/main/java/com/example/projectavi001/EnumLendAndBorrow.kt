package com.example.projectavi001

/**
 * Created by abhilashgupta on Oct, 2019
 */
enum class EnumLendAndBorrow(i: Int) {
    LEND(-1),
    BORROW(1);

    var type: Int = 0
        internal set

    init {
        type = i
    }

    fun getTransactionType(): Int {
        return type
    }
}