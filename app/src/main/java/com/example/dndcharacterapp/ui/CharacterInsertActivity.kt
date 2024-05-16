package com.example.dndcharacterapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.classes.ClassesItem
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.feature.Feature
import com.example.dndcharacterapp.models.language.Language
import com.example.dndcharacterapp.models.proficiency.Proficiency
import com.example.dndcharacterapp.models.race.Race
import com.example.dndcharacterapp.models.spell.Spell
import com.example.dndcharacterapp.models.trait.Trait
import com.example.dndcharacterapp.realm.MainViewModel
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class CharacterInsertarActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val character by viewModel.characters.collectAsState()

                    val races = CrudApi().getRaceList()?.toList()
                    val alignments = CrudApi().getAlignmentList()?.toList()
                    val classes = CrudApi().getClassesList()?.toList()
                    val languages = CrudApi().getLanguageList()?.toList()
                    val proficiencies = CrudApi().getProficiencyList()?.toList()
                    val equipment = CrudApi().getEquipmentList()?.toList()
                    val features = CrudApi().getFeatureList()?.toList()
                    val traits = CrudApi().getTraitList()?.toList()
                    val spells = CrudApi().getSpellList()?.toList()
                    if (races != null && alignments != null && classes != null && languages != null && proficiencies != null && equipment != null && features != null && traits != null && spells != null) {
                        MostrarComponentes(
                            races,
                            alignments,
                            classes,
                            languages,
                            proficiencies,
                            equipment,
                            features,
                            traits,
                            spells
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarComponentes(
    racesList: List<Race>?,
    alignmentsList: List<com.example.dndcharacterapp.models.alignment.Alignment>?,
    classeslist: List<ClassesItem>?,
    languagesList: List<Language>?,
    proficienciesList: List<Proficiency>?,
    equipmentList: List<Equipment>?,
    featuresList: List<Feature>?,
    traitsList: List<Trait>?,
    spellList: List<Spell>?,
) {
    val context = LocalContext.current
    var expandedClasses by remember { mutableStateOf(false) }
    var expandedOtherProficiency by remember { mutableStateOf(false) }
    var expandedEquipment by remember { mutableStateOf(false) }
    var expandedLanguages by remember { mutableStateOf(false) }
    var expandedFeatures by remember { mutableStateOf(false) }
    var expandedTraits by remember { mutableStateOf(false) }
    var expandedPreparedSpells by remember { mutableStateOf(false) }
    var expandedKnownSpells by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        //Name
        val name = Mostrar1TextField(textoMostrar = "Name")
        //Toast.makeText(LocalContext.current, name, Toast.LENGTH_SHORT).show()
        //Level
        val level = Mostrar1TextField(textoMostrar = "Level")
        //Inspiration Background
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Inspiration")
            val inspirationRemember = remember { mutableStateOf(true) }
            Checkbox(checked = inspirationRemember.value,
                onCheckedChange = { inspirationRemember.value = it })
            val inspiration = inspirationRemember.value
            Spacer(modifier = Modifier.width(16.dp))
        }
        val background = Mostrar1TextField(textoMostrar = "Background")

        //Race
        val racesName: MutableList<String> = mutableListOf()
        racesList!!.forEach {
            racesName.add(it.name)
        }
        val race = MostrarDropDowns(list1 = racesName, "Race")

        //Alignment
        val alignmentsName: MutableList<String> = mutableListOf()
        alignmentsList!!.forEach {
            alignmentsName.add(it.name)
        }

        val alignment = MostrarDropDowns(list1 = alignmentsName, "Alignment")

        //HitPoint HitDie
        Text(text = "HitPoints")
        val inputvalueHitPointsMaximum = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueHitPointsCurrent = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueHitPointsTemporary = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitPointsMaximum.value,
                    onValueChange = { inputvalueHitPointsMaximum.value = it },
                    label = { Text("Maximum") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitPointsCurrent.value,
                    { inputvalueHitPointsCurrent.value = it },
                    label = { Text("Current") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitPointsTemporary.value,
                    { inputvalueHitPointsTemporary.value = it },
                    label = { Text("Temporary") })
            }
        }
        val HitPointsMaximum = inputvalueHitPointsMaximum.value.text
        val HitPointsCurrent = inputvalueHitPointsCurrent.value.text
        val HitPointsTemporary = inputvalueHitPointsTemporary.value.text

        Text(text = "HitDie")
        val inputvalueHitDieType = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueHitDieQuantity = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitDieType.value,
                    onValueChange = { inputvalueHitDieType.value = it },
                    label = { Text("Type") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitDieQuantity.value,
                    onValueChange = { inputvalueHitDieQuantity.value = it },
                    label = { Text("Quantity") })
            }
        }
        val HitDieType = inputvalueHitDieType.value.text
        val HitDieQuantity = inputvalueHitDieQuantity.value.text

        //DeathSaves ArmorClass
        Text(text = "DeathSaves")
        val inputvalueDeathSavesSuccess = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueDeathSavesFailures = remember { mutableStateOf(TextFieldValue()) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueDeathSavesSuccess.value,
                    onValueChange = { inputvalueDeathSavesSuccess.value = it },
                    label = { Text("Success") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueDeathSavesFailures.value,
                    onValueChange = { inputvalueDeathSavesFailures.value = it },
                    label = { Text("Failures") })
            }
        }
        val DeathSavesSuccess = inputvalueDeathSavesSuccess.value.text
        val DeathSavesFailures = inputvalueDeathSavesFailures.value.text

        Text(text = "ArmorClass")
        val inputvalueArmorClassName = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueArmorClassType = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueArmorClassValue = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueArmorClassName.value,
                    onValueChange = { inputvalueArmorClassName.value = it },
                    label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueArmorClassType.value,
                    onValueChange = { inputvalueArmorClassType.value = it },
                    label = { Text("Type") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueArmorClassValue.value,
                    onValueChange = { inputvalueArmorClassValue.value = it },
                    label = { Text("Value") })
            }
        }

        val ArmorClassName = inputvalueArmorClassName.value.text
        val ArmorClassType = inputvalueArmorClassType.value.text
        val ArmorClassValue = inputvalueArmorClassValue.value.text

        //Classes
        val classesName: MutableList<String> = mutableListOf()
        classeslist!!.forEach {
            classesName.add(it.name)
        }
        val classes = MostrarDropDowns(list1 = classesName, textoMostrar = "Classes")

        // Texto "Stats"
        Text(
            text = "Stats", modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
        )
        val inputvalueStatsName = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueStatsValue = remember { mutableStateOf(TextFieldValue()) }
        // TextFields para "Name" y "Value"
        Row(Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueStatsName.value,
                    onValueChange = { inputvalueStatsName.value = it },
                    label = { Text("Name") })
            }

            Spacer(modifier = Modifier.width(4.dp))

            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueStatsValue.value,
                    onValueChange = { inputvalueStatsValue.value = it },
                    label = { Text("Value") })
            }
        }

        val StatsName = inputvalueStatsName.value.text
        val StatsValue = inputvalueStatsValue.value.text

        //SkillProficiencies
        Text(text = "SkillProficiencies")
        val inputvalueSkillProficienciesName = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueSkillProficienciesBonus = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSkillProficienciesName.value,
                    onValueChange = { inputvalueSkillProficienciesName.value = it },
                    label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSkillProficienciesBonus.value,
                    onValueChange = { inputvalueSkillProficienciesBonus.value = it },
                    label = { Text("Bonus") })
            }
        }

        val SkillProficienciesName = inputvalueSkillProficienciesName.value.text
        val SkillProficienciesBonus = inputvalueSkillProficienciesBonus.value.text

        //Languages
        val languagesName: MutableList<String> = mutableListOf()
        languagesList!!.forEach {
            languagesName.add(it.name)
        }
        val languages = MostrarDropDowns(list1 = languagesName, textoMostrar = "Languages")

        //OtherProficiencies
        val otherProficienciesName: MutableList<String> = mutableListOf()
        proficienciesList!!.forEach {
            otherProficienciesName.add(it.name)
        }
        val otherProficiencies = MostrarDropDowns(list1 = otherProficienciesName, textoMostrar = "Other Proficiencies")

        //Equipment
        val equipmentName: MutableList<String> = mutableListOf()
        equipmentList!!.forEach {
            equipmentName.add(it.name)
        }

        val equipment = MostrarDropDowns(list1 = equipmentName, textoMostrar = "Equipment")

        //CoinPouch
        Text(text = "CoinPouch")
        val inputvalueCoinPouchName = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueCoinPouchQuantity = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueCoinPouchName.value,
                    onValueChange = { inputvalueCoinPouchName.value = it },
                    label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueCoinPouchQuantity.value,
                    onValueChange = { inputvalueCoinPouchQuantity.value = it },
                    label = { Text("Quantity") })
            }
        }

        val coinPouchName = inputvalueCoinPouchName.value.text
        val coinPouchQuantity = inputvalueCoinPouchQuantity.value.text

        //Features
        val featuresName: MutableList<String> = mutableListOf()
        featuresList!!.forEach {
            featuresName.add(it.name)
        }
        val features = MostrarDropDowns(list1 = featuresName, textoMostrar = "Features")

        //Traits SpellAbilities
        Text(text = "Traits")
        var selectedTextTraits by remember { mutableStateOf(traitsList!![0].name) }
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                ExposedDropdownMenuBox(expanded = expandedTraits, onExpandedChange = {
                    expandedTraits = it
                }) {
                    TextField(value = selectedTextTraits,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTraits) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedTraits,
                        onDismissRequest = { expandedTraits = false }) {
                        traitsList!!.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item.name) }, onClick = {
                                selectedTextTraits = item.name
                                expandedTraits = false
                                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }

        Text(text = "SpellAbilities")
        val inputvalueSpellAbilitiesSpellcastingAbility =
            remember { mutableStateOf(TextFieldValue()) }
        val inputvalueSpellAbilitiesSpellSaveDC = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueSpellAbilitiesSpellAttackBonus = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSpellAbilitiesSpellcastingAbility.value,
                    onValueChange = { inputvalueSpellAbilitiesSpellcastingAbility.value = it },
                    label = { Text("SpellcastingAbility") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSpellAbilitiesSpellSaveDC.value,
                    onValueChange = { inputvalueSpellAbilitiesSpellSaveDC.value = it },
                    label = { Text("SpellSaveDC") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSpellAbilitiesSpellAttackBonus.value,
                    onValueChange = { inputvalueSpellAbilitiesSpellAttackBonus.value = it },
                    label = { Text("SpellAttackBonus") })
            }
        }

        //PreparedSpells KnownSpells
        Text(text = "PreparedSpells")
        var selectedTextPreparedSpells by remember { mutableStateOf(spellList!![0].name) }
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                ExposedDropdownMenuBox(expanded = expandedPreparedSpells, onExpandedChange = {
                    expandedPreparedSpells = it
                }) {
                    TextField(value = selectedTextPreparedSpells,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPreparedSpells) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(expanded = expandedPreparedSpells,
                        onDismissRequest = { expandedPreparedSpells = false }) {
                        spellList!!.forEach { item ->
                            DropdownMenuItem(text = {
                                Text(
                                    text = item.name, color = MaterialTheme.colorScheme.onBackground
                                )
                            }, onClick = {
                                selectedTextPreparedSpells = item.name
                                expandedPreparedSpells = false
                                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }
        Text(text = "KnownSpells")
        var selectedTextKnownSpells by remember { mutableStateOf(spellList!![0].name) }
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                ExposedDropdownMenuBox(expanded = expandedKnownSpells, onExpandedChange = {
                    expandedKnownSpells = it
                }) {
                    TextField(value = selectedTextKnownSpells,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedKnownSpells) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(expanded = expandedKnownSpells,
                        onDismissRequest = { expandedKnownSpells = false }) {
                        spellList!!.forEach { item ->
                            DropdownMenuItem(text = {
                                Text(
                                    text = item.name, color = MaterialTheme.colorScheme.onBackground
                                )
                            }, onClick = {
                                selectedTextKnownSpells = item.name
                                expandedKnownSpells = false
                                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }
        //SavingThrows
        Text(text = "SavingThrows")
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                var stringArray by rememberSaveable { mutableStateOf(mutableListOf("")) }

                Column {
                    stringArray.forEachIndexed { index, item ->
                        OutlinedTextField(value = item, onValueChange = { newValue ->
                            val newList = stringArray.toMutableList()
                            newList[index] = newValue
                            stringArray = newList
                        }, label = {
                            Text(
                                "Item $index", color = MaterialTheme.colorScheme.onBackground
                            )
                        }, colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                        )
                    }
                    Button(onClick = {
                        val newList = stringArray.toMutableList()
                        newList.add("")
                        stringArray = newList
                    }) {
                        Text("Afegir element", color = MaterialTheme.colorScheme.onBackground)
                    }
                    Button(onClick = {
                        val newList = stringArray.toMutableList()
                        newList.remove("")
                        stringArray = newList
                    }) {
                        Text("Eliminar element", color = MaterialTheme.colorScheme.onBackground)
                    }
                    //Con este código se almacena la información
                    Button(onClick = {
                        val stringArrayArray: Array<String> = stringArray.toTypedArray()
                        // Use the stringArrayArray variable as a String array
                        println(stringArrayArray.contentToString())
                    }) {
                        Text("Save to String array", color = MaterialTheme.colorScheme.onBackground)
                    }
                }
            }
        }

        //Exhaustion
        Mostrar1TextField(textoMostrar = "Exhaustion")

        //ExperienciePoints
        Mostrar1TextField(textoMostrar = "ExperienciePoints")

        //PassiveWisdom
        Mostrar1TextField(textoMostrar = "PassiveWisdom")

        //Initiative
        Mostrar1TextField(textoMostrar = "Initiative")

        //Speed
        Mostrar1TextField(textoMostrar = "Speed")

        //ProficiencyBonus
        Mostrar1TextField(textoMostrar = "ProficiencyBonus")

        //PersonalityTraits
        Mostrar1TextField(textoMostrar = "PersonalityTraits")

        //Ideals
        Mostrar1TextField(textoMostrar = "Ideals")

        //Bonds
        Mostrar1TextField(textoMostrar = "Bonds")

        //Claws
        Mostrar1TextField(textoMostrar = "Claws")

        //CharacterBackstory
        Mostrar1TextField(textoMostrar = "CharacterBackstory")

        //AlliesOrganizations
        Mostrar1TextField(textoMostrar = "AlliesOrganizations")

        //Symbol
        Mostrar1TextField(textoMostrar = "Symbol")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                //Almacenar todos los elementos
                Button(onClick = {
                    Toast.makeText(context, "Character Añadido", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Afegir Character", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

//Esto mostrará un textfield para que se escriba el string
@Composable
fun Mostrar1TextField(textoMostrar: String): String {
    val context = LocalContext.current
    val res = remember { mutableStateOf(("")) }
    Text(text = textoMostrar)
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            TextField(value = inputValue.value,
                onValueChange = { inputValue.value = it },
                label = { Text(textoMostrar) })
        }
    }
    Spacer(modifier = Modifier.width(8.dp))
    return inputValue.value.text
}

//Esto mostrará dos exposeddropdownmenusbox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarDropDowns(list1: List<String>, textoMostrar: String): String {
    val context = LocalContext.current
    var expanded1 by remember { mutableStateOf(false) }
    var selectedText1 by remember { mutableStateOf(list1[0]) }

    Text(text = textoMostrar)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            ExposedDropdownMenuBox(expanded = expanded1, onExpandedChange = {
                expanded1 = it
            }) {
                TextField(value = selectedText1,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded1) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded1,
                    onDismissRequest = { expanded1 = false }) {
                    list1.forEach { item ->
                        DropdownMenuItem(text = { Text(text = item) }, onClick = {
                            selectedText1 = item
                            expanded1 = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            }
        }
    }
    return selectedText1
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
}