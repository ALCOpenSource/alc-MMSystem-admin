package com.peculiaruc.alc_mmsystem_admin.domain.models

import com.peculiaruc.alc_mmsystem_admin.type.ProgramProgress

data class ProgramAdmin(val id:Int, val title: String, val avatar: String, val description:String, val active:Boolean, val complete: Boolean)