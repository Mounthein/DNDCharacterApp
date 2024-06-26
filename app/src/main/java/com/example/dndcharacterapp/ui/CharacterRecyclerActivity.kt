package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.dndcharacterapp.models.characterRealm.CharacterRealm
import com.example.dndcharacterapp.realm.MainViewModel
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class CharacterRecyclerActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val character = CrudApi().getCharacterList()?.toList()
        setContent {
            DNDCharacterAppTheme(darkTheme = false, dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val character by viewModel.characters.collectAsState()
                    val context = LocalContext.current
                    Column(modifier = Modifier.fillMaxSize()) {
                        val filtre = remember { mutableStateOf(TextFieldValue()) }
                        Text(text = "Filter")
                        TextField(value = filtre.value,
                            onValueChange = { newFilter -> filtre.value = newFilter })
                        Spacer(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                        )
                        BotonIrInsertarCharacters()

                        if (filtre.value.text.isNotEmpty()) {
                            val filtrats =
                                character.filter { it.name!!.contains(filtre.value.text) }

                            LazyColumn(
                                contentPadding = PaddingValues(10.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)
                            ) {
                                items(filtrats) {
                                    CharacterCard(character = it) {
                                        val intent = Intent(
                                            this@CharacterRecyclerActivity,
                                            CharacterActivity::class.java
                                        )
                                        intent.putExtra("character", it.idString)
                                        startActivity(intent)
                                    }

                                }

                            }
                        } else {
                            LazyColumn(
                                contentPadding = PaddingValues(10.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)
                            ) {
                                items(character) {
                                    CharacterCard(character = it) {
                                        val intent = Intent(
                                            this@CharacterRecyclerActivity,
                                            CharacterActivity::class.java
                                        )
                                        Log.e("Character", it.toString())
                                        intent.putExtra("character", it.idString)
                                        startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CharacterCard(character: CharacterRealm, click: () -> Unit) {
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ), modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .clickable { click() }
        .border(
            BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface), MaterialTheme.shapes.medium
        )) {
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Name: " + character.name,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Level: " + character.level,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Race: " + character.race!!.name,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Class: " + (character.classes?.get(0)?.name),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun BotonIrInsertarCharacters() {
    val context = LocalContext.current
    val intent = Intent(context, CharacterInsertarActivity::class.java)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Button(
                onClick = {
                    ContextCompat.startActivity(context, intent, null)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Insert Character", color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    DNDCharacterAppTheme {
        Greeting5("Android")
    }
}