package cd.wayupdev.church.ui.screen.addpost.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdev.church.ui.screen.addpost.business.AddPostViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MyContent(viewModel: AddPostViewModel = hiltViewModel()){

    var mExpanded by remember { mutableStateOf(false) }

    val categories = listOf("Predication", "Annonce")

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Row(
        Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                mTextFieldSize = coordinates.size.toSize()
            }
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "Categorie : ${viewModel.category}")
        Icon(
            icon,
            "contentDescription",
            Modifier
                .clickable { mExpanded = !mExpanded }
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            categories.forEach { label ->
                DropdownMenuItem(onClick = {
                    viewModel.category = label
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}