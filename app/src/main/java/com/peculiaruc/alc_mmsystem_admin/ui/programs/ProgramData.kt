package com.peculiaruc.alc_mmsystem_admin.ui.programs

/*
* ProgramData
* */
data class ProgramData(
    val id: Int = 0,
    val image: String = "",
    val title: String = "",
    val date: String = "",
    val description: String = "",
    val isDeleted: Boolean = false,
    val mentorManagers: Int = 0,
    val mentors: Int = 0
)
