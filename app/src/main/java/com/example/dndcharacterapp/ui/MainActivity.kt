package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: AbilityScoreViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme (darkTheme = false){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface

                ) {
                    //viewModel.abilityScores.observe(this, Observer {  })
                    Box (Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ){
                        Row {
                            Column (modifier = Modifier.padding(10.dp)) {
                                ButtonWithImage(modifier = Modifier
                                    .clickable {
                                        val intent = Intent(this@MainActivity, MagicReciclerView::class.java)
                                        startActivity(intent)
                                    }
                                    .shadow(15.dp),
                                    id = R.drawable.wand)
                                ButtonWithImage(modifier = Modifier
                                    .clickable {
                                        val intent = Intent(this@MainActivity, ItemReciclerActivity::class.java)
                                        startActivity(intent)
                                    }
                                    .shadow(15.dp),
                                    id = R.drawable.potion)
                            }
                            Column (modifier = Modifier.padding(10.dp)){
                                ButtonWithImage(modifier = Modifier
                                    .clickable {
                                        val intent = Intent(this@MainActivity, CharacterActivity::class.java)
                                        startActivity(intent)
                                    }
                                    .shadow(elevation = 15.dp),
                                    id = R.drawable.helmet)

                                ButtonWithImage(modifier = Modifier
                                    .clickable {
                                        val intent = Intent(this@MainActivity, ConfigurationActivity::class.java)
                                        startActivity(intent)
                                    }
                                    .shadow(elevation = 15.dp),
                                    id = R.drawable.cog)
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ButtonWithImage(modifier: Modifier, id: Int){
    Box (
        modifier = Modifier
            .padding(10.dp)
            .shadow(3.dp)) {
        Image(painter = painterResource(id = id), contentDescription = "wand",
            modifier
                .background(color = colorResource(id = R.color.darkbrown))
                .size(150.dp)
                .padding(10.dp))
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