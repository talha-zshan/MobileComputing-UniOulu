package com.example.talhamobilecomputing.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(){
    Scaffold() {
        LazyColumn{
            items( count = 3){
                Text(text = "test")
            }
            
            item{
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Add Note")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen(){
    HomeScreen()
}