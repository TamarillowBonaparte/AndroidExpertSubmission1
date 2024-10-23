package com.polije.androidexpertsubmission1.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.polije.androidexpertsubmission1.core.data.source.local.entity.AgentEntity

@Database(entities = [AgentEntity::class], version = 2, exportSchema = false)
abstract class AgentDatabase : RoomDatabase() {

    abstract fun agentDao(): AgentDao
}