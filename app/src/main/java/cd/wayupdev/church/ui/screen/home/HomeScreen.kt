package cd.wayupdev.church.ui.screen.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cd.wayupdev.church.R
import cd.wayupdev.church.data.model.Post
import cd.wayupdev.church.ui.screen.home.business.HomeState
import cd.wayupdev.church.ui.screen.home.business.HomeViewModel
import cd.wayupdev.church.ui.screen.home.component.TopPageBar
import cd.wayupdev.church.ui.theme.Black_ic
import cd.wayupdev.church.ui.theme.Blue_box
import cd.wayupdev.church.ui.theme.Red_box
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current

//    BackHandler(enabled = true) {
//        (context as? Activity)?.finish()
//    }

    val posts by viewModel.data.collectAsState()

    Scaffold(
        topBar = {
            TopPageBar(navController)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            if(posts is HomeState.Success){
                DisplayItShow((posts as HomeState.Success).posts, viewModel){
                    //navController.navigate(Screen.DetailPost.route)
                    Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DisplayItShow(posts: ArrayList<Post>, viewModel: HomeViewModel, selectedItem: (Post)->(Unit)) {

    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000)
            refreshing = false
        }
    }
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = {
            refreshing = true
            viewModel.data
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize(),
            content = {
                items(count = posts.size) {
                    ItemUi(posts[it], selectedItem = { post ->
                        selectedItem.invoke(post)
                    })
                }
            }
        )
    }
}

@Composable
fun ItemUi(post: Post, selectedItem: (Post)->(Unit)) {

    val visible by remember {
        mutableStateOf(post.category != "")
    }

    var extended by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            ItemShowImage(post = post)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .clickable {
                        selectedItem(post)
                        extended = !extended
                    }
                    .padding(10.dp)
            ) {
                Text(text = post.title, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = post.description,
                    style = MaterialTheme.typography.body1,
                    maxLines = if (extended) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                val color by animateColorAsState(if (post.category == "Predication") Red_box else Blue_box)
                AnimatedVisibility(visible) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                            .border(
                                width = 1.dp,
                                color = color,
                                RoundedCornerShape(corner = CornerSize(10.dp))
                            )
                            .background(color = color)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = post.category,
                            fontSize = 17.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemShowImage(post: Post) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        GlideImage(
            imageModel = post.imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .background(color = Color.White)
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.BottomEnd)
        ){
            Row {
                Image(painterResource(id = R.drawable.ic_date), contentDescription = "date")
                Text(
                    text = post.date,
                    fontSize = 17.sp
                )
            }
        }
        BottomShadow()
    }
}

@Composable
fun BottomShadow() {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Black_ic,
                        Color.Transparent
                    )
                )
            )
            .height(60.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = Color.White)
                    .padding(start = 8.dp, end = 8.dp),
                contentAlignment = Alignment.Center
            ){
                Image(imageVector = Icons.Default.Favorite, contentDescription = "date")
            }
        }
    }
}

@Preview
@Composable
fun ScHome() {
    BottomShadow()
}