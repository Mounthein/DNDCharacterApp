package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.spell.Spell
import com.example.dndcharacterapp.models.spell.Spells
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

var collected = false
class MagicReciclerView : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collected = false
        //var magics: Spells? = CrudApi().getSpellList()
        setContent {
            DNDCharacterAppTheme (darkTheme = false) {
                var magics by remember { mutableStateOf<Spells?>(null) }
                magics = Spells()

                if (!collected){
                    LaunchedEffect(true) {
                        magics = fetchData()
                    }
                }

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
                            CardList(magicList = filtrats)
                        } else {
                            CardList(magicList = magics!!.toList())
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun CardList(magicList: List<Spell>){
    val context = LocalContext.current
    LazyColumn(contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        items(magicList){
            MagicCard(spell = it) {
                val intent = Intent(context, MagicActivity::class.java)
                intent.putExtra("spell", it.id)
                context.startActivity(intent)
            }
        }
    }
}

suspend fun fetchData(): Spells? {
    collected = true
    return withContext(Dispatchers.IO) {
        CrudApi().getSpellList()
    }
}

@Composable
fun MagicCard(spell: Spell, click: () -> Unit){
    Card(colors = CardDefaults.cardColors(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.primaryContainer,

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
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = spell.name,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface)

                Text(text = "Level: " + spell.level,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface)
            }


            Box (modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(topStart = 15.dp))
                .padding(15.dp)
                .background(color = colorResource(id = R.color.white))
                )
            {
                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 6.dp),
                    horizontalAlignment = Alignment.Start) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Level: " + spell.level.toString(), color = MaterialTheme.colorScheme.inverseSurface)
                    Text(text = "Casting: " + spell.castingTime, color = MaterialTheme.colorScheme.inverseSurface)
                    Text(text = "School: " + spell.from.name, color = MaterialTheme.colorScheme.inverseSurface)
                    Text(text = "Concentation: " + spell.concentration.toString(), color = MaterialTheme.colorScheme.inverseSurface)
                    Text(text = "Range: " + spell.range, color = MaterialTheme.colorScheme.inverseSurface)
                    Spacer(modifier = Modifier.height(20.dp))
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

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    DNDCharacterAppTheme (darkTheme = false) {
        Surface (modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer) {
            Text(text = "ñj",
                color = MaterialTheme.colorScheme.onSurface)
        }

    }
}