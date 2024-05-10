package com.example.dndcharacterapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.realm.MainViewModel
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class ConfigurationActivity : ComponentActivity() {
    private val viewModel: ConfigViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val x = CrudApi().getUserOk("663bb77966105878580551c7", "string")
        setContent {
            DNDCharacterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    val user by viewModel.user.collectAsState()
                    val isLogued = remember { mutableStateOf(true) }
                    Column(modifier = Modifier.fillMaxSize()) {
                        if (user.isNullOrEmpty()) {
                            LoginSelector()
                        } else {
                            isLogued.value = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginMenu(register: Boolean, logedin: Boolean) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentSize()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,) {

            TextField(
                placeholder = { Text(text = "Your Username Here") },
                value = user,
                onValueChange = { user = it },
                label = {
                    Text(
                        text = "Username",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                },
                textStyle = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 30.dp)
                    .clip(MaterialTheme.shapes.small)
            )


            TextField(
                placeholder = { Text(text = "Your Password Here") },
                value = pass,
                onValueChange = { pass = it },
                label = {
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                },
                textStyle = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 30.dp)
                    .clip(MaterialTheme.shapes.small),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            if (register){
                TextField(
                    placeholder = { Text(text = "Your Password Here") },
                    value = pass2,
                    onValueChange = { pass2 = it },
                    label = {
                        Text(
                            text = "RePassword",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    },
                    textStyle = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 30.dp)
                        .clip(MaterialTheme.shapes.small),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                )
            }

            Button(
                onClick = {
                          if (!register){

                          }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                modifier = Modifier.width(150.dp).height(40.dp),
            )
            {
                Text(text = "Send",
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    textAlign = TextAlign.Center,
                )
            }
        }


    }
}

@Composable
fun LoginSelector() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        LoginButton(MaterialTheme.shapes.large, "Login")
        LoginButton(MaterialTheme.shapes.large, "Register")
    }
}

@Composable
fun LoginButton(shape: Shape, text: String) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun Greeting6(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun GreetingPreview7() {
    DNDCharacterAppTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer,
        ) {
            LoginMenu(true, false)
        }

    }
}