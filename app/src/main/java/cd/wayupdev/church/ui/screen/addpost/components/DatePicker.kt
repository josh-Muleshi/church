package cd.wayupdev.church.ui.screen.addpost.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdev.church.R
import cd.wayupdev.church.ui.screen.addpost.business.AddPostViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun ShowDatePicker(viewModel: AddPostViewModel = hiltViewModel()){

    val context = LocalContext.current

    val year: Int
    val month : Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.date = "$dayOfMonth . ${month+1} . $year"
        }, year, month, day
    )

    Card(modifier = Modifier.background(Color.Unspecified), elevation = 8.dp, onClick = {
        datePickerDialog.show()
    }) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .background(color = Color.White)
                .padding(25.dp),
        ) {
            Row{
                Image(painterResource(id = R.drawable.ic_date), contentDescription = "date")
                Text(
                    text = viewModel.date,
                    fontSize = 17.sp,
                    color = Color.Black
                )
            }
        }
    }
}