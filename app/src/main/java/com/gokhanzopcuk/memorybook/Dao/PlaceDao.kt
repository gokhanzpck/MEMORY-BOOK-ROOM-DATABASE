package com.gokhanzopcuk.memorybook.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.gokhanzopcuk.memorybook.Model.Place
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface PlaceDao {
    @Insert
    fun insert(place: Place):Completable
    @Delete
    fun delete(place: Place): Completable
    @Update
    fun update(place: Place):Completable
    @Query("SELECT * FROM Place")
    fun GetAll() : Flowable<List<Place>>
}