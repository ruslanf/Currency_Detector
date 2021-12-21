package bz.soft.currencydetector.data.db

import androidx.room.*

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)

    @Update
    suspend fun update(vararg  obj: T)

    @Delete
    suspend fun delete(vararg  obj: T)
}