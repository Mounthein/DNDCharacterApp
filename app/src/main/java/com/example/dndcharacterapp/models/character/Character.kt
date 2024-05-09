package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Character: RealmObject {
    @PrimaryKey
    val _id: ObjectId = ObjectId()
    val name: String? = null
    val level: Int? = null
    val inspiration: Boolean = false
    val background: String? = null
    val race: EmRaceCh? = null
    val alignment: EmAlignmentCh? = null
    val hitPoints: EmHitPointsCh? = null
    val hit_die: RealmList<EmHitDieCh>? = null
    val death_saves: EmDeathSavesCh? = null
    val temporary_HitPoints: Int? = null
    val exhaustion: Int? = null
    val armor_Class: EmArmorClassCh? = null
    val classes: RealmList<EmClassItemCh>? = null
    val experience_Points: Int? = null
    val stats: RealmList<EmStatCh>? = null
    val skill_proficiencies: RealmList<EmSkillProficiencyCh>? = null
    val languages: RealmList<EmLanguageCh>? = null
    val other_proficiencies: RealmList<EmProficiencyCh>? = null
    val equipment: RealmList<EmEquipmentCh>? = null
    val coin_pouch: RealmList<EmCoinCh>? = null
    val features: RealmList<EmFeatureCh>? = null
    val traits: RealmList<EmTraitCh>? = null
    val spell_abilities: RealmList<EmSpellAbilityCh>? = null
    val prepared_spells: RealmList<EmSpellCh>? = null
    val known_spells: RealmList<EmSpellCh>? = null
    val passive_wisdom: Int? = null
    val initiative: Int? = null
    val speed: Int? = null
    val proficiency_bonus: Int? = null
    val saving_throws: RealmList<String>? = null
    val personality_traits: String? = null
    val ideals: String? = null
    val bonds: String? = null
    val flaws: String? = null
    val character_appearance: String? = null
    val character_backstory: String? = null
    val allies_organizations: String? = null
    val symbol: String? = null
}
