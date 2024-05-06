package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.realm.MainViewModel
import com.example.dndcharacterapp.realm.RealmApp
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme
import com.example.dndcharacterapp.R

class MainActivity : ComponentActivity() {

    private val viewModel: AbilityScoreViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.brown)

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
                                    .clickable { }
                                    .shadow(15.dp),
                                    id = R.drawable.potion)
                            }
                            Column (modifier = Modifier.padding(10.dp)){
                                ButtonWithImage(modifier = Modifier
                                    .clickable { }
                                    .shadow(elevation = 15.dp),
                                    id = R.drawable.helmet)
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