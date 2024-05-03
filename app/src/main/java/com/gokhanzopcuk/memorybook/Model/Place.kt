package com.gokhanzopcuk.memorybook.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Place(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id:Int=0,
    @ColumnInfo(name = "placeName")
    var placeName: String,
@ColumnInfo(name = "placeInfo")
    var placeInfo:String

):Serializable{

}