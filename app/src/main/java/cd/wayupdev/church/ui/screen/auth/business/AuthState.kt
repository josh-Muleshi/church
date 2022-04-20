package cd.wayupdev.church.ui.screen.auth.business

sealed class AuthState {
    object Uninitialized : AuthState()
    object Loading : AuthState()
    data class Error(val errorMessage: String) : AuthState()
    object Success: AuthState()
}