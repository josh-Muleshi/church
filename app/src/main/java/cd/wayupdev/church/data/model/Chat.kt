package cd.wayupdev.church.data.model

import java.util.*

data class Chat(
    val uid: String = "",
    val UserUid: String = "",
    val message: String = "",
    val imageUrl: String = "",
    val createdAt: Date? = null
)