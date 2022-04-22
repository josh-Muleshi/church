package cd.wayupdev.church.ui.screen.home.business

import cd.wayupdev.church.data.model.Post

sealed class HomeState {
    object Uninitialized: HomeState()
    object Loading : HomeState()
    data class Error(val message: String) : HomeState()
    data class Success(val posts: ArrayList<Post>) : HomeState()
}