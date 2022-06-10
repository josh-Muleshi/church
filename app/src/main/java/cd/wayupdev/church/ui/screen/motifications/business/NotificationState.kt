package cd.wayupdev.church.ui.screen.motifications.business

import cd.wayupdev.church.data.model.Post

sealed class NotificationState {
    object Uninitialized: NotificationState()
    object Loading : NotificationState()
    data class Error(val message: String) : NotificationState()
    data class Success(val post: ArrayList<Post>) : NotificationState()
}