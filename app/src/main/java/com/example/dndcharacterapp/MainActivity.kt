package com.example.dndcharacterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.abilityscore.AbilityScore
import com.example.dndcharacterapp.realm.MainViewModel
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    val abililies by viewModel.abilities.collectAsState()
                    val crudApi = CrudApi()
                    var llista1 = crudApi.getAbilityScoreList()
                    var llista2 = crudApi.getBackgroundList()
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ){
                        items(abililies){ ability ->
                            AbilityItem(
                                ability = ability,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .clickable {

                                    }
                            )
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun AbilityItem(
    ability: AbilityScore,
    modifier: Modifier = Modifier
){
    Column (modifier = modifier)
    {
        Text(
            text = ability.fullName!!,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Skills: ${ability.skills!!.joinToString { it.name!! }}",
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DNDCharacterAppTheme {
        Greeting("Android")
    }
}
*/