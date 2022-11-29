package com.peculiaruc.alc_mmsystem_admin.database

import java.util.Date

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