package cd.wayupdev.church.ui.screen.addpost.business

sealed class AddPostState {
    object Uninitialized : AddPostState()
    object Loading : AddPostState()
    data class Error(val errorMessage: String) : AddPostState()
    object Success: AddPostState()
}