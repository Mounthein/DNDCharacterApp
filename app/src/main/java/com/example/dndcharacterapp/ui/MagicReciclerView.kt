package com.example.dndcharacterapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.spell.Spell
import com.example.dndcharacterapp.models.spell.Spells
import com.example.dndcharacterapp.ui.ui.theme.DNDCharacterAppTheme
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.ui.ui.theme.MagicActivity

class MagicReciclerView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var magics: Spells? = CrudApi().getSpellList()
        setContent {
            DNDCharacterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column (modifier = Modifier.fillMaxSize()) {
                        var filtre = remember { mutableStateOf(TextFieldValue())}
                        Text(text = "Filtre")
                        TextField(value = filtre.value, onValueChange = {newFilter -> filtre.value = newFilter})
                        Spacer(modifier = Modifier
                            .height(15.dp)
                            .fillMaxWidth())

                        if (filtre.value.text.isNotEmpty()){
                            var filtrats = magics!!.filter { it.name.contains(filtre.value.text) }
                            LazyColumn (contentPadding = PaddingValues(10.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)){
                                items(filtrats){
                                    magicCard(spell = it, modifier = Modifier.fillMaxWidth()) {
                                        val intent =
                                            Intent(this@MagicReciclerView, MagicActivity::class.java)
                                        intent.putExtra("spell", it.id)
                                        startActivity(intent)
                                    }

                                }

                            }
                        } else {
                            LazyColumn (contentPadding = PaddingValues(10.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)){
                                items(magics!!){
                                    magicCard(spell = it, modifier = Modifier.fillMaxWidth()) {
                                        val intent =
                                            Intent(this@MagicReciclerView, MagicActivity::class.java)
                                        intent.putExtra("spell", it.id)
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
fun magicCard(spell: Spell, modifier: Modifier, click: () -> Unit){
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable {
                click()
            })
    {
        Column (modifier = Modifier
            .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.Start)
        {
            Text(text = spell.name,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center)
            Box (modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(color = colorResource(id = R.color.white))
                .clip(shape = RoundedCornerShape(topStart = 15.dp)))
            {
                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 6.dp),
                    horizontalAlignment = Alignment.Start) {
                    Text(text = "Level: " + spell.level.toString())
                    Text(text = "Casting: " + spell.castingTime)
                    Text(text = "School: " + spell.from.name)
                    Text(text = "Concentation: " + spell.concentration.toString())
                    Text(text = "Range: " + spell.range.toString())
                }
            }
        }
    }
}
@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DNDCharacterAppTheme {
        Greeting2("Android")
    }
}