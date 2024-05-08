package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharacterapp.ui.ui.theme.DNDCharacterAppTheme
import com.example.dndcharacterapp.R
import  android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.equipment.Equipments
import com.example.dndcharacterapp.models.magicitem.MagicItem
import com.example.dndcharacterapp.ui.ui.theme.EquipmentItemActivity
import com.example.dndcharacterapp.ui.ui.theme.MagicActivity
import java.util.Locale
import java.util.Objects

class ItemReciclerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val equipmentList = CrudApi().getEquipmentList()?.toList()
        val equipmentMagicList = CrudApi().getMagicItemList()?.toList()
        setContent {
            DNDCharacterAppTheme (darkTheme = false){
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column (modifier = Modifier.fillMaxSize()) {
                        val filtre = remember { mutableStateOf(TextFieldValue())}
                        Box (modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Filtre")
                            TextField(value = filtre.value, onValueChange = {newFilter -> filtre.value = newFilter})
                            Spacer(modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth())
                        }
                        if (filtre.value.text.isNotEmpty()){
                            val equipmentFiltrat = equipmentList!!.filter { it.name.toLowerCase(Locale.ROOT).contains(filtre.value.text.toLowerCase(Locale.ROOT)) }
                            val magicFiltrat = equipmentMagicList!!.filter { it.name.toLowerCase(Locale.ROOT).contains(filtre.value.text.toLowerCase(Locale.ROOT)) }
                            CardList(equipmentList = equipmentFiltrat, magicFiltrat)
                        } else {
                            CardList(equipmentList = equipmentList!!, equipmentMagicList!!)
                        }

                    }

                }
            }
        }
    }
}


@Composable
fun CardList(equipmentList: List<Equipment>, magicList: List<MagicItem>,){
    var llista: ArrayList<Any> = ArrayList()
    val context = LocalContext.current
    llista.addAll(equipmentList)
    llista.addAll(magicList)
    LazyColumn (contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)){
        items(llista){
            if (IsEquipment(it)){
                val eq = it as Equipment
                EquipmentCard(equipment = eq) {
                    val intent= Intent(context, EquipmentItemActivity::class.java)
                    intent.putExtra("equipment", eq.id)
                    intent.putExtra("isMagic", "n")
                    context.startActivity(intent)
                }
            } else {
                val mi = it as MagicItem
                EquipmentCard(equipment = mi) {
                    val intent= Intent(context, EquipmentItemActivity::class.java)
                    intent.putExtra("equipment", mi.id)
                    intent.putExtra("isMagic", "y")
                    context.startActivity(intent)
                }
            }


        }
    }
}

fun IsEquipment(o: Any): Boolean{
    return try {
        val e = o as Equipment
        true
    }catch (_: Throwable){
        false
    }
}
@Composable
fun EquipmentCard(equipment: Equipment, click: () -> Unit){
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { click() })
    {
        Column (modifier = Modifier
            .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.Start)
        {
            Text(text = "Name: " + equipment.name,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center)
            Text(text = "Type: " + equipment.equipmentCategory.name,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun EquipmentCard(equipment: MagicItem, click: () -> Unit){
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { click() })
    {
        Column (modifier = Modifier
            .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.Start)
        {
            Text(text = "Name: " + equipment.name,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center)
            Text(text = "Type: " + equipment.equipmentCategory.name,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    DNDCharacterAppTheme {
        Greeting3("Android")
    }
}