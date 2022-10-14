package com.hfad.temperatureconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    MainActivityContent()
                }
            }
        }
    }
}

@Composable
fun MainActivityContent(){
    val celsius = remember { mutableStateOf(0)}
    val newCelsius = remember { mutableStateOf("")}
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Header(R.drawable.sunrise, "sunrise image")
        EnterTemperature(temperature = newCelsius.value, changed = {newCelsius.value = it})
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ConvertButton {
                newCelsius.value.toIntOrNull()?.let {celsius.value = it}
            }
        }
        TemperatureText(celsius.value)
    }
    
}

@Composable
fun Header(image: Int, description: String) {
    Image(
        painter = painterResource(image), 
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop)
}

@Composable
fun TemperatureText(celsius: Int) {
    val fahr = (celsius.toDouble()*9/5)+32
    Text("$celsius Celsius is $fahr Fahrenheit")
}

@Composable
fun EnterTemperature(temperature: String, changed: (String) -> Unit) {
    TextField(
        value = temperature,
        label = { Text("Enter a temperature in Celsius")},
        onValueChange = changed,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ConvertButton(clicked: () -> Unit) {
    Button(onClick = clicked) {
        Text("Convert")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MaterialTheme {
        Surface {
            MainActivityContent()
        }
    }
}