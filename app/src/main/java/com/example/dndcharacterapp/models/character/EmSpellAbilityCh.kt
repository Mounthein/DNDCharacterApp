package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmSpellAbilityCh : EmbeddedRealmObject {
    val spellcasting_ability: String? = null
    val spell_save_dc: Int? = null
    val spell_attack_bonus: Int? = null
}
