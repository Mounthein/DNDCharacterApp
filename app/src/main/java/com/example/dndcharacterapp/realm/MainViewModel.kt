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

    val characters = realm.query<CharacterRealm>().asFlow().map { results ->
            results.list.toList()
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
        )

    init {
        createSampleEntriesCharacter()
    }

    private fun createSampleEntriesCharacter() {
        viewModelScope.launch {
            realm.write {
                var username = "user"
                var name = "name"
                var level = 1
                var inspiration = true
                var background = "background"
                var race = EmRaceCh().apply {
                    name = "namerace"
                    speed = 1
                    size = "sizerace"
                    subrace = "subracerace"
                }
                var alignment = EmAlignmentCh().apply {
                    name = "namealignment"
                    abbreviation = "abbreviattionalignment"
                }
                var hitpoints = EmHitPointsCh().apply {
                    maximum = 1
                    current = 1
                    temporary = 1
                }
                var hitdie = EmHitDieCh().apply {
                    type = "typehitdie"
                    quantity = 1
                }
                var deathsaves = EmDeathSavesCh().apply {
                    success = 1
                    failures = 1
                }
                var temporaryHitPoints = 1
                var exhaustion = 1
                var armorclass = EmArmorClassCh().apply {
                    name = "namearmorclass"
                    type = "typearmorclass"
                    value = 1
                }
                var classItem = EmClassItemCh().apply {
                    name = "nameclassitem"
                    level = 1
                    subclass = "subclassitem"
                }
                var experiencePoints = 1
                var stats = EmStatCh().apply {
                    name = "namestat"
                    value = 1
                }
                var skillProficiency = EmSkillProficiencyCh().apply {
                    name = "nameskillproficiency"
                    bonus = 1
                }
                var language = EmLanguageCh().apply {
                    name = "namelanguage"
                    type = "typelanguage"
                }
                var proficiency = EmProficiencyCh().apply {
                    name = "nameproficiency"
                    type = "typeproficiency"
                }
                var equipment = EmEquipmentCh().apply {
                    name = "nameequipment"
                    equipment_category = "equipmentcategoryequipment"
                    quantity = 1
                }
                var coin = EmCoinCh().apply {
                    name = "namecoin"
                    quantity = 1
                }
                var feature = EmFeatureCh().apply {
                    name = "namefeature"
                    description = "descriptionfeature"
                }
                var trait = EmTraitCh().apply {
                    name = "nametrait"
                    description = "descriptiontrait"
                }
                var spellAbility = EmSpellAbilityCh().apply {
                    spellcasting_ability = "spellcastingabilityspellability"
                    spell_save_dc = 1
                    spell_attack_bonus = 1
                }
                var spell = EmSpellCh().apply {
                    name = "namespell"
                    description = "descriptionspell"
                    school = "schoolspell"
                    level = 1
                    casting_time = "castingtimespell"
                    duration = "durationspell"
                }
                var passiveWisdom = 1
                var initiative = 1
                var speed = 1
                var proficiencyBonus = 1
                var savingThrows = realmListOf("")
                var personalityTraits = "personalitytraits"
                var ideals = "ideals"
                var bonds = "bonds"
                var flaws = "flaws"
                var characterAppearance = "characterappearance"
                var characterBackstory = "characterbackstory"
                var alliesOrganizations = "alliesorganizations"
                var symbol = "symbol"
                val character: CharacterRealm? = null
                character!!.username = username
                character.name = name
                character.level = level
                character.inspiration = inspiration
                character.background = background
                character.race = race
                character.alignment = alignment
                character.hitPoints = hitpoints
                character.hit_die = realmListOf(hitdie)
                character.death_saves = deathsaves
                character.temporary_HitPoints = temporaryHitPoints
                character.exhaustion = exhaustion
                character.armor_Class = armorclass
                character.classes = realmListOf(classItem)
                character.experience_Points = experiencePoints
                character.stats = realmListOf(stats)
                character.skill_proficiencies = realmListOf(skillProficiency)
                character.languages = realmListOf(language)
                character.proficiency_bonus = 1
                character.equipment = realmListOf(equipment)
                character.coin_pouch = realmListOf(coin)
                character.features = realmListOf(feature)
                character.traits = realmListOf(trait)
                character.spell_abilities = realmListOf(spellAbility)
                character.known_spells = realmListOf(spell)
                character.passive_wisdom = passiveWisdom
                character.initiative = initiative
                character.speed = speed
                character.proficiency_bonus = proficiencyBonus
                character.saving_throws = savingThrows
                character.personality_traits = personalityTraits
                character.ideals = ideals
                character.bonds = bonds
                character.flaws = flaws
                character.character_appearance = characterAppearance
                character.character_backstory = characterBackstory
                character.allies_organizations = alliesOrganizations
                character.symbol = symbol

                copyToRealm(character, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
}