package com.example.dndcharacterapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndcharacterapp.R
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.equipment.Property
import com.example.dndcharacterapp.models.magicitem.MagicItem
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme


class EquipmentItemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var magicEquipment: MagicItem? = null
        var normalEquipment: Equipment? = null
        val isMagic: String? = intent.getStringExtra("isMagic")
        val equipmentid: String? = intent.getStringExtra("equipment")
        if (isMagic.equals("y")) {
            magicEquipment = CrudApi().getMagicItem(equipmentid!!)
        } else {
            normalEquipment = CrudApi().getEquipment(equipmentid!!)
        }

        setContent {
            DNDCharacterAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        if (isMagic.equals("y")) {
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
fun PrintMagicItem(magicItem: MagicItem) {
    Header(name = magicItem.name)

    Body(magicItem = magicItem)
}

@Composable
fun Body(magicItem: MagicItem) {
    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Rarity: " + magicItem.rarity.name)
        if (magicItem.variant) Text(text = "Variant: Yes")
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        Text(
            text = "Variants", fontSize = 20.sp
        )
        magicItem.variants.forEach {
            Text(text = ".- ${it.name}")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        Text(
            text = "Description", fontSize = 20.sp
        )
        magicItem.desc.forEach {
            Text(text = ".- $it\n")
        }
    }
}

@Composable
fun PrintNormalEquipment(equipment: Equipment) {
    Header(name = equipment.name)

    when (equipment.equipmentCategory.index) {
        "armor" -> NormalArmor(equipment = equipment)
        "weapon" -> NormalWeapon(equipment = equipment)
        "tools" -> NormalTools(equipment = equipment)
        "adventuring-gear" -> NormalAdventuring(equipment = equipment)
    }
}

@Composable
fun NormalTools(equipment: Equipment) {
    Text(text = "Tool Category: " + equipment.toolCategory)
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    )
    Descriptions(equipment = equipment)
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    )
    CostBox(equipment = equipment)
}

@Composable
fun NormalAdventuring(equipment: Equipment) {

    if (equipment.quantity != null) {
        Text(
            text = "Quantity: " + equipment.quantity.toString(),
            color = MaterialTheme.colorScheme.onSurface
        )
    }

    if (equipment.weight != null) Text(
        text = "Weight: " + equipment.weight.toString(), color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    )
    CostBox(equipment = equipment)
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    )

    if (!equipment.description.isNullOrEmpty()) {
        Descriptions(equipment)
    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    )

}

@Composable
fun NormalArmor(equipment: Equipment) {
    Text(
        text = "Armor Category: " + equipment.armorCategory,
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        text = "Armor Class: " + equipment.armorName!!.base.toString(),
        color = MaterialTheme.colorScheme.onSurface
    )
    var dexbonus = ""
    dexbonus = if (equipment.armorName.dexBonus) {
        "Yes"
    } else {
        "No"
    }
    Text(text = "Dex bonus: $dexbonus", color = MaterialTheme.colorScheme.onSurface)
    if (equipment.armorName.maxBonus != null) {
        Text(
            text = "Max Dex Bonus: " + equipment.armorName.maxBonus.toString(),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    )

    CostBox(equipment = equipment)
}

@Composable
fun NormalWeapon(equipment: Equipment) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Category: " + equipment.weaponCategory)
        Text(text = "Type: " + equipment.weaponRange, color = MaterialTheme.colorScheme.onSurface)
        Text(
            text = "Weight: " + equipment.weight.toString(),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Damage Type: " + equipment.unitQuantity!!.damageType.name,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Damage Dealt: " + equipment.unitQuantity.damageDice,
            color = MaterialTheme.colorScheme.onSurface
        )
        if (equipment.weaponRange == "Ranged") {
            val range: String = equipment.range.normal.toString() + "/" + equipment.range.long
            Text(text = "Range: $range", color = MaterialTheme.colorScheme.onSurface)
        }
        if (equipment.throwRange != null) {
            Text(
                text = "Throwable Range: " + equipment.throwRange.normal,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        if (equipment.special != null) {
            Text(
                text = "Special: " + equipment.special, color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        CostBox(equipment = equipment)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        if (!equipment.properties.isNullOrEmpty()) {
            EquipmentProperties(properties = equipment.properties)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
    }

}

@Composable
fun Descriptions(equipment: Equipment) {
    Box(modifier = Modifier.fillMaxWidth()) {
        var desc: String = ""
        equipment.description!!.forEach { desc += ".- $it\n" }
        Text(text = desc, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun EquipmentProperties(properties: List<Property>) {
    Box(
        modifier = Modifier
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
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Properties", fontSize = 20.sp
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    )
    properties.forEach {
        Text(text = "·- " + it.name)
    }
}

@Composable
fun CostBox(equipment: Equipment) {
    Spacer(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 15.dp)
            .fillMaxWidth()
            .height(15.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface), MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primary)
    )
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .border(BorderStroke(2.dp, colorResource(id = R.color.black))),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Cost", fontSize = 20.sp
            )
        }

        Text(
            text = "Unit :" + equipment.cost.unit,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Quantity :" + equipment.cost.quantity,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    DNDCharacterAppTheme {
        Greeting4("Android")
    }
}