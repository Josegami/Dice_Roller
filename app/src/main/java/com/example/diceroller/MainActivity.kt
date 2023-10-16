package com.example.diceroller

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme
import com.exaple.diceroller.R
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    var count by remember { mutableStateOf(0) }
    var previousResult by remember { mutableStateOf(0) }
    DiceWithButtonAndImage(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
        count = count,
        onRoll = { newResult ->
            count++
            previousResult = newResult
        }
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier, count: Int, onRoll: (Int)->Unit){
    var result by remember {
        mutableStateOf(1)
    }
    var previousResult by remember {
        mutableStateOf(0)
    }
    val imageResource = when(result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val newResult = (1..6).random()
                previousResult = result
                result = newResult
                onRoll(newResult)
            }
        ) {
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }
        Row() {
            Text(
                text = stringResource(R.string.contador),
                fontSize = 18.sp,
                color = Color(0xFF757575),
            )
            Text(
                text = count.toString(),
                fontSize = 18.sp,
                color = Color(0xFF757575)
            )
        }
        Row() {
            Text(
                text = stringResource(R.string.ulti_tir),
                fontSize = 18.sp,
                color = Color(0xFF757575),
            )
            Text(
                text = previousResult.toString(),
                fontSize = 18.sp,
                color = Color(0xFF757575),
            )
        }
    }
}