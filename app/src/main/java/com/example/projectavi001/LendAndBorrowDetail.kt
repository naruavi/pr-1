package com.example.projectavi001

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by abhilashgupta on Oct, 2019
 */
@Entity(tableName = "lend_and_borrow_detail")
class LendAndBorrowDetail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "money")
    var money: Double,
    @ColumnInfo(name = "lend_or_borrow")
    var lendOrBorrow: Int
)