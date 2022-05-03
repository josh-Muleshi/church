package cd.wayupdev.church.ui.screen.addpost.business

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdev.church.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class AddPostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    var title by mutableStateOf("")
    var desc by mutableStateOf("")
    var date by mutableStateOf("")
    var category by mutableStateOf("")

    private val _addPostState = MutableStateFlow<AddPostState>(AddPostState.Uninitialized)
    val addPostState: StateFlow<AddPostState>
        get() = _addPostState

    fun addPost(title: String, description: String, date: String, category: String ,uri: Uri) = viewModelScope.launch {
        _addPostState.emit(AddPostState.Loading)
        try {
            postRepository.add(title, description, date, category, uri)
            _addPostState.emit(AddPostState.Success)
        } catch (t: Throwable) {
            _addPostState.emit(AddPostState.Error(t.message.toString()))
        }
    }

    fun delete(contactUid: String) = viewModelScope.launch {
        //contactRepository.delete(contactUid = contactUid)
    }
}