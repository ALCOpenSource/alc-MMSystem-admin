package com.peculiaruc.alc_mmsystem_admin.domain.models

/**
 *  represent the Report object that will come from api .
 */
data class Report(val title: String, val author: String, val date: String) : Item()




/**
 * Report
 *
 * @property id
 * @property title
 * @property time
 * @property user
 * @constructor Create empty Report
 */
data class Report2 (val id:Int, val title: String, val time: String, val user: String)