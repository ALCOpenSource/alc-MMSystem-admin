package com.peculiaruc.alc_mmsystem_admin.domain.models

import java.util.Date

/**
defines the data class of Chat input from
user input
 */

data class Chat(
    var id: String,
    var toId: String,
    var fromId: String,
    var isRead: Boolean,
    var sentTime: Date,
    var isDelivered: Boolean,
    var message: String,
    var messageTitle: String?
)