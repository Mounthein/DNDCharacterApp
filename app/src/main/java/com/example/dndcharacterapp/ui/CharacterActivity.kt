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
import com.example.dndcharacterapp.models.character.HitPoints
import com.example.dndcharacterapp.models.race.Race
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class CharacterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme (darkTheme = false){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val races = CrudApi().getRaceList()?.toList()
                    val alignments = CrudApi().getAlignmentList()?.toList()
                    if (races != null && alignments != null) {
                        MostrarEditText(races, alignments)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarEditText(
    racesList: List<Race>,
    alignmentsList: List<com.example.dndcharacterapp.models.alignment.Alignment>?,
) {
    var expanded by remember { mutableStateOf(false) }
    var racesOptions by remember { mutableStateOf(racesList[0].name) }
    var alignmentsOptions by remember { mutableStateOf(alignmentsList!![0].name) }

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
            Checkbox(checked = inspiration.value, onCheckedChange = {inspiration.value = it} )
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


            Mostrar1TextField(textoMostrar = "Maximum")
            Mostrar1TextField(textoMostrar = "Current")
            Mostrar1TextField(textoMostrar = "Temporary")


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
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text(textoMostrar) }
            )
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
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text(textoMostrar1) }
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier.weight(1f)
        ) {
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text(textoMostrar2) }
            )
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
            ExposedDropdownMenuBox(
                expanded = expanded1,
                onExpandedChange = {
                    expanded1 = it
                }
            ) {
                TextField(
                    value = selectedText1,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded1) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded1,
                    onDismissRequest = { expanded1 = false }
                ) {
                    list1.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText1 = item
                                expanded1 = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            }
                        )
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
            ExposedDropdownMenuBox(
                expanded = expanded2,
                onExpandedChange = {
                    expanded2 = it
                }
            ) {
                TextField(
                    value = selectedText2,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded2,
                    onDismissRequest = { expanded2 = false }
                ) {
                    list2.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText2 = item
                                expanded2 = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            }
                        )
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