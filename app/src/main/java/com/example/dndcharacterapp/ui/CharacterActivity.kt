package com.example.dndcharacterapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.character.Alignments
import com.example.dndcharacterapp.models.race.Race
import com.example.dndcharacterapp.ui.ui.theme.DNDCharacterAppTheme

class CharacterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme {
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

@Composable
fun MostrarEditText(
    racesList: List<Race>,
    alignmentsList: List<com.example.dndcharacterapp.models.alignment.Alignment>,
    initialButtonText: String = racesList[0].name
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(initialButtonText) }

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
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text("Name") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text("Level") },
                modifier = Modifier.weight(1f)
            )
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
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text("Background") },
                modifier = Modifier.weight(1f)
            )
        }
        //Race Alignment
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { expanded = true },
                modifier = Modifier.weight(1f)
            ) {
                Text(selectedOption)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                racesList.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = option.name
                            expanded = false
                        },
                        text = { Text(option.name) }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text("Alignment") },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
}