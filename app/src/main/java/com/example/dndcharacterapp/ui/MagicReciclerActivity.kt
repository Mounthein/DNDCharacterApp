package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.dndcharacterapp.models.spell.DamageAtCharacterLevel
import com.example.dndcharacterapp.models.spell.DamageSlotLevel
import com.example.dndcharacterapp.models.spell.HealAtSlotLevel
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
        verticalArrangement = Arrangement.spacedBy(25.dp)
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
            .clickable {
                click()
            }
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface), MaterialTheme.shapes.medium))
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

            CardContent(spell = spell)
        }
    }
}

@Composable
fun CardContent(spell: Spell){
    Box (modifier = Modifier
        .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), MaterialTheme.shapes.medium)
        .clip(MaterialTheme.shapes.medium)
        .background(color = colorResource(id = R.color.white))

    )
    {
        Row (Modifier.fillMaxSize()){
            Column (modifier = Modifier
                .fillMaxHeight()
                .padding(start = 6.dp),
                horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Casting: " + spell.castingTime, color = MaterialTheme.colorScheme.inverseSurface)
                Text(text = "School: " + spell.from.name, color = MaterialTheme.colorScheme.inverseSurface)
                Text(text = "Concentation: " + spell.concentration.toString(), color = MaterialTheme.colorScheme.inverseSurface)
                Text(text = "Range: " + spell.range, color = MaterialTheme.colorScheme.inverseSurface)
                Spacer(modifier = Modifier.height(20.dp))
            }
            Spacer(modifier = Modifier
                .padding(horizontal = 5.dp)
                .width(10.dp)
                .height(150.dp)
                .background(MaterialTheme.colorScheme.primary))

            Column (modifier = Modifier
                .height(140.dp)
                .padding(vertical = 10.dp)
                .verticalScroll(rememberScrollState()))
            {
                if (spell.damageSpell != null){
                    if (spell.damageSpell.damageSlotLevel != null){
                        SpellDamageCard(levelDamage = spell.damageSpell.damageSlotLevel)
                    } else{
                        spell.damageSpell.damageAtCharacterLevel?.let { DamageAtLevelCard(damageLevel = it) }
                    }
                }

                if (spell.healAtSlotLevel != null){
                    SpellHealCard(spell.healAtSlotLevel)
                }
            }

        }

    }
}

@Composable
fun SpellDamageCard(levelDamage: DamageSlotLevel){
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (levelDamage.dmSlLvl1!!.isNotEmpty()) Text(text = "Level 1: " + levelDamage.dmSlLvl1)
        if (levelDamage.dmSlLvl2!!.isNotEmpty()) Text(text = "Level 2: " + levelDamage.dmSlLvl2)
        if (levelDamage.dmSlLvl3!!.isNotEmpty()) Text(text = "Level 3: " + levelDamage.dmSlLvl3)
        if (levelDamage.dmSlLvl4!!.isNotEmpty()) Text(text = "Level 4: " + levelDamage.dmSlLvl4)
        if (levelDamage.dmSlLvl5!!.isNotEmpty()) Text(text = "Level 5: " + levelDamage.dmSlLvl5)
        if (levelDamage.dmSlLvl6!!.isNotEmpty()) Text(text = "Level 6: " + levelDamage.dmSlLvl6)
        if (levelDamage.dmSlLvl7!!.isNotEmpty()) Text(text = "Level 7: " + levelDamage.dmSlLvl7)
        if (levelDamage.dmSlLvl8!!.isNotEmpty()) Text(text = "Level 8: " + levelDamage.dmSlLvl8)
        if (levelDamage.dmSlLvl9!!.isNotEmpty()) Text(text = "Level 9: " + levelDamage.dmSlLvl9)
    }
}

@Composable
fun DamageAtLevelCard(damageLevel: DamageAtCharacterLevel){
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        if (damageLevel.dmChLcl1!!.isNotEmpty()) Text(text = "Level 1: " + damageLevel.dmChLcl1)
        if (damageLevel.dmChLcl5!!.isNotEmpty()) Text(text = "Level 5: " + damageLevel.dmChLcl5)
        if (damageLevel.dmChLcl11!!.isNotEmpty()) Text(text = "Level 11: " + damageLevel.dmChLcl11)
        if (damageLevel.dmChLcl17!!.isNotEmpty()) Text(text = "Level 17: " + damageLevel.dmChLcl17)
    }
}

@Composable
fun SpellHealCard(healAtLevel: HealAtSlotLevel){
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        if (healAtLevel.healAtSlotLevel1!!.isNotEmpty()) Text(text = "Level 1: " + healAtLevel.healAtSlotLevel1)
        if (healAtLevel.healAtSlotLevel2!!.isNotEmpty()) Text(text = "Level 2: " + healAtLevel.healAtSlotLevel2)
        if (healAtLevel.healAtSlotLevel3!!.isNotEmpty()) Text(text = "Level 3: " + healAtLevel.healAtSlotLevel3)
        if (healAtLevel.healAtSlotLevel4!!.isNotEmpty()) Text(text = "Level 4: " + healAtLevel.healAtSlotLevel4)
        if (healAtLevel.healAtSlotLevel5!!.isNotEmpty()) Text(text = "Level 5: " + healAtLevel.healAtSlotLevel5)
        if (healAtLevel.healAtSlotLevel6!!.isNotEmpty()) Text(text = "Level 6: " + healAtLevel.healAtSlotLevel6)
        if (healAtLevel.healAtSlotLevel7!!.isNotEmpty()) Text(text = "Level 7: " + healAtLevel.healAtSlotLevel7)
        if (healAtLevel.healAtSlotLevel8!!.isNotEmpty()) Text(text = "Level 8: " + healAtLevel.healAtSlotLevel8)
        if (healAtLevel.healAtSlotLevel9!!.isNotEmpty()) Text(text = "Level 9: " + healAtLevel.healAtSlotLevel9)
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 500)
@Composable
fun GreetingPreview() {
    DNDCharacterAppTheme (darkTheme = false) {
        Surface (modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            Card (colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.primaryContainer,

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp)
                    ) {
                    Column (modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                        horizontalAlignment = Alignment.Start) {
                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween)
                        {
                            Text(text = "Hola",
                                modifier = Modifier
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface)
                            Spacer(modifier = Modifier
                                .fillMaxHeight()
                                .width(13.dp)
                                .background(MaterialTheme.colorScheme.primary))
                            Text(text = "Level: ",
                                modifier = Modifier
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
            }
        }

    }
}