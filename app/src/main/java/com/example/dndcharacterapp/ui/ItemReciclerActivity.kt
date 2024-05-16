package com.example.dndcharacterapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.magicitem.MagicItem
import java.util.Locale

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
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ){
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
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ),
        modifier = Modifier
            .clickable { click() })
    {
        Column (modifier = Modifier
            .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.Start)
        {
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = equipment.name,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface)
                Text(text = equipment.equipmentCategory.name,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface)
            }
            CardContent(equipment = equipment)
        }
    }
}

@Composable
fun CardContent(equipment: Equipment){
    Box (modifier = Modifier
        .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), MaterialTheme.shapes.medium)
        .clip(MaterialTheme.shapes.medium)
        .background(color = colorResource(id = R.color.white))

    )
    {
        Row (Modifier.fillMaxSize()){
            Column (modifier = Modifier
                .fillMaxHeight()
                .padding(start = 6.dp),
                horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.height(20.dp))
                if (equipment.weaponCategory != null){
                    Text(text = "Weapon type: " + equipment.weaponCategory, color = MaterialTheme.colorScheme.onSurface)
                }
                if (equipment.weaponRange != null){
                    Text(text = "Range: " + equipment.weaponRange, color = MaterialTheme.colorScheme.onSurface)
                }
                if (equipment.unitQuantity != null){
                    Text(text = "Damage dice: " + equipment.unitQuantity.damageDice, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "Damage type: " + equipment.unitQuantity.damageType.name, color = MaterialTheme.colorScheme.onSurface)
                }
                if (equipment.twoHandedDamage != null){
                    Text(text = "Two handed damage dice: " + equipment.twoHandedDamage.damageDice, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "Two handed damage type: " + equipment.twoHandedDamage.damageType.name, color = MaterialTheme.colorScheme.onSurface)
                }
                if (equipment.toolCategory != null){
                    Text(text = "Tool category: " + equipment.toolCategory, color = MaterialTheme.colorScheme.onSurface)
                }
                if (equipment.vehicleCategory != null){
                    Text(text = "Vehicle category: " + equipment.vehicleCategory, color = MaterialTheme.colorScheme.onSurface)
                }
                if (equipment.description != null){
                    Text(text = "Description: " + equipment.vehicleCategory, color = MaterialTheme.colorScheme.onSurface)
                    equipment.description.forEach {
                        Text(text = it, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun EquipmentCard(equipment: MagicItem, click: () -> Unit){
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
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
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = equipment.name,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface)
                Text(text = equipment.equipmentCategory.name,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface)
            }
            CardContent(equipment = equipment)
        }

    }
}

@Composable
fun CardContent(equipment: MagicItem){
    Box (modifier = Modifier
        .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), MaterialTheme.shapes.medium)
        .clip(MaterialTheme.shapes.medium)
        .background(color = colorResource(id = R.color.white))

    )
    {
        Row (Modifier.fillMaxSize()){
            Column (modifier = Modifier
                .fillMaxHeight()
                .padding(start = 6.dp),
                horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Rarity: " + equipment.rarity.name, color = MaterialTheme.colorScheme.onSurface)
                Text(text = "Description: " + equipment.desc[1], color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(20.dp))
            }
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