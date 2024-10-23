package com.polije.androidexpertsubmission1.core.utils

import android.util.Log
import com.polije.androidexpertsubmission1.core.data.source.local.entity.AgentEntity
import com.polije.androidexpertsubmission1.core.data.source.remote.response.DataItem
import com.polije.androidexpertsubmission1.core.domain.model.Agent

object DataMapper {
    fun mapResponsesToEntities(input: List<DataItem>): List<AgentEntity> {
        val menuList = ArrayList<AgentEntity>()
        input.map {
            val menu = AgentEntity(
                fullPortrait = it.fullPortrait,
                displayName = it.displayName,
                description = it.description,
                uuid = it.uuid,
                developerName = it.developerName,
                isFavorite = false
            )
            menuList.add(menu)
        }
        return menuList
    }

    fun mapEntitiesToDomain(input: List<AgentEntity>): List<Agent> =
        input.map {
            Log.d("DataMapper", "Mapping agent with fullPortrait: ${it.fullPortrait}")
            Agent(
                fullPortrait = it.fullPortrait ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/1280px-Valorant_logo_-_pink_color_version.svg.png",
                displayName = it.displayName,
                description = it.description,
                uuid = it.uuid,
                developerName = it.developerName,
                isFavorite = it.isFavorite,
            )
        }

    fun mapDomainToEntity(input: Agent) = AgentEntity(
        fullPortrait = input.fullPortrait,
        displayName = input.displayName,
        description = input.description,
        uuid = input.uuid,
        developerName = input.developerName,
        isFavorite = input.isFavorite
    )
}