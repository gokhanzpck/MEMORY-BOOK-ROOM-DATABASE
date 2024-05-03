package com.gokhanzopcuk.memorybook.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gokhanzopcuk.memorybook.Dao.PlaceDao
import com.gokhanzopcuk.memorybook.Model.Place

@Database(entities = [Place::class], version = 1)
abstract class PlaceDataBase:RoomDatabase(){
    abstract fun placeDao():PlaceDao

}