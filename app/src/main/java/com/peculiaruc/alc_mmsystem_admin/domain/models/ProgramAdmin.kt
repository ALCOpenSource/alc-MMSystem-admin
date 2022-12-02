package com.peculiaruc.alc_mmsystem_admin.domain.models

/**
 * Program admin
 *
 * @property id
 * @property title
 * @property avatar
 * @property description
 * @property active
 * @property complete
 * @constructor Create empty Program admin
 */
data class ProgramAdmin(
    val id: Int,
    val title: String,
    val avatar: String,
    val description: String,
    val active: Boolean,
    val complete: Boolean
)