package com.example.dndcharacterapp.models.equipmentcategory

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class EquipmentCategory : RealmObject{
    val equipment: RealmList<Equipment> = realmListOf()
    val id: String? = null
    val index: String? = null
    val name: String? = null
}