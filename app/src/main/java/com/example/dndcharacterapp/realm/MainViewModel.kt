package com.example.dndcharacterapp.realm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndcharacterapp.models.characterRealm.CharacterRealm
import com.example.dndcharacterapp.models.characterRealm.EmAlignmentCh
import com.example.dndcharacterapp.models.characterRealm.EmArmorClassCh
import com.example.dndcharacterapp.models.characterRealm.EmClassItemCh
import com.example.dndcharacterapp.models.characterRealm.EmCoinCh
import com.example.dndcharacterapp.models.characterRealm.EmDeathSavesCh
import com.example.dndcharacterapp.models.characterRealm.EmEquipmentCh
import com.example.dndcharacterapp.models.characterRealm.EmFeatureCh
import com.example.dndcharacterapp.models.characterRealm.EmHitDieCh
import com.example.dndcharacterapp.models.characterRealm.EmHitPointsCh
import com.example.dndcharacterapp.models.characterRealm.EmLanguageCh
import com.example.dndcharacterapp.models.characterRealm.EmProficiencyCh
import com.example.dndcharacterapp.models.characterRealm.EmRaceCh
import com.example.dndcharacterapp.models.characterRealm.EmSkillProficiencyCh
import com.example.dndcharacterapp.models.characterRealm.EmSpellAbilityCh
import com.example.dndcharacterapp.models.characterRealm.EmSpellCh
import com.example.dndcharacterapp.models.characterRealm.EmStatCh
import com.example.dndcharacterapp.models.characterRealm.EmTraitCh
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val realm = RealmApp.realm


    val characters = realm.query<CharacterRealm>().asFlow().map { results ->
        results.list.toList()
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
    )

