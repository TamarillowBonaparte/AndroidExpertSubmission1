package com.polije.androidexpertsubmission1.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent(
    val fullPortrait: String,
    val displayName: String,
    val description: String,
    val uuid: String,
    val developerName: String,
    val isFavorite: Boolean
): Parcelable
