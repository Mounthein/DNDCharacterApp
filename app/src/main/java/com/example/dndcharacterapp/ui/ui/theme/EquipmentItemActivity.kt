package com.example.dndcharacterapp.ui.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.magicitem.MagicItem
import com.example.dndcharacterapp.ui.ui.theme.ui.theme.DNDCharacterAppTheme

class EquipmentItemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val magicEquipment: MagicItem?
        val normalEquipment: Equipment?
        val isMagic: String? = intent.getStringExtra("isMagic")
        val equipmentid: String? = intent.getStringExtra("equipment")
        if (isMagic.equals("y")){
            magicEquipment = CrudApi().getMagicItem(equipmentid!!)
        } else {
            normalEquipment = CrudApi().getEquipment(equipmentid!!)
        }

        setContent {
            DNDCharacterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isMagic.equals("y")){

                    }
                }
            }
        }
    }
}

@Composable
fun PrintMagicItem(magicItem: MagicItem){
    Header(name = magicItem.name)

}

@Composable
fun PrintNormalEquipment(equipment: Equipment){
    Header(name = equipment.name)

    when(equipment.equipmentCategory.index){
        "armor" -> ""
        "weapon" -> NormalWeapon(equipment = equipment)
        "tools" -> ""
        "adventuring-gear" -> ""
    }
}

@Composable
fun NormalWeapon(equipment: Equipment){
    TextBox(title = "Category", content = equipment.weaponCategory)
    TextBox(title = "Type", content = equipment.weaponRange)
    TextBox(title = "Weight", content = equipment.weight.toString())
    TextBox(title = "Damage Type", content = equipment.unitQuantity.damageType.name)
    TextBox(title = "Damage Dealt", content = equipment.unitQuantity.damageDice)
    if (equipment.weaponRange == "Ranged") {
        var range: String = equipment.range.long.toString()+"/"+equipment.range.normal
        TextBox(title = "Range", content = range)
    }
    if (equipment.special != null){
        TextBox(title = "Special", content = equipment.special)
    }
}

@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    DNDCharacterAppTheme {
        Greeting4("Android")
    }
}