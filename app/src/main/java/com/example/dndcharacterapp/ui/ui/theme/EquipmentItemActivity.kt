package com.example.dndcharacterapp.ui.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.magicitem.MagicItem
import com.example.dndcharacterapp.ui.ui.theme.ui.theme.DNDCharacterAppTheme
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.models.equipment.Cost
import com.example.dndcharacterapp.models.equipment.Property


class EquipmentItemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var magicEquipment: MagicItem? = null
        var normalEquipment: Equipment? = null
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
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())) {
                        if (isMagic.equals("y")){
                            PrintMagicItem(magicItem = magicEquipment!!)
                        } else {
                            PrintNormalEquipment(equipment = normalEquipment!!)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun PrintMagicItem(magicItem: MagicItem){
    Header(name = magicItem.name)

    TextBox(title = "Rarity", content = magicItem.rarity.name)
    if (magicItem.variant) TextBox(title = "Variant", content = "Yes") else TextBox(title = "Variant", content = "No")

    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp))

    Text(text = "Variants",
        fontFamily = FontFamily.Cursive,
        fontSize = 20.sp)
    magicItem.variants.forEach {
        Text(text = ".- ${it.name}")
    }

    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp))

    Text(text = "Description",
        fontFamily = FontFamily.Cursive,
        fontSize = 20.sp)
    magicItem.desc.forEach {
        Text(text = ".- $it\n")
    }
}

@Composable
fun PrintNormalEquipment(equipment: Equipment){
    Header(name = equipment.name)

    when(equipment.equipmentCategory.index){
        "armor" -> NormalArmor(equipment = equipment)
        "weapon" -> NormalWeapon(equipment = equipment)
        "tools" -> NormalTools(equipment = equipment)
        "adventuring-gear" -> NormalAdventuring(equipment = equipment)
    }
}

@Composable
fun NormalTools(equipment: Equipment){
    TextBox(title = "Tool Category", content = equipment.toolCategory)
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp))
    Descriptions(equipment = equipment)
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp))
    CostBox(equipment = equipment)
}

@Composable
fun NormalAdventuring(equipment: Equipment){

    if (equipment.quantity != null){
        TextBox(title = "Quantity", content = equipment.quantity.toString())
    }
    if (equipment.weight != null) TextBox(title = "Weight", content = equipment.weight.toString())
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp))
    CostBox(equipment = equipment)
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp))

    if (!equipment.description.isNullOrEmpty()){
        Descriptions(equipment)
    }

    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp))

}
    
@Composable
fun NormalArmor(equipment: Equipment){
    TextBox(title = "Armor Category", content = equipment.armorCategory)
    TextBox(title = "Armor Class", content = equipment.armorName!!.base.toString())
    var dexbonus = ""
    dexbonus = if (equipment.armorName.dexBonus){
        "Yes"
    } else {
        "No"
    }
    TextBox(title = "Dex bonus", content = dexbonus)
    if (equipment.armorName.maxBonus != null){
        TextBox(title = "Max Dex Bonus", content = equipment.armorName.maxBonus.toString())
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp))
    
    CostBox(equipment = equipment)
}

@Composable
fun NormalWeapon(equipment: Equipment){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        TextBox(title = "Category", content = equipment.weaponCategory)
        TextBox(title = "Type", content = equipment.weaponRange)
        TextBox(title = "Weight", content = equipment.weight.toString())
        TextBox(title = "Damage Type", content = equipment.unitQuantity.damageType.name)
        TextBox(title = "Damage Dealt", content = equipment.unitQuantity.damageDice)
        if (equipment.weaponRange == "Ranged") {
            val range: String = equipment.range.normal.toString()+"/"+equipment.range.long
            TextBox(title = "Range", content = range)
        }
        if (equipment.throwRange != null){
            TextBox(title = "Throwable Range", content = equipment.throwRange.normal.toString()+"/"+equipment.throwRange.long)
        }
        if (equipment.special != null){
            TextBox(title = "Special", content = equipment.special)
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp))
        CostBox(equipment = equipment)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp))
        if (!equipment.properties.isNullOrEmpty()){
            EquipmentProperties(properties = equipment.properties)
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp))
    }

}

@Composable
fun Descriptions(equipment: Equipment){
    Box (modifier = Modifier.fillMaxWidth()) {
        var desc: String = ""
        equipment.description!!.forEach { desc += ".- $it\n" }
        Text(text = desc, modifier = Modifier.fillMaxWidth())
    }
}
@Composable
fun EquipmentProperties(properties: List<Property>){
    Box (modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .drawBehind {
            val borderSize = 4.dp.toPx()
            drawLine(
                color = Color.Black,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = borderSize
            )
        },
        contentAlignment = Alignment.Center){
        Text(text = "Properties",
            fontFamily = FontFamily.Cursive,
            fontSize = 20.sp)
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(20.dp))
    properties.forEach { 
        Text(text = "·- " + it.name)
    }
}
@Composable
fun CostBox(equipment: Equipment){
    Column (modifier = Modifier.fillMaxWidth()) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
            contentAlignment = Alignment.Center){
            Text(text = "Cost",
                fontFamily = FontFamily.Cursive,
                fontSize = 20.sp)
        }
        TextBox(title = equipment.cost.unit, content = equipment.cost.quantity.toString())
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