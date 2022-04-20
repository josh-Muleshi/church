package cd.wayupdev.church.data.model

import java.util.*

data class User(
    val uid: String = "",
    val name: String = "",
    val tel: String = "",
    val createdAt: Date? = null
)
