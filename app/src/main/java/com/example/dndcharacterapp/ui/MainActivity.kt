package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.trait.Traits
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private var collected = false
private var canConnect: Boolean = false
private var crudApi = CrudApi()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tryConnection()
        setContent {
            DNDCharacterAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background

                ) {
                    val context = LocalContext.current
                    if (!canConnect) {
                        Toast.makeText(
                            this,
                            "No hi ha connexió amb la api, reinicia l'aplicació  o espera a tenir connexió",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row {
                            Column(modifier = Modifier.padding(10.dp)) {
                                ButtonWithImage(modifier = Modifier
                                    .clickable {
                                        if (canConnect) {
                                            val intent = Intent(
                                                this@MainActivity, MagicReciclerView::class.java
                                            )
                                            startActivity(intent)
                                        } else {
                                            if (tryConnection()) {
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Ja tens connexió, espera...",
                                                        Toast.LENGTH_LONG
                                                    )
                                                    .show()
                                                val intent = Intent(
                                                    this@MainActivity, MagicReciclerView::class.java
                                                )
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                    .shadow(15.dp), id = R.drawable.wand)
                                ButtonWithImage(modifier = Modifier
                                    .clickable {
                                        if (canConnect) {
                                            val intent = Intent(
                                                this@MainActivity, ItemReciclerActivity::class.java
                                            )
                                            startActivity(intent)
                                        } else {
                                            if (tryConnection()) {
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Ja tens connexió, espera...",
                                                        Toast.LENGTH_LONG
                                                    )
                                                    .show()
                                                val intent = Intent(
                                                    this@MainActivity,
                                                    ItemReciclerActivity::class.java
                                                )
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                    .shadow(15.dp), id = R.drawable.potion)
                            }
                            Column(modifier = Modifier.padding(10.dp)) {
                                ButtonWithImage(modifier = Modifier
                                    .clickable {
                                        if (canConnect) {
                                            val intent = Intent(
                                                this@MainActivity,
                                                CharacterRecyclerActivity::class.java
                                            )
                                            startActivity(intent)
                                        } else {
                                            if (tryConnection()) {
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Ja tens connexió, espera...",
                                                        Toast.LENGTH_LONG
                                                    )
                                                    .show()
                                                val intent = Intent(
                                                    this@MainActivity,
                                                    CharacterRecyclerActivity::class.java
                                                )
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                    .shadow(elevation = 15.dp), id = R.drawable.helmet)

                                ButtonWithImage(modifier = Modifier
                                    .clickable {
                                        if (canConnect) {
                                            val intent = Intent(
                                                this@MainActivity, ConfigurationActivity::class.java
                                            )
                                            startActivity(intent)
                                        } else {
                                            if (tryConnection()) {
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Ja tens connexió, espera...",
                                                        Toast.LENGTH_LONG
                                                    )
                                                    .show()
                                                val intent = Intent(
                                                    this@MainActivity,
                                                    ConfigurationActivity::class.java
                                                )
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                    .shadow(elevation = 15.dp), id = R.drawable.cog)
                            }
                        }

                    }/*} else {
                        Toast.makeText(
                            this,
                            "No hi ha connexió amb la api, reinicia l'aplicació  o espera a tenir connexió",
                            Toast.LENGTH_LONG
                        ).show()
                    }*/
                }
            }
        }
    }
}


private fun tryConnection(): Boolean {
    try {
        canConnect = crudApi.canConnectToApi()
    } catch (e: Exception) {
        Log.e("cantConnectToApi", "No tienes conexión con la API")
    }
    return canConnect
}

private suspend fun fetchData(): Traits? {
    collected = true
    return withContext(Dispatchers.IO) {
        CrudApi().getTraitList()
    }
}

@Composable
fun ButtonWithImage(modifier: Modifier, id: Int) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = "wand",
            modifier
                .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                .size(150.dp)
                .padding(10.dp)
        )
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}


@Preview(showBackground = true, widthDp = 100)
@Composable
fun Greeting() {
    DNDCharacterAppTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface
        ) {

        }
    }
}
