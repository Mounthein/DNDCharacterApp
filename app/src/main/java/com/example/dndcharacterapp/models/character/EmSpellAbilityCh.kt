package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmSpellAbilityCh : EmbeddedRealmObject {
    var spellcasting_ability: String? = null
    var spell_save_dc: Int? = null
    var spell_attack_bonus: Int? = null
}
