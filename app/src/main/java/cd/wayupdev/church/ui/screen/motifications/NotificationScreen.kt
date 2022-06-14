package cd.wayupdev.church.ui.screen.motifications

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdev.church.data.model.Post
import cd.wayupdev.church.ui.screen.motifications.business.NotificationState
import cd.wayupdev.church.ui.screen.motifications.business.NotificationViewModel
import cd.wayupdev.church.ui.screen.topAppBar.AppBar
import com.google.accompanist.insets.imePadding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun NotificationScreen(viewModel: NotificationViewModel = hiltViewModel()) {

    val posts by viewModel.data.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        modifier = Modifier.background(Color.LightGray),
        topBar = {
            AppBar("Notification")
        }
    ) {
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
            sheetContent = {
//                AddContactScreen(onSubmit = { name, phone ->
//                    coroutineScope.launch {
//                        bottomSheetState.hide()
//                    }
//                    //viewModel.addContact(name, phone)
//                })
            },
            modifier = Modifier.imePadding()
        ) {
            Crossfade(targetState = posts) {
                when (it) {
                    is NotificationState.Success -> {
                        ListSection(
                            (posts as NotificationState.Success).post,
                            onContactDelete = { uid ->
                               // viewModel.delete(uid)
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Contact supprimé")
                                }
                            })
                    }

                    else -> {}
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ListSection(posts: ArrayList<Post>, onContactDelete: (uid: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize(),
        content = {
            item {
                Text(text = "${posts.size} Contacts", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(count = posts.size) {
                ContactItem(posts[it], onSwipe = { uid ->
                    onContactDelete.invoke(uid)
                })
            }
        })
}

@ExperimentalMaterialApi
@Composable
fun ContactItem(post: Post, onSwipe: (uid: String) -> Unit) {

    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                onSwipe.invoke(post.uid)
            }
            true
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                color = Color.Red
            ) {

            }
        },
        dismissContent = {
            Card(modifier = Modifier.fillMaxWidth(), elevation = 2.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.wrapContentSize()) {
                        Text(
                            text = post.category,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(text = post.createdAt.toString())
                    }
                }
            }
        }
    )
}

