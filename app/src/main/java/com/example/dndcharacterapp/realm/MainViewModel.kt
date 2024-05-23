package com.example.dndcharacterapp.realm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndcharacterapp.models.character.Alignment
import com.example.dndcharacterapp.models.character.ArmorClass
import com.example.dndcharacterapp.models.character.Character
import com.example.dndcharacterapp.models.character.ClassItem
import com.example.dndcharacterapp.models.character.Coin
import com.example.dndcharacterapp.models.character.DeathSaves
import com.example.dndcharacterapp.models.character.Equipment
import com.example.dndcharacterapp.models.character.Feature
import com.example.dndcharacterapp.models.character.HitDie
import com.example.dndcharacterapp.models.character.HitPoints
import com.example.dndcharacterapp.models.character.Language
import com.example.dndcharacterapp.models.character.Proficiency
import com.example.dndcharacterapp.models.character.Race
import com.example.dndcharacterapp.models.character.SkillProficiency
import com.example.dndcharacterapp.models.character.Spell
import com.example.dndcharacterapp.models.character.SpellAbility
import com.example.dndcharacterapp.models.character.Stat
import com.example.dndcharacterapp.models.character.Trait
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
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
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
                    userName = characterRealm.userName
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

    fun convertRealmCharacterToNormal(characterRealm: CharacterRealm): Character {
        val race = Race(
            speed = characterRealm.race?.speed,
            name = characterRealm.race?.name,
            size = characterRealm.race?.size,
            subrace = characterRealm.race?.subrace
        )

        val alignment = Alignment(
            name = characterRealm.alignment?.name,
            abbreviation = characterRealm.alignment?.abbreviation
        )

        val hitPoints = HitPoints(
            current = characterRealm.hitPoints?.current ?: 0,
            temporary = characterRealm.hitPoints?.temporary ?: 0,
            maximum = characterRealm.hitPoints?.maximum ?: 0
        )

        val hitDie = characterRealm.hit_die?.firstOrNull()?.let {
            listOf(HitDie(type = it.type, quantity = it.quantity))
        } ?: emptyList()

        val deathSaves = DeathSaves(
            success = characterRealm.death_saves?.success ?: 0,
            failures = characterRealm.death_saves?.failures ?: 0
        )

        val armorClass = ArmorClass(
            name = characterRealm.armor_Class?.name,
            type = characterRealm.armor_Class?.type,
            value = characterRealm.armor_Class?.value ?: 0
        )

        val classes = characterRealm.classes?.map {
            ClassItem(name = it.name, subclass = it.subclass)
        } ?: emptyList()

        val stats = characterRealm.stats?.map {
            Stat(name = it.name, value = it.value)
        } ?: emptyList()

        val skillProficiencies = characterRealm.skill_proficiencies?.map {
            SkillProficiency(name = it.name, bonus = it.bonus)
        } ?: emptyList()

        val otherProficiencies = characterRealm.other_proficiencies?.map {
            Proficiency(name = it.name, type = it.type)
        } ?: emptyList()

        val languages = characterRealm.languages?.map {
            Language(name = it.name, type = it.type)
        } ?: emptyList()

        val equipment = characterRealm.equipment?.map {
            Equipment(
                name = it.name, equipmentCategory = it.equipment_category, quantity = it.quantity
            )
        } ?: emptyList()

        val coinPouch = characterRealm.coin_pouch?.map {
            Coin(name = it.name, quantity = it.quantity)
        } ?: emptyList()

        val features = characterRealm.features?.map {
            Feature(name = it.name, description = it.description)
        } ?: emptyList()

        val traits = characterRealm.traits?.map {
            Trait(name = it.name, description = it.description)
        } ?: emptyList()

        val spellAbilities = characterRealm.spell_abilities?.map {
            SpellAbility(
                spellcastingAbility = it.spellcasting_ability,
                spellSaveDc = it.spell_save_dc,
                spellAttackBonus = it.spell_attack_bonus
            )
        } ?: emptyList()

        val knownSpells = characterRealm.known_spells?.map {
            Spell(
                name = it.name,
                level = it.level,
                school = it.school,
                castingTime = it.casting_time,
                duration = it.duration,
                description = it.description
            )
        } ?: emptyList()

        val preparedSpells = characterRealm.prepared_spells?.map {
            Spell(
                name = it.name,
                level = it.level,
                school = it.school,
                castingTime = it.casting_time,
                duration = it.duration,
                description = it.description
            )
        } ?: emptyList()

        return Character(
            userName = characterRealm.userName,
            name = characterRealm.name,
            level = characterRealm.level,
            inspiration = characterRealm.inspiration,
            background = characterRealm.background,
            race = race,
            alignment = alignment,
            hitPoints = hitPoints,
            hitDie = hitDie,
            deathSaves = deathSaves,
            temporaryHitPoints = characterRealm.temporary_HitPoints,
            exhaustion = characterRealm.exhaustion,
            armorClass = armorClass,
            classes = classes,
            experiencePoints = characterRealm.experience_Points,
            stats = stats,
            skillProficiencies = skillProficiencies,
            otherProficiencies = otherProficiencies,
            languages = languages,
            proficiencyBonus = characterRealm.proficiency_bonus,
            equipment = equipment,
            coinPouch = coinPouch,
            features = features,
            traits = traits,
            spellAbilities = spellAbilities,
            knownSpells = knownSpells,
            preparedSpells = preparedSpells,
            passiveWisdom = characterRealm.passive_wisdom,
            initiative = characterRealm.initiative,
            speed = characterRealm.speed,
            personalityTrait = characterRealm.personality_traits,
            ideals = characterRealm.ideals,
            bonds = characterRealm.bonds,
            flaws = characterRealm.flaws,
            characterAppearance = characterRealm.character_appearance,
            characterBackstory = characterRealm.character_backstory,
            alliesOrganizations = characterRealm.allies_organizations,
            symbol = characterRealm.symbol
        )
    }


    fun insertNewCharacterToRealm(characterRealm: Character) {
        val raceInsertar = EmRaceCh()
        raceInsertar.speed = characterRealm.race?.speed
        raceInsertar.name = characterRealm.race?.name
        raceInsertar.size = characterRealm.race?.size
        raceInsertar.subrace = characterRealm.race?.subrace
        val alignmentInsertar = EmAlignmentCh()
        alignmentInsertar.name = characterRealm.alignment?.name
        alignmentInsertar.abbreviation = characterRealm.alignment?.abbreviation
        val hitPointsInsertar = EmHitPointsCh()
        hitPointsInsertar.current = characterRealm.hitPoints!!.current
        hitPointsInsertar.temporary = characterRealm.hitPoints.temporary
        hitPointsInsertar.maximum = characterRealm.hitPoints.maximum
        val hitDieIndividualInsertar = EmHitDieCh()
        hitDieIndividualInsertar.type = characterRealm.hitDie!!.first().type
        hitDieIndividualInsertar.quantity = characterRealm.hitDie.first().quantity
        val hitDieinsertar = realmListOf(hitDieIndividualInsertar)
//        hitDieinsertar.add(hitDieIndividualInsertar)
        val deathSavesInsertar = EmDeathSavesCh()
        deathSavesInsertar.success = characterRealm.deathSaves?.success
        deathSavesInsertar.failures = characterRealm.deathSaves?.failures
        val armorClassInsertar = EmArmorClassCh()
        armorClassInsertar.name = characterRealm.armorClass?.name
        armorClassInsertar.type = characterRealm.armorClass?.type
        armorClassInsertar.value = characterRealm.armorClass?.value
        val classesInsertar = EmClassItemCh()
        classesInsertar.name = characterRealm.classes?.first()?.name
        classesInsertar.subclass = characterRealm.classes?.first()?.subclass
        val classesIndividualInsertar = realmListOf(classesInsertar)
//        classesIndividualInsertar.add(classesInsertar)
        val statsInsertar = EmStatCh()
        statsInsertar.name = characterRealm.stats!!.first().name
        statsInsertar.value = characterRealm.stats.first().value
        val statsIndividualInsertar = realmListOf(statsInsertar)
//        statsIndividualInsertar.add(statsInsertar)
        val skillProficiencyInsertar = EmSkillProficiencyCh()
        skillProficiencyInsertar.name = characterRealm.skillProficiencies?.first()?.name
        skillProficiencyInsertar.bonus = characterRealm.skillProficiencies?.first()?.bonus
        val skillProficiencyIndividualInsertar = realmListOf(skillProficiencyInsertar)
//        skillProficiencyIndividualInsertar.add(skillProficiencyInsertar)
        val otherProficienciesInsertar = EmProficiencyCh()
        otherProficienciesInsertar.name = characterRealm.otherProficiencies?.first()?.name
        otherProficienciesInsertar.type = characterRealm.otherProficiencies?.first()?.type
        val otherProficienciesIndividualInsertar = realmListOf(otherProficienciesInsertar)
//        otherProficienciesIndividualInsertar.add(otherProficienciesInsertar)
        val languagesInsertar = EmLanguageCh()
        languagesInsertar.type = characterRealm.languages?.first()?.type
        languagesInsertar.name = characterRealm.languages?.first()?.name
        val languagesIndividualInsertar = realmListOf(languagesInsertar)
//        languagesIndividualInsertar.add(languagesInsertar)
        val equipmentInsertar = EmEquipmentCh()
        equipmentInsertar.name = characterRealm.equipment?.first()?.name
        equipmentInsertar.equipment_category = characterRealm.equipment?.first()?.equipmentCategory
        equipmentInsertar.quantity = characterRealm.equipment?.first()?.quantity
        val equipmentIndividualInsertar = realmListOf(equipmentInsertar)
//        equipmentIndividualInsertar.add(equipmentInsertar)
        val coinPouch = EmCoinCh()
        coinPouch.name = characterRealm.coinPouch?.first()?.name
        coinPouch.quantity = characterRealm.coinPouch?.first()?.quantity
        val coinPouchIndividualInsertar = realmListOf(coinPouch)
//        coinPouchIndividualInsertar.add(coinPouch)
        val featuresInsertar = EmFeatureCh()
        featuresInsertar.name = characterRealm.features?.first()?.name
        featuresInsertar.description = characterRealm.features?.first()?.description
        val featuresIndividualInsertar = realmListOf(featuresInsertar)
//        featuresIndividualInsertar.add(featuresInsertar)
        val traitsInsertar = EmTraitCh()
        traitsInsertar.name = characterRealm.traits?.first()?.name
        traitsInsertar.description = characterRealm.traits?.first()?.description
        val traitsIndividualInsertar = realmListOf(traitsInsertar)
//        traitsIndividualInsertar.add(traitsInsertar)
        val spellAbilityInsertar = EmSpellAbilityCh()
        spellAbilityInsertar.spellcasting_ability =
            characterRealm.spellAbilities?.first()?.spellcastingAbility
        spellAbilityInsertar.spell_save_dc = characterRealm.spellAbilities?.first()?.spellSaveDc
        spellAbilityInsertar.spell_attack_bonus =
            characterRealm.spellAbilities?.first()?.spellAttackBonus
        val spellAbilityIndividualInsertar = realmListOf(spellAbilityInsertar)
//        spellAbilityIndividualInsertar.add(spellAbilityInsertar)
        val knownSpellsInsertar = EmSpellCh()
        knownSpellsInsertar.name = characterRealm.knownSpells?.first()?.name
        knownSpellsInsertar.level = characterRealm.knownSpells?.first()?.level
        knownSpellsInsertar.school = characterRealm.knownSpells?.first()?.school
        knownSpellsInsertar.casting_time = characterRealm.knownSpells?.first()?.castingTime
        knownSpellsInsertar.duration = characterRealm.knownSpells?.first()?.duration
        knownSpellsInsertar.description = characterRealm.knownSpells?.first()?.description
        val knownSpellsIndividualInsertar = realmListOf(knownSpellsInsertar)
//        knownSpellsIndividualInsertar.add(knownSpellsInsertar)
        val preparedSpellsInsertar = EmSpellCh()
        preparedSpellsInsertar.name = characterRealm.preparedSpells!!.first().name
        preparedSpellsInsertar.level = characterRealm.preparedSpells!!.first().level
        preparedSpellsInsertar.school = characterRealm.preparedSpells!!.first().school
        preparedSpellsInsertar.casting_time = characterRealm.preparedSpells!!.first().castingTime
        preparedSpellsInsertar.duration = characterRealm.preparedSpells!!.first().duration
        preparedSpellsInsertar.description = characterRealm.preparedSpells!!.first().description
        val preparedSpellsIndividualInsertar = realmListOf(preparedSpellsInsertar)
//        preparedSpellsIndividualInsertar.add(preparedSpellsInsertar)
        viewModelScope.launch {
            realm.write {
                val characterRealmInsertar = CharacterRealm().apply {
                    userName = characterRealm.userName
                    name = characterRealm.name
                    level = characterRealm.level
                    inspiration = characterRealm.inspiration
                    background = characterRealm.background
//                    race?.speed = characterRealm.race?.speed
//                    race?.name = characterRealm.race?.name
//                    race?.size = characterRealm.race?.size
//                    race?.subrace = characterRealm.race?.subrace
                    race = raceInsertar
//                    alignment?.name = characterRealm.alignment?.name
//                    alignment?.abbreviation = characterRealm.alignment?.abbreviation
                    alignment = alignmentInsertar
//                    hitPoints?.temporary = characterRealm.hitPoints.temporary
//                    hitPoints?.current = characterRealm.hitPoints.current
//                    hitPoints?.maximum = characterRealm.hitPoints.maximum
                    hitPoints = hitPointsInsertar
//                    hit_die?.first()?.type = characterRealm.hitDie.first().type
//                    hit_die?.first()?.quantity = characterRealm.hitDie.first().quantity
                    hit_die = hitDieinsertar
//                    death_saves?.failures = characterRealm.deathSaves?.failures
//                    death_saves?.success = characterRealm.deathSaves?.success
                    death_saves = deathSavesInsertar
                    temporary_HitPoints = characterRealm.temporaryHitPoints
                    exhaustion = characterRealm.exhaustion
//                    armor_Class?.type = characterRealm.armorClass?.type
//                    armor_Class?.name = characterRealm.armorClass?.name
//                    armor_Class?.value = characterRealm.armorClass?.value
                    armor_Class = armorClassInsertar
//                    classes?.first()?.name = characterRealm.classes?.first()?.name
//                    classes?.first()?.subclass = characterRealm.classes?.first()?.subclass
                    classes = classesIndividualInsertar
                    experience_Points = characterRealm.experiencePoints
//                    stats?.first()?.name = characterRealm.stats?.first()?.name
//                    stats?.first()?.value = characterRealm.stats?.first()?.value
                    stats = statsIndividualInsertar
//                    skill_proficiencies?.first()?.name =
//                        characterRealm.skillProficiencies?.first()?.name
//                    skill_proficiencies?.first()?.bonus =
//                        characterRealm.skillProficiencies?.first()?.bonus
                    skill_proficiencies = skillProficiencyIndividualInsertar
//                    other_proficiencies?.first()?.type =
//                        characterRealm.otherProficiencies?.first()?.type
//                    other_proficiencies?.first()?.name =
//                        characterRealm.otherProficiencies?.first()?.name
                    other_proficiencies = otherProficienciesIndividualInsertar
//                    languages?.first()?.type = characterRealm.languages?.first()?.type
//                    languages?.first()?.name = characterRealm.languages?.first()?.name
                    languages = languagesIndividualInsertar
//                    proficiency_bonus = characterRealm.proficiencyBonus
//                    equipment?.first()?.name = characterRealm.equipment?.first()?.name
//                    equipment?.first()?.equipment_category =
//                        characterRealm.equipment?.first()?.equipmentCategory
//                    equipment?.first()?.quantity = characterRealm.equipment?.first()?.quantity
                    equipment = equipmentIndividualInsertar
//                    coin_pouch?.first()?.name = characterRealm.coinPouch?.first()?.name
                    coin_pouch = coinPouchIndividualInsertar
//                    features?.first()?.name = characterRealm.features?.first()?.name
//                    features?.first()?.description = characterRealm.features?.first()?.description
                    features = featuresIndividualInsertar
//                    traits?.first()?.name = characterRealm.traits?.first()?.name
//                    traits?.first()?.description = characterRealm.traits?.first()?.description
                    traits = traitsIndividualInsertar
//                    spell_abilities?.first()?.spell_save_dc =
//                        characterRealm.spellAbilities?.first()?.spellSaveDc
//                    spell_abilities?.first()?.spellcasting_ability =
//                        characterRealm.spellAbilities?.first()?.spellcastingAbility
//                    spell_abilities?.first()?.spell_attack_bonus =
//                        characterRealm.spellAbilities?.first()?.spellAttackBonus
                    spell_abilities = spellAbilityIndividualInsertar
//                    known_spells?.first()?.name = characterRealm.knownSpells?.first()?.name
//                    known_spells?.first()?.level = characterRealm.knownSpells?.first()?.level
//                    known_spells?.first()?.school = characterRealm.knownSpells?.first()?.school
//                    known_spells?.first()?.duration = characterRealm.knownSpells?.first()?.duration
//                    known_spells?.first()?.description =
//                        characterRealm.knownSpells?.first()?.description
//                    known_spells?.first()?.casting_time =
//                        characterRealm.knownSpells?.first()?.castingTime
                    known_spells = knownSpellsIndividualInsertar
//                    prepared_spells?.first()?.name = characterRealm.knownSpells?.first()?.name
//                    prepared_spells?.first()?.level = characterRealm.knownSpells?.first()?.level
//                    prepared_spells?.first()?.school = characterRealm.knownSpells?.first()?.school
//                    prepared_spells?.first()?.duration =
//                        characterRealm.knownSpells?.first()?.duration
//                    prepared_spells?.first()?.description =
//                        characterRealm.knownSpells?.first()?.description
//                    prepared_spells?.first()?.casting_time =
//                        characterRealm.knownSpells?.first()?.castingTime
                    prepared_spells = preparedSpellsIndividualInsertar
                    passive_wisdom = characterRealm.passiveWisdom
                    initiative = characterRealm.initiative
                    speed = characterRealm.speed
                    proficiency_bonus = characterRealm.proficiencyBonus
//                    saving_throws = characterRealm.saving_throws.toRealmList()
                    personality_traits = characterRealm.personalityTrait
                    ideals = characterRealm.ideals
                    bonds = characterRealm.bonds
                    flaws = characterRealm.flaws
                    character_appearance = characterRealm.characterAppearance
                    character_backstory = characterRealm.characterBackstory
                    allies_organizations = characterRealm.alliesOrganizations
                    symbol = characterRealm.symbol
                }
                copyToRealm(characterRealmInsertar, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    fun createSampleEntriesCharacter() {
        viewModelScope.launch {
            realm.write {
                var usernameCharacter = "cocant"
                var nameCharacter = "Jironiah"
                var levelCharacter = 1
                var inspirationCharacter = true
                var backgroundCharacter = "Guardian"
                var raceCharacter = EmRaceCh().apply {
                    name = "Humano"
                    speed = "1"
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
                    quantity = "1"
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
                    name = "Fighter"
                    subclass = "Champion"
                }
                var experiencePoints = 1
                var statsCharacter = EmStatCh().apply {
                    name = "Fuerza"
                    value = 1
                }
                var skillProficiency = EmSkillProficiencyCh().apply {
                    name = "Inteligencia"
                    bonus = 1.0
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
                    userName = usernameCharacter
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