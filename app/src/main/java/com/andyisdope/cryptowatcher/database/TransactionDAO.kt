package com.andyisdope.cryptowatcher.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.andyisdope.cryptowatcher.model.Transaction

@Dao
interface TransactionDAO {

    @Insert
    fun insertAll(vararg toAdd: Transaction)

    @Delete
    fun deleteTrans(toRemove: Transaction)

    @Query("Delete From Transactions")
    fun deleteAll()

    @Query("Select * from Transactions Order By Date")
    fun getAll(): List<Transaction>

    @Query("Select * from Transactions where Buy = 1")
    fun getAllBuys(): List<Transaction>

    @Query("Select SUM(Net) from Transactions where Buy = 1 AND Coin = :coin")
    fun getAllCoinBuys(coin: String):Float

    @Query("Select SUM(Net) from Transactions where Sell = 1 AND Coin = :coin")
    fun getAllCoinSells(coin: String): Float

    @Query("Select * from Transactions where Sell = 1")
    fun getAllSells(): List<Transaction>

    @Query("Select * from Transactions where Coin = :coin")
    fun getCoinTransactions(coin: String): List<Transaction>

    @Query("Select SUM(Net) from Transactions")
    fun getNetAssests(): Float

    @Query("Select SUM(Net) from Transactions where Sell = 1")
    fun getSellSum(): Float

    @Query("Select SUM(Net) from Transactions where Buy = 1")
    fun getBuySum(): Float

    @Query("Select SUM(Amount) from Transactions where Coin = :coin")
    fun getCurrentCoins(coin: String): Float
}