package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Background : RealmObject{
    var bonds: Bonds? = null
    var feature: Feature? = null
    var flaws: Flaws? = null
    var id: String? = null
    var ideals: Ideals? = null
    var index: String? = null
    var languageOptions: Int? = null
    var name: String? = null
    var personalityTraits: PersonalityTraits? = null
    var startingEquipment: RealmList<StartingEquipment> = realmListOf()
    var startingEquipmentOption: RealmList<StartingEquipmentOption> = realmListOf()
    var startingProficiencies: RealmList<StartingProficiency> = realmListOf()
}
