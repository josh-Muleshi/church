package cd.wayupdev.church.ui.screen.addpost

import android.app.DatePickerDialog
import android.net.Uri
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cd.wayupdev.church.R
import cd.wayupdev.church.app.navigation.Screen
import cd.wayupdev.church.ui.screen.addpost.business.AddPostViewModel
import cd.wayupdev.church.ui.screen.addpost.components.MyContent
import cd.wayupdev.church.ui.screen.addpost.components.ShowDatePicker
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AddPostScreen(navController : NavHostController, viewModel: AddPostViewModel = hiltViewModel()){

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val focusRequest = remember {
        FocusRequester()
    }

    LaunchedEffect(Unit){
        focusRequest.requestFocus()
    }

    var textFieldHeight by remember {
        mutableStateOf(190.dp)
    }

    if (imageUri != null) {
        textFieldHeight = Dp.Unspecified
    } else {
        190.dp
    }

    Column {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .width(120.dp)) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate(Screen.Home.route)
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "close", modifier = Modifier.size(30.dp))
                }
                Button(
                    enabled = viewModel.title.isNotEmpty() && viewModel.title.isNotEmpty(),
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                    onClick = {
                        imageUri?.let { viewModel.addPost(viewModel.title, viewModel.desc, viewModel.date, viewModel.category, it) }
                        navController.navigate(Screen.Home.route)
                    }
                ) {
                    Text(text = "Save")
                }
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                value = viewModel.title,
                onValueChange = { viewModel.title = it },
                placeholder = { Text("Title...") },
                textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize, fontWeight = FontWeight.Medium),
                singleLine = true,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequest),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    focusedIndicatorColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background
                )
            )

            MyContent()

            TextField(
                value = viewModel.desc,
                onValueChange = { viewModel.desc = it },
                placeholder = { Text("Description...") },
                textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequest)
                    .defaultMinSize(minHeight = textFieldHeight),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    focusedIndicatorColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background
                )
            )
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                imageUri = uri
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                if (imageUri != null) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, end = 24.dp),horizontalArrangement = Arrangement.End) {
                        GlideImage(
                            imageModel = imageUri,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(200.dp)
                                .height(180.dp)
                                .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))

                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(shape = RoundedCornerShape(corner = CornerSize(10.dp)), onClick = {
                        launcher.launch("image/*")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_photo_camera),
                            contentDescription = null,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    ShowDatePicker()
                }
            }
        }
    }
}


