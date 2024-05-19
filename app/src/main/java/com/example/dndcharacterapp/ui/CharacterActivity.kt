package com.example.dndcharacterapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.models.characterRealm.CharacterRealm
import com.example.dndcharacterapp.realm.MainViewModel
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class CharacterActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme(darkTheme = false, dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val character by viewModel.characters.collectAsState()
                    val characterImportado = intent.getStringExtra("character")
                    val filtrats = character.filter { it.idString == characterImportado }

                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LazyColumn(
                                contentPadding = PaddingValues(10.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)
                            ) {
                                items(filtrats) {
                                    HeaderCharacter(name = it.name!!)
                                    BodyCharacter(character = it)
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
fun HeaderCharacter(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .height(60.dp)
            .border(3.dp, color = colorResource(id = R.color.red)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Cursive,
        )
    }
}

@Composable
fun BodyCharacter(character: CharacterRealm) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {
        TextBoxCharacter(title = "Level", content = character.level.toString())
        TextBoxCharacter(title = "Inspiration", content = character.inspiration.toString())
        //if (character.background != null) {
        character.background?.let { TextBoxCharacter(title = "background", content = it) }
        //}
        character.race?.name?.let { TextBoxCharacter(title = "race", content = it) }
        character.alignment?.name?.let { TextBoxCharacter(title = "alignment", content = it) }
        TextBoxCharacter(
            title = "hitPointsMaximum", content = character.hitPoints?.maximum.toString()
        )
        TextBoxCharacter(
            title = "hitPointsCurrent", content = character.hitPoints?.current.toString()
        )
        TextBoxCharacter(
            title = "hitPointsTemporary" , content = character.hitPoints?.temporary.toString()
        )
        character.hit_die?.get(0)?.type?.let {
            TextBoxCharacter(
                title = "hit_dieType", content = it
            )
        }
        TextBoxCharacter(
            title = "hit_dieQuantity", content = character.hit_die?.get(0)?.quantity.toString()
        )
        TextBoxCharacter(
            title = "death_savesSuccess", content = character.death_saves?.success.toString()
        )
        TextBoxCharacter(
            title = "death_savesFailure", content = character.death_saves?.failures.toString()
        )
        TextBoxCharacter(
            title = "temporary_HitPoints", content = character.temporary_HitPoints.toString()
        )
        TextBoxCharacter(title = "exhaustion", content = character.exhaustion.toString())
        character.armor_Class?.name?.let {
            TextBoxCharacter(
                title = "armor_ClassName", content = it
            )
        }
        character.armor_Class?.type?.let {
            TextBoxCharacter(
                title = "armor_ClassType", content = it
            )
        }
        TextBoxCharacter(
            title = "armor_ClassValue", content = character.armor_Class?.value.toString()
        )
        character.classes?.get(0)?.name?.let { TextBoxCharacter(title = "classes", content = it) }
        character.classes?.get(0)?.subclass?.let {
            TextBoxCharacter(
                title = "classes", content = it
            )
        }
        TextBoxCharacter(
            title = "experience_Points", content = character.experience_Points.toString()
        )

        //if(character.stats?.get(0)?.name != null){
        character.stats?.get(0)?.name?.let { TextBoxCharacter(title = "statsName", content = it) }
        //}
        //if(character.stats?.get(0)?.value != null){
        TextBoxCharacter(
            title = "statsValue", content = character.stats?.get(0)?.value.toString()
        )
        //}

        //if(character.skill_proficiencies?.get(0)?.name != null){
        character.skill_proficiencies?.get(0)?.name?.let {
            TextBoxCharacter(
                title = "skill_proficienciesName", content = it
            )
        }
        //}

        //if(character.skill_proficiencies?.get(0)?.bonus != null){
        TextBoxCharacter(
            title = "skill_proficienciesBonus",
            content = character.skill_proficiencies?.get(0)?.bonus.toString()
        )
        //}

        //if(character.languages?.get(0)?.name != null){
        character.languages?.get(0)?.name?.let {
            TextBoxCharacter(
                title = "languagesName", content = it
            )
        }
        //}
        //if(character.languages?.get(0)?.type != null){
        character.languages?.get(0)?.type?.let {
            TextBoxCharacter(
                title = "languagesType", content = it
            )
        }
        //}
        character.other_proficiencies?.get(0)?.name?.let {
            TextBoxCharacter(
                title = "other_proficienciesName", content = it
            )
        }
        character.other_proficiencies?.get(0)?.type?.let {
            TextBoxCharacter(
                title = "other_proficienciesType", content = it
            )
        }
        character.equipment?.get(0)?.name?.let {
            TextBoxCharacter(
                title = "equipmentName", content = it
            )
        }
        character.equipment?.get(0)?.equipment_category?.let {
            TextBoxCharacter(
                title = "equipmentCategory", content = it
            )
        }
        character.coin_pouch?.get(0)?.name?.let {
            TextBoxCharacter(
                title = "coin_pouch", content = it
            )
        }
        character.features?.get(0)
            ?.let { it.name?.let { it1 -> TextBoxCharacter(title = "features", content = it1) } }
        character.traits?.get(0)
            ?.let { it.name?.let { it1 -> TextBoxCharacter(title = "traits", content = it1) } }
        //if (character.spell_abilities != null) {
        TextBoxCharacter(
            title = "spell_abilitiesSpellcastingAbility",
            content = character.spell_abilities?.get(0)?.spellcasting_ability.toString()
        )
        TextBoxCharacter(
            title = "spell_abilitiesSpellSaveDC",
            content = character.spell_abilities?.get(0)?.spell_save_dc.toString()
        )
        TextBoxCharacter(
            title = "spell_abilitiesSpellAttackBonus",
            content = character.spell_abilities?.get(0)?.spell_attack_bonus.toString()
        )

        //}
        character.prepared_spells?.get(0)?.let {
            it.name?.let { it1 ->
                TextBoxCharacter(
                    title = "prepared_spells", content = it1
                )
            }
        }
        character.known_spells?.get(0)?.let {
            it.name?.let { it1 ->
                TextBoxCharacter(
                    title = "known_spells", content = it1
                )
            }
        }
        TextBoxCharacter(title = "passive_wisdom", content = character.passive_wisdom.toString())
        TextBoxCharacter(title = "initiative", content = character.initiative.toString())
        TextBoxCharacter(title = "speed", content = character.speed.toString())
        TextBoxCharacter(
            title = "proficiency_bonus", content = character.proficiency_bonus.toString()
        )
        TextBoxCharacter(title = "saving_throws", content = character.saving_throws)
        character.personality_traits?.let {
            TextBoxCharacter(
                title = "personality_traits", content = it
            )
        }
        character.ideals?.let { TextBoxCharacter(title = "ideals", content = it) }
        character.bonds?.let { TextBoxCharacter(title = "bonds", content = it) }
        character.flaws?.let { TextBoxCharacter(title = "flaws", content = it) }
        character.character_appearance?.let {
            TextBoxCharacter(
                title = "character_appearance", content = it
            )
        }
        character.character_backstory?.let {
            TextBoxCharacter(
                title = "character_backstory", content = it
            )
        }
        character.allies_organizations?.let {
            TextBoxCharacter(
                title = "allies_organizations", content = it
            )
        }
        character.symbol?.let { TextBoxCharacter(title = "symbol", content = it) }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
    }
}

@Composable
fun TextBoxCharacter(title: String, content: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), contentAlignment = Alignment.CenterStart
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
                    .padding()
                    .border(BorderStroke(2.dp, colorResource(id = R.color.black)))
                    .clip(MaterialTheme.shapes.medium)
                    .background(color = colorResource(id = R.color.darkbrown)),
                contentAlignment = Alignment.Center,
            ) {

                Text(
                    text = title, fontFamily = FontFamily.Cursive, fontSize = 20.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = content,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun TextBoxCharacter(title: String, content: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), contentAlignment = Alignment.CenterStart
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
                    .padding()
                    .background(color = colorResource(id = R.color.darkbrown))
                    .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
                contentAlignment = Alignment.Center,
            ) {

                Text(
                    text = title, fontFamily = FontFamily.Cursive, fontSize = 20.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
                contentAlignment = Alignment.Center
            ) {
                var showContent: String = ""
                content.forEach {
                    showContent += "$it, "
                }
                showContent =
                    showContent.removeRange(showContent.length - 2, showContent.length - 1)
                Text(
                    text = showContent, fontFamily = FontFamily.Cursive, fontSize = 20.sp
                )
            }
        }
    }
}

@Composable

fun TitleTextCharacter(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .height(60.dp)
            .border(3.dp, color = colorResource(id = R.color.red)),
        contentAlignment = Alignment.Center
    ) {

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

@Composable
fun Greeting7(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    DNDCharacterAppTheme {
        Greeting7("Android")
    }
}