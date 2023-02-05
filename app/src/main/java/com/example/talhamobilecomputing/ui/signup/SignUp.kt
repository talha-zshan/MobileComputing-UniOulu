package com.example.talhamobilecomputing.ui.signup


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.talhamobilecomputing.viewmodel.AuthViewModel
import com.example.talhamobilecomputing.viewmodel.UserLoginStatus

@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel = viewModel(),
    navController: NavController
){
    val username = remember { mutableStateOf(value = "") }
    val password = remember { mutableStateOf(value = "") }
    val confirmPassword = remember { mutableStateOf(value = "") }

    var isPasswordHidden by remember { mutableStateOf(true) }
    val loginStatus = authViewModel.userLoginStatus.collectAsState()
    val localContext = LocalContext.current
    var showFailedDialog = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = loginStatus.value){
        when(loginStatus.value){
            is UserLoginStatus.Failure -> {
                localContext.showToast("Unable to Register")
                showFailedDialog.value = true
            }
            UserLoginStatus.Successful -> {
                localContext.showToast("Registration Successful")
                navController.navigate("home")
            }
            null -> {
            }
        }
    }


//    Box(modifier = Modifier.fillMaxSize()){
//        Image(
//            painter = painterResource(id = R.drawable.login),
//            contentDescription = "background",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Register", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        /* Username TextField */
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = username.value,
            shape = RoundedCornerShape(50.dp),
            onValueChange = { text -> username.value = text},
            label = { Text(text = "Username") },
            placeholder =  { Text(text = "Enter your email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next ),
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email")}
        )

        /* Password TextField */
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password.value,
            shape = RoundedCornerShape(50.dp),
            onValueChange = { pwdString -> password.value = pwdString},
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            placeholder =  { Text(text = "Enter your password")},
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "Password")}
        )

        /* Confirm Password TextField */
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = confirmPassword.value,
            shape = RoundedCornerShape(50.dp),
            onValueChange = { pwdString -> confirmPassword.value = pwdString},
            label = { Text(text = "Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            placeholder =  { Text(text = "Confirm Password")},
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "Password")}
        )

        /* Login Button */
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
            Button(
                onClick = {
                    when {
                        username.value.isBlank() -> {
                            localContext.showToast(msg = "Enter your username")
                        }

                        password.value.isBlank() -> {
                            localContext.showToast(msg = "Enter your password")
                        }

                        confirmPassword.value.isBlank() -> {
                            localContext.showToast(msg = "re-enter your password")
                        }

                        else -> {
                            authViewModel.createNewUser(username.value, password.value)
                            authViewModel.loginToApp(username.value, password.value)
                        }
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

    }

}

private fun Context.showToast(msg: String){
    Toast.makeText( this, msg, Toast.LENGTH_SHORT).show()
}