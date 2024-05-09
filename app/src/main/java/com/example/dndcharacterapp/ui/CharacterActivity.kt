package com.example.dndcharacterapp.ui

import android.os.Bundle
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Maximum") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Current") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Temporary") })
            }
        }
        Text(text = "HitDie")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Type") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Quantity") })
            }
        }
        //DeathSaves ArmorClass
        Text(text = "DeathSaves")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Success") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Failures") })
            }
        }
        Text(text = "ArmorClass")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Type") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Value") })
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
                                DropdownMenuItem(text = { Text(text = item.name) }, onClick = {
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

            // TextFields para "Name" y "Value"
            Row(Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Name") })
                }

                Spacer(modifier = Modifier.width(4.dp))

                Box(modifier = Modifier.weight(1f)) {
                    TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Value") })
                }
            }
        }

        //SkillProficiencies Languages
        Text(text = "SkillProficiencies")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Bonus") })
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Quantity") })
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

                    ExposedDropdownMenu(expanded = expandedFeatures,
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
    }
}

//Esto mostrará un textfield para que se escriba el string
@Composable
fun Mostrar1TextField(textoMostrar: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text(textoMostrar) })
        }

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun Mostrar2TextField(textoMostrar1: String, textoMostrar2: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text(textoMostrar1) })
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier.weight(1f)
        ) {
            TextField(value = "", onValueChange = { /*TODO*/ }, label = { Text(textoMostrar2) })
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

                ExposedDropdownMenu(expanded = expanded1,
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

                ExposedDropdownMenu(expanded = expanded2,
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