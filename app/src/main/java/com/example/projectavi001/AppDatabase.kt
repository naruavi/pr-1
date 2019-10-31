package com.example.projectavi001

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by abhilashgupta on Sep, 2019
 */

@Database(entities = [PaymentDetail::class, LendAndBorrowDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paymentDetailDao(): PaymentDetailDao
    abstract fun lendAndBorrowDoa(): LendAndBorrowDetailDao

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(
            it.applicationContext,
            AppDatabase::class.java,
            it.applicationContext.getString(R.string.appDb)
        ).build()
    })

}