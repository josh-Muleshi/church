package cd.wayupdev.church.ui.screen.motifications.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdev.church.data.model.Post
import cd.wayupdev.church.data.repository.PostRepository
import cd.wayupdev.church.ui.screen.addpost.business.AddPostState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    private val _data = MutableStateFlow<NotificationState>(NotificationState.Uninitialized)
    val data: StateFlow<NotificationState>
        get() = _data

    @ExperimentalCoroutinesApi
    private fun getAllContacts() = viewModelScope.launch {
        _data.emit(NotificationState.Loading)
        try {
//            contactRepository.getAll().collect { contacts ->
//                _data.emit(NotificationState.Success(contacts = contacts as ArrayList<Post>))
//            }
        } catch (t: Throwable) {
            _data.emit(NotificationState.Error(t.message.toString()))
        }
    }

}