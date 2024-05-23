package com.example.dndcharacterapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.user.Message
import com.example.dndcharacterapp.models.user.User
import com.example.dndcharacterapp.models.user.apiUser
import com.example.dndcharacterapp.realm.MainViewModel
import com.example.dndcharacterapp.realm.RealmApp
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme
import io.realm.kotlin.UpdatePolicy

class ConfigurationActivity : ComponentActivity() {
    private val viewModel: ConfigViewModel by viewModels()
    private val viewModelCharacter: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val x = CrudApi().getUserOk("663bb77966105878580551c7", "string")
        setContent {
            DNDCharacterAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val user by viewModel.user.collectAsState()
                    val character by viewModelCharacter.characters.collectAsState()
                    val isLogued = remember { mutableStateOf(false) }
                    Column(modifier = Modifier.fillMaxSize()) {
                        if (user.isNullOrEmpty()) {
                            LoginSelector(isLogued)
                        } else {
                            isLogued.value = true
                            DatabaseSelector(viewModelCharacter, user)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginMenu(register: Boolean, logedin: MutableState<Boolean>, returner: MutableIntState) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    var cont = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentSize()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

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
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            if (register) {
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
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (!register && user.isNotEmpty() && pass.isNotEmpty() || register && user.isNotEmpty() && pass.isNotEmpty() && pass2.isNotEmpty()) {
                            var missatge: Message? = null
                            if (!register) {
                                missatge = CrudApi().getUserOk(user, pass)
                            } else {
                                if (pass.equals(pass2)) {
                                    var usuari: apiUser = apiUser(user, pass)

                                    missatge = CrudApi().postUserOk(usuari)
                                }
                            }

                            var test1 = missatge?.Message == "Login Incorrect"
                            var test2 = missatge?.Message.equals("User alredy exists")
                            var test3 = missatge == null
                            if (test1 || test2 || test3) {
                                if (missatge != null) {
                                    if (missatge.Message.equals("User already exists")) {
                                        Toast.makeText(
                                            cont,
                                            "Error with the username, try another",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else if (missatge.Message.equals("Login Incorrect")) {
                                        Toast.makeText(
                                            cont, "Error with user/password", Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        cont,
                                        "Something looks wrong, try again later",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                if (missatge!!.Message == "Login Correct") {
                                    var us: User = User()
                                    us.username = user
                                    us.password = pass
                                    val realm = RealmApp.realm
                                    realm.writeBlocking {
                                        copyToRealm(us, updatePolicy = UpdatePolicy.ALL)
                                    }
                                    Toast.makeText(cont, "Login Correct", Toast.LENGTH_LONG).show()
                                    logedin.value = true
                                } else {
                                    Toast.makeText(cont, "Register Correct", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                ) {
                    Text(
                        text = "Send",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        textAlign = TextAlign.Center,
                    )
                }

                Button(
                    onClick = {
                        returner.value = 0
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                ) {
                    Text(
                        text = "Return",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun LoginSelector(logedin: MutableState<Boolean>) {
    val what = remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (what.intValue == 0) {
            LoginButton(MaterialTheme.shapes.large, "Login") { what.intValue = 1 }
            LoginButton(MaterialTheme.shapes.large, "Register") { what.intValue = 2 }
        } else if (what.intValue == 1) {
            LoginMenu(register = false, logedin = logedin, returner = what)
        } else {
            LoginMenu(register = true, logedin = logedin, returner = what)
        }
    }
}

@Composable
fun LoginButton(shape: Shape, text: String, click: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = click),
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
fun DatabaseSelector(viewModel: MainViewModel, user: List<User>?) {
    val what = remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (what.intValue == 0) {
            InsertDatabaseToApi(MaterialTheme.shapes.large, "Save to Api") {
                what.intValue = 1
            }
            LoadDatabaseButton(MaterialTheme.shapes.large, "Load from Api") {
                what.intValue = 2
            }
            LoadDatabaseButton(MaterialTheme.shapes.large, "Example Character") {
                what.intValue = 3
            }
        } else if (what.intValue == 1) {
            DatabaseMenu("insert", returner = what, viewModel, user)
        } else if (what.intValue == 2) {
            DatabaseMenu("load", returner = what, viewModel, user)
        } else if (what.intValue == 3) {
            DatabaseMenu("example", returner = what, viewModel, null)
        }
    }
}

@Composable
fun DatabaseMenu(
    loadInsert: String, returner: MutableIntState, viewModel: MainViewModel, user: List<User>?
) {
    val crudApi = CrudApi()
    val characterRealm by viewModel.characters.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentSize()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (loadInsert.equals("load")) {
                if (!user.isNullOrEmpty()) {
                    Text(text = "Vols carregar els characters de la api?")
                    val logedUser = user.first()
                    val charactersLoadApi =
                        logedUser.let { crudApi.getCharacterByUserName(it.username) }
                    Button(
                        onClick = {
                            if (charactersLoadApi != null) {
//                            Log.e("CargarCharactersAPI", "Recoge los characters")
                                charactersLoadApi.forEach {
                                    viewModel.insertNewCharacterToRealm(it)
                                }
                                Toast.makeText(
                                    context,
                                    "Carregat character de l'usuari -> ${logedUser.username}",
                                    Toast.LENGTH_LONG
                                ).show()
                                returner.value = 0
                            } else {
                                Toast.makeText(
                                    context,
                                    "No hi ha characters amb aquest usuari",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .padding(top = 8.dp),
                    ) {
                        Text(
                            text = "Load",
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            textAlign = TextAlign.Center,
                        )
                    }
                } else {
                    Toast.makeText(
                        context, "Fes un LogIn abans de carregar characters", Toast.LENGTH_LONG
                    ).show()
                    returner.value = 0
                }
            } else if (loadInsert.equals("insert")) {
                if (!user.isNullOrEmpty()) {
                    Text(text = "Vols insertar els characters de Realm a la API?")
                    Button(
                        onClick = {
                            characterRealm.forEach {
                                crudApi.postCharacter(viewModel.convertRealmCharacterToNormal(it))
                                Toast.makeText(
                                    context,
                                    "Heu insertat els characters a la API",
                                    Toast.LENGTH_LONG
                                ).show()
                                returner.value = 0
                            }

                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .padding(top = 8.dp),
                    ) {
                        Text(
                            text = "Insert",
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            textAlign = TextAlign.Center,
                        )
                    }
                } else {
                    Toast.makeText(
                        context, "Fes un LogIn abans de carregar characters", Toast.LENGTH_LONG
                    ).show()
                    returner.value = 0
                }
            } else if (loadInsert.equals("example")) {
                Text(text = "Vols insertar un character d'exemple?")
                Button(
                    onClick = {
                        viewModel.createSampleEntriesCharacter()
                        Toast.makeText(
                            context, "Has insertat un character d'exemple", Toast.LENGTH_LONG
                        ).show()
                        returner.value = 0
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp)
                        .padding(top = 8.dp),
                ) {
                    Text(
                        text = "Insert",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}


@Composable
fun InsertDatabaseToApi(shape: Shape, text: String, click: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = click),
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
fun LoadDatabaseButton(shape: Shape, text: String, click: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = click),
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
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun GreetingPreview7() {
    DNDCharacterAppTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            val what = remember { mutableIntStateOf(0) }
            val isLogued = remember { mutableStateOf(false) }
            //LoginSelector(logedin = isLogued)
            LoginMenu(true, isLogued, what)
        }

    }
}