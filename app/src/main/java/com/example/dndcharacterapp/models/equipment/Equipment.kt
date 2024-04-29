package com.example.dndcharacterapp.models.equipment

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Equipment : RealmObject{
    val armorCategory: String? = null
    val armorName: ArmorName? = null
    val capacity: String? = null
    val categoryRange: String? = null
    val contents: Contents? = null
    val cost: Cost? = null
    val description: RealmList<String> = realmListOf()
    val equipmentCategory: EquipmentCategory? = null
    val gearCategory: Item? = null
    val id: String? = null
    val index: String? = null
    val name: String? = null
    val properties: RealmList<Property> = realmListOf()
    val quantity: Int? = null
    val range: Range? = null
    val special: RealmList<String> = realmListOf()
    val speed: Speed? = null
    val stealthDisadvantage: Boolean? = null
    val strengthMinimum: Int? = null
    val throwRange: ThrowRange? = null
    val toolCategory: String? = null
    val twoHandedDamage: TwoHandedDamage? = null
    val unitQuantity: UnitQuantity? = null
    val vehicleCategory: String? = null
    val weaponCategory: String? = null
    val weaponRange: String? = null
    val weight: Double? = null
}