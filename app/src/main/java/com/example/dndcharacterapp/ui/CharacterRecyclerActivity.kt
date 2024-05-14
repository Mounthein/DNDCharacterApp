package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.example.dndcharacterapp.models.character.Character
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class CharacterRecyclerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val character = CrudApi().getCharacterList()?.toList()
        setContent {
            DNDCharacterAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column (modifier = Modifier.fillMaxSize()) {
                        var filtre = remember { mutableStateOf(TextFieldValue())}
                        Text(text = "Filtre")
                        TextField(value = filtre.value, onValueChange = {newFilter -> filtre.value = newFilter})
                        Spacer(modifier = Modifier
                            .height(15.dp)
                            .fillMaxWidth())

                        if (filtre.value.text.isNotEmpty()){
                            var filtrats = character!!.filter { it.name!!.contains(filtre.value.text) }
                            LazyColumn (contentPadding = PaddingValues(10.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)){
                                items(filtrats){
                                    CharacterCard(character = it) {
                                        val intent =
                                            Intent(this@CharacterRecyclerActivity, MagicActivity::class.java)
                                        intent.putExtra("character", it._id.toString())
                                        startActivity(intent)
                                    }

                                }

                            }
                        } else {
                            LazyColumn (contentPadding = PaddingValues(10.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)){
                                items(character!!){
                                    CharacterCard(character = it) {
                                        val intent =
                                            Intent(this@CharacterRecyclerActivity, MagicActivity::class.java)
                                        intent.putExtra("character", it._id.toString())
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
fun CharacterCardList(character: List<Character>) {
    var llista: ArrayList<Any> = ArrayList()
    val context = LocalContext.current
    llista.addAll(character)
    LazyColumn(
        contentPadding = PaddingValues(10.dp), modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        items(llista) {

        }
    }
}

@Composable
fun CharacterCard(character: Character, click: () -> Unit) {
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onSurfaceVariant
    ), modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .clickable { click() }) {
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Name: " + character.name,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Level: " + character.level,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
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