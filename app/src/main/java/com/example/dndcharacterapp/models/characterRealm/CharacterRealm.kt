package com.example.dndcharacterapp.models.characterRealm

import com.example.dndcharacterapp.models.character.Character
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class CharacterRealm(): RealmObject {
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

    // Constructor secundario con todos los parámetros
    constructor(
        _id: ObjectId,
        idString: String,
        username: String?,
        name: String?,
        level: Int?,
        inspiration: Boolean,
        background: String?,
        race: EmRaceCh?,
        alignment: EmAlignmentCh?,
        hitPoints: EmHitPointsCh?,
        hit_die: RealmList<EmHitDieCh>,
        death_saves: EmDeathSavesCh?,
        temporary_HitPoints: Int?,
        exhaustion: Int?,
        armor_Class: EmArmorClassCh?,
        classes: RealmList<EmClassItemCh>,
        experience_Points: Int?,
        stats: RealmList<EmStatCh>,
        skill_proficiencies: RealmList<EmSkillProficiencyCh>,
        languages: RealmList<EmLanguageCh>,
        other_proficiencies: RealmList<EmProficiencyCh>,
        equipment: RealmList<EmEquipmentCh>,
        coin_pouch: RealmList<EmCoinCh>,
        features: RealmList<EmFeatureCh>,
        traits: RealmList<EmTraitCh>,
        spell_abilities: RealmList<EmSpellAbilityCh>,
        prepared_spells: RealmList<EmSpellCh>,
        known_spells: RealmList<EmSpellCh>,
        passive_wisdom: Int?,
        initiative: Int?,
        speed: Int?,
        proficiency_bonus: Int?,
        saving_throws: RealmList<String>,
        personality_traits: String?,
        ideals: String?,
        bonds: String?,
        flaws: String?,
        character_appearance: String?,
        character_backstory: String?,
        allies_organizations: String?,
        symbol: String?
    ) : this() {
        this._id = _id
        this.idString = idString
        this.username = username
        this.name = name
        this.level = level
        this.inspiration = inspiration
        this.background = background
        this.race = race
        this.alignment = alignment
        this.hitPoints = hitPoints
        this.hit_die = hit_die
        this.death_saves = death_saves
        this.temporary_HitPoints = temporary_HitPoints
        this.exhaustion = exhaustion
        this.armor_Class = armor_Class
        this.classes = classes
        this.experience_Points = experience_Points
        this.stats = stats
        this.skill_proficiencies = skill_proficiencies
        this.languages = languages
        this.other_proficiencies = other_proficiencies
        this.equipment = equipment
        this.coin_pouch = coin_pouch
        this.features = features
        this.traits = traits
        this.spell_abilities = spell_abilities
        this.prepared_spells = prepared_spells
        this.known_spells = known_spells
        this.passive_wisdom = passive_wisdom
        this.initiative = initiative
        this.speed = speed
        this.proficiency_bonus = proficiency_bonus
        this.saving_throws = saving_throws
        this.personality_traits = personality_traits
        this.ideals = ideals
        this.bonds = bonds
        this.flaws = flaws
        this.character_appearance = character_appearance
        this.character_backstory = character_backstory
        this.allies_organizations = allies_organizations
        this.symbol = symbol
    }

    override fun toString(): String {
        return "CharacterRealm(_id=$_id, idString='$idString', username=$username, name=$name, level=$level, inspiration=$inspiration, background=$background, race=$race, alignment=$alignment, hitPoints=$hitPoints, hit_die=$hit_die, death_saves=$death_saves, temporary_HitPoints=$temporary_HitPoints, exhaustion=$exhaustion, armor_Class=$armor_Class, classes=$classes, experience_Points=$experience_Points, stats=$stats, skill_proficiencies=$skill_proficiencies, languages=$languages, other_proficiencies=$other_proficiencies, equipment=$equipment, coin_pouch=$coin_pouch, features=$features, traits=$traits, spell_abilities=$spell_abilities, prepared_spells=$prepared_spells, known_spells=$known_spells, passive_wisdom=$passive_wisdom, initiative=$initiative, speed=$speed, proficiency_bonus=$proficiency_bonus, saving_throws=$saving_throws, personality_traits=$personality_traits, ideals=$ideals, bonds=$bonds, flaws=$flaws, character_appearance=$character_appearance, character_backstory=$character_backstory, allies_organizations=$allies_organizations, symbol=$symbol)"
    }
//    fun fromCharacter(character: Character): CharacterRealm {
//        val characterRealm = CharacterRealm()
//
//        with(characterRealm) {
//            username = username
//            name = character.name
//            level = character.level
//            inspiration = character.inspiration
//            background = character.background
//            race = character.race?.let { EmRaceCh.fromRace(it) }
//            alignment = character.alignment?.let { EmAlignmentCh.fromAlignment(it) }
//            hitPoints = character.hitPoints?.let { EmHitPointsCh.fromHitPoints(it) }
//            hit_die = character.hitDie?.let { realmListOf(*it.map { EmHitDieCh.fromHitDie(it) }.toTypedArray()) }
//            death_saves = character.deathSaves?.let { EmDeathSavesCh.fromDeathSaves(it) }
//            temporary_HitPoints = character.temporaryHitPoints
//            exhaustion = character.exhaustion
//            armor_Class = character.armorClass?.let { EmArmorClassCh.fromArmorClass(it) }
//            classes = character.classes?.let { realmListOf(*it.map { EmClassItemCh.fromClassItem(it) }.toTypedArray()) }
//            experience_Points = character.experiencePoints
//            stats = character.stats?.let { realmListOf(*it.map { EmStatCh.fromStat(it) }.toTypedArray()) }
//            skill_proficiencies = character.skillProficiencies?.let { realmListOf(*it.map { EmSkillProficiencyCh.fromSkillProficiency(it) }.toTypedArray()) }
//            languages = character.languages?.let { realmListOf(*it.map { EmLanguageCh.fromLanguage(it) }.toTypedArray()) }
//            other_proficiencies = character.otherProficiencies?.let { realmListOf(*it.map { EmProficiencyCh.fromProficiency(it) }.toTypedArray()) }
//            equipment = character.equipment?.let { realmListOf(*it.map { EmEquipmentCh.fromEquipment(it) }.toTypedArray()) }
//            coin_pouch = character.coinPouch?.let { realmListOf(*it.map { EmCoinCh.fromCoin(it) }.toTypedArray()) }
//            features = character.features?.let { realmListOf(*it.map { EmFeatureCh.fromFeature(it) }.toTypedArray()) }
//            traits = character.traits?.let { realmListOf(*it.map { EmTraitCh.fromTrait(it) }.toTypedArray()) }
//            spell_abilities = character.spellAbilities?.let { realmListOf(*it.map { EmSpellAbilityCh.fromSpellAbility(it) }.toTypedArray()) }
//            prepared_spells = character.preparedSpells?.let { realmListOf(*it.map { EmSpellCh.fromSpell(it) }.toTypedArray()) }
//            known_spells = character.knownSpells?.let { realmListOf(*it.map { EmSpellCh.fromSpell(it) }.toTypedArray()) }
//            passive_wisdom = character.passiveWisdom
//            initiative = character.initiative
//            speed = character.speed
//            proficiency_bonus = character.proficiencyBonus
//            saving_throws = RealmList(character.savingThrows.toTypedArray())
//            personality_traits = character.personalityTrait
//            ideals = character.ideals
//            bonds = character.bonds
//            flaws = character.flaws
//            character_appearance = character.characterAppearance
//            character_backstory = character.characterBackstory
//            allies_organizations = character.alliesOrganizations
//            symbol = character.symbol
//        }
//
//        return characterRealm
//    }
}
