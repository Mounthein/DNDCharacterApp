package com.example.dndcharacterapp.models.magicitem

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class MagicItem(): RealmObject {
    val desc: RealmList<String> = realmListOf()
    val equipmentCategory: EquipmentCategory? = null
    val id: String? = null
    val index: String? = null
    val name: String? = null
    val rarity: Rarity? = null
    val variant: Boolean? = null
    val variants: RealmList<Variant> = realmListOf()
}