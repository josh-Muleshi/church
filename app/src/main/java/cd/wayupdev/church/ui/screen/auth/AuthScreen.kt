package cd.wayupdev.church.ui.screen.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cd.wayupdev.church.app.navigation.Screen
import cd.wayupdev.church.ui.screen.auth.business.AuthState
import cd.wayupdev.church.ui.screen.auth.business.AuthViewModel

@Composable
fun AuthScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        when (state) {
            is AuthState.Success -> {
                navController.navigate(Screen.AddPost.route)
            }
            is AuthState.Error -> {
                snackbarHostState.showSnackbar((state as AuthState.Error).errorMessage)
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.LightGray,
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray
            ),
            label = {
                Text(text = "Email")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation('*'),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            label = {
                Text(text = "Password")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            enabled = email.isNotBlank() && password.isNotBlank() && state != AuthState.Loading,
            onClick = { viewModel.register(email = email.trim(), password = password.trim()) },
            contentPadding = PaddingValues(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            ),
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Sing in", style = MaterialTheme.typography.button)
                Spacer(modifier = Modifier.width(4.dp))
                AnimatedVisibility(visible = state == AuthState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.Red)
                }
            }
        }
    }
}