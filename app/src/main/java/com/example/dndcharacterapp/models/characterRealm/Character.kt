package com.example.dndcharacterapp.models.characterRealm

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Character: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var username: String? = null
    var name: String? = null
    var level: Int? = null
    var inspiration: Boolean = false
    var background: String? = null
    var race: EmRaceCh? = null
    var alignment: EmAlignmentCh? = null
    var hitPoints: EmHitPointsCh? = null
    var hit_die: RealmList<EmHitDieCh>? = null
    var death_saves: EmDeathSavesCh? = null
    var temporary_HitPoints: Int? = null
    var exhaustion: Int? = null
    var armor_Class: EmArmorClassCh? = null
    var classes: RealmList<EmClassItemCh>? = null
    var experience_Points: Int? = null
    var stats: RealmList<EmStatCh>? = null
    var skill_proficiencies: RealmList<EmSkillProficiencyCh>? = null
    var languages: RealmList<EmLanguageCh>? = null
    var other_proficiencies: RealmList<EmProficiencyCh>? = null
    var equipment: RealmList<EmEquipmentCh>? = null
    var coin_pouch: RealmList<EmCoinCh>? = null
    var features: RealmList<EmFeatureCh>? = null
    var traits: RealmList<EmTraitCh>? = null
    var spell_abilities: RealmList<EmSpellAbilityCh>? = null
    var prepared_spells: RealmList<EmSpellCh>? = null
    var known_spells: RealmList<EmSpellCh>? = null
    var passive_wisdom: Int? = null
    var initiative: Int? = null
    var speed: Int? = null
    var proficiency_bonus: Int? = null
    var saving_throws: RealmList<String> = crearRealmList()
    var personality_traits: String? = null
    var ideals: String? = null
    var bonds: String? = null
    var flaws: String? = null
    var character_appearance: String? = null
    var character_backstory: String? = null
    var allies_organizations: String? = null
    var symbol: String? = null

    companion object {
        private fun <T> crearRealmList(): RealmList<T> {
            try {
                val realmListClass = Class.forName("io.realm.RealmList")
                val constructor = realmListClass.getDeclaredConstructor()
                constructor.isAccessible = true
                return constructor.newInstance() as RealmList<T>
            } catch (e: Exception) {
                throw RuntimeException("Error al crear RealmList mediante reflexión", e)
            }
        }
    }
}
