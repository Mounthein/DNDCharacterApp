package com.example.dndcharacterapp.realm

import android.content.Context
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
import io.realm.kotlin.Realm
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

    //init {
    //    createSampleEntriesCharacter()
    //}

    private fun createSampleEntriesCharacter() {
        viewModelScope.launch {
            realm.write {
                var usernameCharacter = "user"
                var nameCharacter = "name"
                var levelCharacter = 1
                var inspirationCharacter = true
                var backgroundCharacter = "background"
                var raceCharacter = EmRaceCh().apply {
                    name = "namerace"
                    speed = 1
                    size = "sizerace"
                    subrace = "subracerace"
                }

                var alignmentCharacter = EmAlignmentCh().apply {
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
                var exhaustionCharacter = 1
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
                var statsCharacter = EmStatCh().apply {
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
                var proficiencyCharacter = EmProficiencyCh().apply {
                    name = "nameproficiency"
                    type = "typeproficiency"
                }
                var equipmentCharacter = EmEquipmentCh().apply {
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