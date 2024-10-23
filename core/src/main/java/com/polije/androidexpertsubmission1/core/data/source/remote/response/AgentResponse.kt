package com.polije.androidexpertsubmission1.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AgentResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: Int
)

data class DataItem(

	@field:SerializedName("fullPortrait")
	val fullPortrait: String,

	@field:SerializedName("displayName")
	val displayName: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("developerName")
	val developerName: String
)
