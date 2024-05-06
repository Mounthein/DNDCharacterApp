package com.example.dndcharacterapp.ui.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.spell.DamageAtCharacterLevel
import com.example.dndcharacterapp.models.spell.DamageSlotLevel
import com.example.dndcharacterapp.models.spell.HealAtSlotLevel
import com.example.dndcharacterapp.models.spell.Spell
import com.example.dndcharacterapp.ui.ui.theme.ui.theme.DNDCharacterAppTheme


class MagicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val spellid: String? = intent.getStringExtra("spell")
        setContent {
            DNDCharacterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box (modifier = Modifier.fillMaxSize()){
                        var spell: Spell? = spellid?.let { CrudApi().getSpell(it) }
                        Column(modifier = Modifier.fillMaxSize()) {
                            Header(name = spell!!.name)
                            Body(spell = spell!!)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Header(name: String){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)
        .height(60.dp)
        .border(3.dp, color = colorResource(id = R.color.red)),
        contentAlignment = Alignment.Center){
        Text(text = name,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Cursive,
        )
    }
}
@Composable
fun Body(spell: Spell){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {
        TextBox(title = "Level", content = spell.level.toString(),)
        TextBox(title = "Casting Time", content = spell.castingTime)
        TextBox(title = "School", content = spell.from.name)
        TextBox(title = "Duration", content = spell.duration)
        TextBox(title = "Range", content = spell.range)
        TextBox(title = "Components", content = spell.components)
        if (spell.material.isNotEmpty()) TextBox(title = "Material", content = spell.material)

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp))
        TitleText(title = "Description")
        SpellDescription(descr = spell.description)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp))

        // Canviant las data class per a que puguin ser null fan que aquesta part funciona
        // Problema solucionat, ja puc deixar de tenir una crisis mental
        if (spell.damageSpell != null){
            if (spell.damageSpell.damageSlotLevel != null){
                SpellDamageLevel(levelDamage = spell.damageSpell.damageSlotLevel)
            } else{
                spell.damageSpell.damageAtCharacterLevel?.let { DamageAtLevel(damageLevel = it) }
            }
        }

        if (spell.healAtSlotLevel != null){
            SpellHealLevel(spell.healAtSlotLevel)
        }
    }
}

