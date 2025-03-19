package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
                    ImageBox(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ImageBox(modifier: Modifier = Modifier){
    val numOfImages = 5
    var imageNum by remember { mutableStateOf(1) }
    var imageResource = when (imageNum) {
        1 -> R.drawable.thebatman
        2 -> R.drawable.bvsposter
        3 -> R.drawable.infinitywaraposter
        4 -> R.drawable.endgameposter
        5 -> R.drawable.wandavisionposter
        else -> R.drawable.thebatman
    }

    var imageDescription = when (imageNum){
        1 -> R.string.theBatman
        2 -> R.string.bvs
        3 -> R.string.infinityWar
        4 -> R.string.endgame
        5 -> R.string.wandavision
        else -> R.string.theBatman
    }

    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painterResource(imageResource),
            contentDescription = "",
            modifier = Modifier.width(500.dp).height(500.dp),
            contentScale = ContentScale.Fit,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(imageDescription),
            modifier = Modifier.width(400.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {imageNum = changeImageNum(false, imageNum, numOfImages) }){
                Text(stringResource(R.string.back))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {imageNum = changeImageNum(true, imageNum, numOfImages)}){
                Text(stringResource(R.string.next))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = inputText,
            onValueChange = {inputText = it},
            label = {Text(stringResource(R.string.image_num))},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    imageNum = changeImageNumFromText(inputText, numOfImages)

                }

            ),
            singleLine = true,
        )
    }
}

private fun changeImageNumFromText(imageNum: String, numOfImages: Int): Int{
    val num = imageNum.toIntOrNull()
    if(num != null && num > 0 && num <= numOfImages){
        return num
    }
    else {
        return 1
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
