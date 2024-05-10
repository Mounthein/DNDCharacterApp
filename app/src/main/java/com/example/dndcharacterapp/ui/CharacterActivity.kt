package com.example.dndcharacterapp.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
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
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class CharacterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
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
    var racesOptions by remember { mutableStateOf(racesList!![0].name) }
    var alignmentsOptions by remember { mutableStateOf(alignmentsList!![0].name) }
    var classesOptions by remember { mutableStateOf(classeslist!![0].name) }
    var languagesOptions by remember { mutableStateOf(languagesList!![0].name) }
    var proficiencyOptions by remember { mutableStateOf(proficienciesList!![0].name) }
    var equipmentOptions by remember { mutableStateOf(equipmentList!![0].name) }
    var featureOptions by remember { mutableStateOf(featuresList!![0].name) }
    var traitOptions by remember { mutableStateOf(traitsList!![0].name) }
    var spellOptions by remember { mutableStateOf(spellList!![0].name) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        //Name Level
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Mostrar2TextField("Name", "Level")
        }
        //Inspiration Background
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Inspiration")
            val inspiration = remember { mutableStateOf(true) }
            Checkbox(checked = inspiration.value, onCheckedChange = { inspiration.value = it })
            Spacer(modifier = Modifier.width(16.dp))
            Mostrar1TextField("Background")
        }
        //Race Alignment
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val racesName: MutableList<String> = mutableListOf()
            racesList!!.forEach {
                racesName.add(it.name)
            }
            val alignmentsName: MutableList<String> = mutableListOf()
            alignmentsList!!.forEach {
                alignmentsName.add(it.name)
            }

            MostrarDropDowns(list1 = racesName, list2 = alignmentsName)
        }
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
        //Classes Stats
        Column(Modifier.fillMaxWidth()) {
            // Texto "Classes"
            Text(
                text = "Classes", modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
            )

            var selectedTextClasses by remember { mutableStateOf(classeslist!![0].name) }
            Row(Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                ) {
                    ExposedDropdownMenuBox(expanded = expandedClasses, onExpandedChange = {
                        expandedClasses = it
                    }) {
                        TextField(value = selectedTextClasses,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedClasses) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(expanded = expandedClasses,
                            onDismissRequest = { expandedClasses = false }) {
                            classeslist!!.forEach { item ->
                                DropdownMenuItem(text = {
                                    Text(
                                        text = item.name,
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                }, onClick = {
                                    selectedTextClasses = item.name
                                    expandedClasses = false
                                    Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                                })
                            }
                        }
                    }
                }
            }

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
        }

        //SkillProficiencies Languages
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
        Text(text = "Languages")
        var selectedTextLanguages by remember { mutableStateOf(languagesList!![0].name) }
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                ExposedDropdownMenuBox(expanded = expandedLanguages, onExpandedChange = {
                    expandedLanguages = it
                }) {
                    TextField(value = selectedTextLanguages,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLanguages) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(expanded = expandedLanguages,
                        onDismissRequest = { expandedLanguages = false }) {
                        languagesList!!.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item.name) }, onClick = {
                                selectedTextLanguages = item.name
                                expandedLanguages = false
                                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }

        //OtherProficiencies Equipment
        Text(text = "OtherProficiencies")
        var selectedTextOtherProficiencies by remember { mutableStateOf(proficienciesList!![0].name) }
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                ExposedDropdownMenuBox(expanded = expandedOtherProficiency, onExpandedChange = {
                    expandedOtherProficiency = it
                }) {
                    TextField(value = selectedTextOtherProficiencies,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedOtherProficiency) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(expanded = expandedOtherProficiency,
                        onDismissRequest = { expandedOtherProficiency = false }) {
                        proficienciesList!!.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item.name) }, onClick = {
                                selectedTextOtherProficiencies = item.name
                                expandedOtherProficiency = false
                                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }
        Text(text = "Equipment")
        var selectedTextEquipment by remember { mutableStateOf(equipmentList!![0].name) }
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                ExposedDropdownMenuBox(expanded = expandedEquipment, onExpandedChange = {
                    expandedEquipment = it
                }) {
                    TextField(value = selectedTextEquipment,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedEquipment) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(expanded = expandedEquipment,
                        onDismissRequest = { expandedEquipment = false }) {
                        equipmentList!!.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item.name) }, onClick = {
                                selectedTextEquipment = item.name
                                expandedEquipment = false
                                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }

        //CoinPouch Features
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
        Text(text = "Features")
        var selectedTextFeatures by remember { mutableStateOf(featuresList!![0].name) }
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                ExposedDropdownMenuBox(expanded = expandedFeatures, onExpandedChange = {
                    expandedFeatures = it
                }) {
                    TextField(value = selectedTextFeatures,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFeatures) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedFeatures,
                        onDismissRequest = { expandedFeatures = false }) {
                        featuresList!!.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item.name) }, onClick = {
                                selectedTextFeatures = item.name
                                expandedFeatures = false
                                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }

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
                        Text("Add item", color = MaterialTheme.colorScheme.onBackground)
                    }
                    Button(onClick = {
                        val newList = stringArray.toMutableList()
                        newList.remove("")
                        stringArray = newList
                    }) {
                        Text("Remove item", color = MaterialTheme.colorScheme.onBackground)
                    }
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
    }
}

//Esto mostrará un textfield para que se escriba el string
@Composable
fun Mostrar1TextField(textoMostrar: String) {
    val inputvalue1 = remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            TextField(value = inputvalue1.value,
                onValueChange = { inputvalue1.value = it },
                label = { Text(textoMostrar) })

        }

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun Mostrar2TextField(textoMostrar1: String, textoMostrar2: String) {
    val inputvalue1 = remember { mutableStateOf(TextFieldValue()) }
    val inputvalue2 = remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            TextField(value = inputvalue1.value,
                onValueChange = { inputvalue1.value = it },
                label = { Text(textoMostrar1) })
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier.weight(1f)
        ) {
            TextField(value = inputvalue2.value,
                onValueChange = { inputvalue2.value = it },
                label = { Text(textoMostrar2) })

        }
    }
}


//Esto mostrará dos exposeddropdownmenusbox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarDropDowns(list1: List<String>, list2: List<String>) {
    val context = LocalContext.current
    var expanded1 by remember { mutableStateOf(false) }
    var selectedText1 by remember { mutableStateOf(list1[0]) }
    var expanded2 by remember { mutableStateOf(false) }
    var selectedText2 by remember { mutableStateOf(list2[0]) }

    Row(Modifier.fillMaxWidth()) {
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

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            ExposedDropdownMenuBox(expanded = expanded2, onExpandedChange = {
                expanded2 = it
            }) {
                TextField(value = selectedText2,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded2,
                    onDismissRequest = { expanded2 = false }) {
                    list2.forEach { item ->
                        DropdownMenuItem(text = { Text(text = item) }, onClick = {
                            selectedText2 = item
                            expanded2 = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
}