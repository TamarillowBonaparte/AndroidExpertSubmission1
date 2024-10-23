package com.polije.androidexpertsubmission1.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "agent")
data class AgentEntity(

    @ColumnInfo(name = "fullPortrait")
    val fullPortrait: String? = null,

    @ColumnInfo(name = "displayName")
    val displayName: String,

    @ColumnInfo(name = "description")
    val description: String,

    @PrimaryKey
    val uuid: String,

    @ColumnInfo(name = "developerName")
    val developerName: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
