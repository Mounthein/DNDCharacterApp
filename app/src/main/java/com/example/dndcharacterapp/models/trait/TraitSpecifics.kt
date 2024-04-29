package com.example.dndcharacterapp.models.trait

import io.realm.kotlin.types.EmbeddedRealmObject

class TraitSpecifics : EmbeddedRealmObject{
    val breathWeapon: BreathWeapon? = null
    val damageType: DamageTypeX? = null
    val spellOptions: SpellOptions? = null
    val subtraitOptions: SubtraitOptions? = null
}