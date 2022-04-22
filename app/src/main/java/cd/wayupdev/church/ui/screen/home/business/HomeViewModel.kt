package cd.wayupdev.church.ui.screen.home.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdev.church.data.model.Post
import cd.wayupdev.church.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    private val _data = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val data: StateFlow<HomeState>
        get() = _data

    init {
        getAllPosts()
    }

    @ExperimentalCoroutinesApi
    private fun getAllPosts() = viewModelScope.launch {
        _data.emit(HomeState.Loading)
        try {
            postRepository.getAll().collect { posts ->
                _data.emit(HomeState.Success(posts = posts as ArrayList<Post>))
            }
        } catch (t: Throwable) {
            _data.emit(HomeState.Error(t.message.toString()))
        }
    }
}