@Composable
fun TextBox(title: String, content: String){
    Box (modifier = Modifier
        .fillMaxWidth()
        .height(80.dp),
        contentAlignment = Alignment.CenterStart)
    {
        Row (modifier = Modifier.fillMaxSize(),){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
                    .padding()
                    .background(color = colorResource(id = R.color.darkbrown))
                    .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
                contentAlignment = Alignment.Center,
            ) {

                Text(text = title,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp)
            }
            Box (modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
                contentAlignment = Alignment.Center) {
                Text(text = content,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun TextBox(title: String, content: List<String>){
    Box (modifier = Modifier
        .fillMaxWidth()
        .height(80.dp),
        contentAlignment = Alignment.CenterStart)
    {
        Row (modifier = Modifier.fillMaxSize(),){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
                    .padding()
                    .background(color = colorResource(id = R.color.darkbrown))
                    .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
                contentAlignment = Alignment.Center,
            ) {

                Text(text = title,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp)
            }
            Box (modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
                contentAlignment = Alignment.Center) {
                var showContent: String = ""
                content.forEach {
                    showContent += "$it, "
                }
                showContent = showContent.removeRange(showContent.length-2, showContent.length-1)
                Text(text = showContent,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun SpellDescription(descr: List<String>){
    LazyColumn(contentPadding = PaddingValues(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)){
        items(descr){
            Text(text = it,
                fontFamily = FontFamily.Cursive,)
        }
    }
}

@Composable
fun SpellDamageLevel(levelDamage: DamageSlotLevel){
    Column {
        TitleText(title = "Damage per spent slot")
        if (levelDamage.dmSlLvl1!!.isNotEmpty()) TextBox(title = "Level 1", content = levelDamage.dmSlLvl1)
        if (levelDamage.dmSlLvl2!!.isNotEmpty()) TextBox(title = "Level 2", content = levelDamage.dmSlLvl2)
        if (levelDamage.dmSlLvl3!!.isNotEmpty()) TextBox(title = "Level 3", content = levelDamage.dmSlLvl3)
        if (levelDamage.dmSlLvl4!!.isNotEmpty()) TextBox(title = "Level 4", content = levelDamage.dmSlLvl4)
        if (levelDamage.dmSlLvl5!!.isNotEmpty()) TextBox(title = "Level 5", content = levelDamage.dmSlLvl5)
        if (levelDamage.dmSlLvl6!!.isNotEmpty()) TextBox(title = "Level 6", content = levelDamage.dmSlLvl6)
        if (levelDamage.dmSlLvl7!!.isNotEmpty()) TextBox(title = "Level 7", content = levelDamage.dmSlLvl7)
        if (levelDamage.dmSlLvl8!!.isNotEmpty()) TextBox(title = "Level 8", content = levelDamage.dmSlLvl8)
        if (levelDamage.dmSlLvl9!!.isNotEmpty()) TextBox(title = "Level 9", content = levelDamage.dmSlLvl9)
    }
}

@Composable
fun DamageAtLevel(damageLevel: DamageAtCharacterLevel){
    Column {
        TitleText(title = "Damage at class level")
        if (damageLevel.dmChLcl1!!.isNotEmpty()) TextBox(title = "Level 1", content = damageLevel.dmChLcl1)
        if (damageLevel.dmChLcl5!!.isNotEmpty()) TextBox(title = "Level 5", content = damageLevel.dmChLcl5)
        if (damageLevel.dmChLcl11!!.isNotEmpty()) TextBox(title = "Level 11", content = damageLevel.dmChLcl11)
        if (damageLevel.dmChLcl17!!.isNotEmpty()) TextBox(title = "Level 17", content = damageLevel.dmChLcl17)
    }
}

@Composable
fun SpellHealLevel(healAtLevel: HealAtSlotLevel){
    Column {
        TitleText(title = "Heal per spent slot")
        if (healAtLevel.healAtSlotLevel1!!.isNotEmpty()) TextBox(title = "Level 1", content = healAtLevel.healAtSlotLevel1)
        if (healAtLevel.healAtSlotLevel2!!.isNotEmpty()) TextBox(title = "Level 2", content = healAtLevel.healAtSlotLevel2)
        if (healAtLevel.healAtSlotLevel3!!.isNotEmpty()) TextBox(title = "Level 3", content = healAtLevel.healAtSlotLevel3)
        if (healAtLevel.healAtSlotLevel4!!.isNotEmpty()) TextBox(title = "Level 4", content = healAtLevel.healAtSlotLevel4)
        if (healAtLevel.healAtSlotLevel5!!.isNotEmpty()) TextBox(title = "Level 5", content = healAtLevel.healAtSlotLevel5)
        if (healAtLevel.healAtSlotLevel6!!.isNotEmpty()) TextBox(title = "Level 6", content = healAtLevel.healAtSlotLevel6)
        if (healAtLevel.healAtSlotLevel7!!.isNotEmpty()) TextBox(title = "Level 7", content = healAtLevel.healAtSlotLevel7)
        if (healAtLevel.healAtSlotLevel8!!.isNotEmpty()) TextBox(title = "Level 8", content = healAtLevel.healAtSlotLevel8)
        if (healAtLevel.healAtSlotLevel9!!.isNotEmpty()) TextBox(title = "Level 9", content = healAtLevel.healAtSlotLevel9)
    }
}

@Composable

fun TitleText(title: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)
        .height(60.dp)
        .border(3.dp, color = colorResource(id = R.color.red)),
        contentAlignment = Alignment.Center){

        Text(
            text = title,
            style = TextStyle(
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
            ),
        )
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    DNDCharacterAppTheme {
        TitleText("Fireball")
    }
}