//    init {
//        createSampleEntriesCharacter()
//    }

    fun insertNewCharacter(characterRealm: CharacterRealm) {
        viewModelScope.launch {
            realm.write {
                val characterRealmInsertar = CharacterRealm().apply {
                    username = characterRealm.username
                    name = characterRealm.name
                    level = characterRealm.level
                    inspiration = characterRealm.inspiration
                    background = characterRealm.background
                    race = characterRealm.race
                    alignment = characterRealm.alignment
                    hitPoints = characterRealm.hitPoints
                    hit_die = characterRealm.hit_die
                    death_saves = characterRealm.death_saves
                    temporary_HitPoints = characterRealm.temporary_HitPoints
                    exhaustion = characterRealm.exhaustion
                    armor_Class = characterRealm.armor_Class
                    classes = characterRealm.classes
                    experience_Points = characterRealm.experience_Points
                    stats = characterRealm.stats
                    skill_proficiencies = characterRealm.skill_proficiencies
                    other_proficiencies = characterRealm.other_proficiencies
                    languages = characterRealm.languages
                    proficiency_bonus = characterRealm.proficiency_bonus
                    equipment = characterRealm.equipment
                    coin_pouch = characterRealm.coin_pouch
                    features = characterRealm.features
                    traits = characterRealm.traits
                    spell_abilities = characterRealm.spell_abilities
                    known_spells = characterRealm.known_spells
                    prepared_spells = characterRealm.prepared_spells
                    passive_wisdom = characterRealm.passive_wisdom
                    initiative = characterRealm.initiative
                    speed = characterRealm.speed
                    proficiency_bonus = characterRealm.proficiency_bonus
                    saving_throws = characterRealm.saving_throws
                    personality_traits = characterRealm.personality_traits
                    ideals = characterRealm.ideals
                    bonds = characterRealm.bonds
                    flaws = characterRealm.flaws
                    character_appearance = characterRealm.character_appearance
                    character_backstory = characterRealm.character_backstory
                    allies_organizations = characterRealm.allies_organizations
                    symbol = characterRealm.symbol
                }
                copyToRealm(characterRealmInsertar, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    private fun createSampleEntriesCharacter() {
        viewModelScope.launch {
            realm.write {
                var usernameCharacter = "seancoca"
                var nameCharacter = "Jironiah"
                var levelCharacter = 1
                var inspirationCharacter = true
                var backgroundCharacter = "Guardian"
                var raceCharacter = EmRaceCh().apply {
                    name = "Humano"
                    speed = 1
                    size = "170cm"
                    subrace = "Joven"
                }

                var alignmentCharacter = EmAlignmentCh().apply {
                    name = "Bueno"
                    abbreviation = "BO"
                }
                var hitpoints = EmHitPointsCh().apply {
                    maximum = 1
                    current = 1
                    temporary = 1
                }
                var hitdie = EmHitDieCh().apply {
                    type = "20"
                    quantity = 1
                }
                var deathsaves = EmDeathSavesCh().apply {
                    success = 1
                    failures = 1
                }
                var temporaryHitPoints = 1
                var exhaustionCharacter = 1
                var armorclass = EmArmorClassCh().apply {
                    name = "Ligero"
                    type = "Ligero"
                    value = 1
                }
                var classItem = EmClassItemCh().apply {
                    name = "Chaqueta"
                    subclass = "Camiseta"
                }
                var experiencePoints = 1
                var statsCharacter = EmStatCh().apply {
                    name = "Fuerza"
                    value = 1
                }
                var skillProficiency = EmSkillProficiencyCh().apply {
                    name = "Inteligencia"
                    bonus = 1
                }
                var language = EmLanguageCh().apply {
                    name = "Humano"
                    type = "Real"
                }
                var proficiencyCharacter = EmProficiencyCh().apply {
                    name = "Inteligente"
                    type = "Inteligencia"
                }
                var equipmentCharacter = EmEquipmentCh().apply {
                    name = "Armero"
                    equipment_category = "Armeria"
                    quantity = 1
                }
                var coin = EmCoinCh().apply {
                    name = "Euros"
                    quantity = 200
                }
                var feature = EmFeatureCh().apply {
                    name = "Suerte"
                    description = "Se basa en la suerte del personaje"
                }
                var trait = EmTraitCh().apply {
                    name = "Breath-Weapon"
                    description =
                        "You can use your action to exhale destructive energy. Your draconic ancestry determines the size, shape, and damage type of the exhalation."
                }
                var spellAbility = EmSpellAbilityCh().apply {
                    spellcasting_ability = "AnimalFriendship"
                    spell_save_dc = 1
                    spell_attack_bonus = 1
                }
                var spell = EmSpellCh().apply {
                    name = "AnimalMessenger"
                    description =
                        "By means of this spell, you use an animal to deliver a message. Choose a Tiny beast you can see within range, such as a squirrel, a blue jay, or a bat. You specify a location, which you must have visited, and a recipient who matches a general description, such as \"a man or woman dressed in the uniform of the town guard\" or \"a red-haired dwarf wearing a pointed hat.\" You also speak a message of up to twenty-five words. The target beast travels for the duration of the spell toward the specified location, covering about 50 miles per 24 hours for a flying messenger, or 25 miles for other animals."
                    school = "Enchantment"
                    level = 1
                    casting_time = "1 action"
                    duration = "24 hours"
                }
                var passiveWisdom = 1
                var initiativeCharacter = 1
                var speedCharacter = 1
                var proficiencyBonus = 1
                var savingThrows = realmListOf("")
                var personalityTraits = "personalitytraits"
                var idealschar = "ideals"
                var bondsCharacter = "bonds"
                var flawsCharacter = "flaws"
                var characterAppearance = "characterappearance"
                var characterBackstory = "characterbackstory"
                var alliesOrganizations = "alliesorganizations"
                var symbolCharacter = "symbol"
                val character = CharacterRealm().apply {
                    username = usernameCharacter
                    name = nameCharacter
                    level = levelCharacter
                    inspiration = inspirationCharacter
                    background = backgroundCharacter
                    race = raceCharacter
                    alignment = alignmentCharacter
                    hitPoints = hitpoints
                    hit_die = realmListOf(hitdie)
                    death_saves = deathsaves
                    temporary_HitPoints = temporaryHitPoints
                    exhaustion = exhaustionCharacter
                    armor_Class = armorclass
                    classes = realmListOf(classItem)
                    experience_Points = experiencePoints
                    stats = realmListOf(statsCharacter)
                    skill_proficiencies = realmListOf(skillProficiency)
                    other_proficiencies = realmListOf(proficiencyCharacter)
                    languages = realmListOf(language)
                    proficiency_bonus = 1
                    equipment = realmListOf(equipmentCharacter)
                    coin_pouch = realmListOf(coin)
                    features = realmListOf(feature)
                    traits = realmListOf(trait)
                    spell_abilities = realmListOf(spellAbility)
                    known_spells = realmListOf(spell)
                    prepared_spells = realmListOf(spell)
                    passive_wisdom = passiveWisdom
                    initiative = initiativeCharacter
                    speed = speedCharacter
                    proficiency_bonus = proficiencyBonus
                    saving_throws = savingThrows
                    personality_traits = personalityTraits
                    ideals = idealschar
                    bonds = bondsCharacter
                    flaws = flawsCharacter
                    character_appearance = characterAppearance
                    character_backstory = characterBackstory
                    allies_organizations = alliesOrganizations
                    symbol = symbolCharacter
                }

                copyToRealm(character, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    /*
    val abilities = realm
        .query<AbilityScore>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )
     */

    init {
        //createSampleEntries()
    }

    /*
    private fun createSampleEntries(){
        viewModelScope.launch {
            realm.write {
                var ability1 = AbilityScore().apply {
                    fullName = "DEX"
                    description = realmListOf("string1", "string2")
                    index = "dex"
                }

                var skill1 = Skill().apply {
                    index = "stealth"
                    name = "Stealth"
                }

                var skill2 = Skill().apply {
                    index = "acrobatics"
                    name = "Acrobatics"
                }

                ability1.skills?.add(skill1)
                ability1.skills?.add(skill2)

                copyToRealm(ability1, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
     */
}