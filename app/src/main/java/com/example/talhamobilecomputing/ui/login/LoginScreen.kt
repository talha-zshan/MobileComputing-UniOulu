package com.example.talhamobilecomputing.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.talhamobilecomputing.R
import com.example.talhamobilecomputing.ui.theme.Purple700

@Composable
fun LoginScreen(
    modifier: Modifier
){
    val username = remember { mutableStateOf(value = "") }
    val password = remember { mutableStateOf(value = "") }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    /* Sign-Up TextField */
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Don't have an account? Sign up here"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Welcome", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        /* Username TextField */
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = username.value,
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
            onValueChange = { pwdString -> password.value = pwdString},
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            placeholder =  { Text(text = "Enter your password")},
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "Password")}
        )

        /* Login Button */
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        /* Forgot Password TextField */
        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot Password?"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )

    }
}