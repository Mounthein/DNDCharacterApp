package com.example.dndcharacterapp.models.characterRealm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class CharacterRealm: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var idString: String = _id.toString()
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
    var saving_throws: RealmList<String> = realmListOf("")
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

    override fun toString(): String {
        return "CharacterRealm(_id=$_id, idString='$idString', username=$username, name=$name, level=$level, inspiration=$inspiration, background=$background, race=$race, alignment=$alignment, hitPoints=$hitPoints, hit_die=$hit_die, death_saves=$death_saves, temporary_HitPoints=$temporary_HitPoints, exhaustion=$exhaustion, armor_Class=$armor_Class, classes=$classes, experience_Points=$experience_Points, stats=$stats, skill_proficiencies=$skill_proficiencies, languages=$languages, other_proficiencies=$other_proficiencies, equipment=$equipment, coin_pouch=$coin_pouch, features=$features, traits=$traits, spell_abilities=$spell_abilities, prepared_spells=$prepared_spells, known_spells=$known_spells, passive_wisdom=$passive_wisdom, initiative=$initiative, speed=$speed, proficiency_bonus=$proficiency_bonus, saving_throws=$saving_throws, personality_traits=$personality_traits, ideals=$ideals, bonds=$bonds, flaws=$flaws, character_appearance=$character_appearance, character_backstory=$character_backstory, allies_organizations=$allies_organizations, symbol=$symbol)"
    }

}
