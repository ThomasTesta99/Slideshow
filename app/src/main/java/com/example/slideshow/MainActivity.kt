package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slideshow.ui.theme.SlideshowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideshowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ImageBox(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun ImageBox(modifier: Modifier = Modifier){
    val numOfImages = 6
    var imageNum by remember { mutableStateOf(1) }
    Box(){
        Image(
            painterResource(imageNum),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = {imageNum = changeImageNum(false, imageNum, numOfImages) }){
                Text(stringResource(R.string.back))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {imageNum = changeImageNum(true, imageNum, numOfImages)}){
                Text(stringResource(R.string.next))
            }
        }
    }
}

private fun changeImageNum(up: Boolean, imageNum: Int, numOfImages: Int): Int{
    var num = imageNum
    if(up){
        num++
    }else{
        num--
    }

    if(num > numOfImages){
        num = 1
    }
    if(num < 1){
        num = numOfImages
    }

    return num
}
