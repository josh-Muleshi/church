package cd.wayupdev.church.ui.screen.about.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow<AboutState>(AboutState.Uninitialized)
    val state: StateFlow<AboutState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.emit(AboutState.Loading)
            if (sharedPreferences.getBoolean("is-auth", false)) {
                _state.emit(AboutState.Success(true))
            } else {
                _state.emit(AboutState.Success(false))
            }
        }
    }
}