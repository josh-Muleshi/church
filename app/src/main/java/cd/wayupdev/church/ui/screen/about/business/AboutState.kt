package cd.wayupdev.church.ui.screen.about.business

sealed class AboutState {
    object Uninitialized : AboutState()
    object Loading : AboutState()
    data class Error(val errorMessage: String) : AboutState()
    data class Success(val isAuth: Boolean) : AboutState()
}