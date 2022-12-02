package com.peculiaruc.alc_mmsystem_admin.domain.models

/**
 * data class of Mentor object
 */

data class Mentor(
    val name: String,
    val image: String,
    val role: String,
    val tags: List<String>
)