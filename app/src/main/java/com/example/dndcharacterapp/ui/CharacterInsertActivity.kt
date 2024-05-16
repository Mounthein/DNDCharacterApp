package com.example.dndcharacterapp.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.character.HitDie
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
import com.example.dndcharacterapp.models.classes.ClassesItem
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.feature.Feature
import com.example.dndcharacterapp.models.language.Language
import com.example.dndcharacterapp.models.proficiency.Proficiency
import com.example.dndcharacterapp.models.race.Race
import com.example.dndcharacterapp.models.spell.Spell
import com.example.dndcharacterapp.models.trait.Trait
import com.example.dndcharacterapp.realm.MainViewModel
import com.example.dndcharacterapp.ui.theme.DNDCharacterAppTheme
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList

class CharacterInsertarActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDCharacterAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val races = CrudApi().getRaceList()?.toList()
                    val alignments = CrudApi().getAlignmentList()?.toList()
                    val classes = CrudApi().getClassesList()?.toList()
                    val languages = CrudApi().getLanguageList()?.toList()
                    val proficiencies = CrudApi().getProficiencyList()?.toList()
                    val equipment = CrudApi().getEquipmentList()?.toList()
                    val features = CrudApi().getFeatureList()?.toList()
                    val traits = CrudApi().getTraitList()?.toList()
                    val spells = CrudApi().getSpellList()?.toList()
                    if (races != null && alignments != null && classes != null && languages != null && proficiencies != null && equipment != null && features != null && traits != null && spells != null) {
                        MostrarComponentes(
                            races,
                            alignments,
                            classes,
                            languages,
                            proficiencies,
                            equipment,
                            features,
                            traits,
                            spells,
                            viewModel
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarComponentes(
    racesList: List<Race>?,
    alignmentsList: List<com.example.dndcharacterapp.models.alignment.Alignment>?,
    classeslist: List<ClassesItem>?,
    languagesList: List<Language>?,
    proficienciesList: List<Proficiency>?,
    equipmentList: List<Equipment>?,
    featuresList: List<Feature>?,
    traitsList: List<Trait>?,
    spellList: List<Spell>?,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var expandedClasses by remember { mutableStateOf(false) }
    var expandedOtherProficiency by remember { mutableStateOf(false) }
    var expandedEquipment by remember { mutableStateOf(false) }
    var expandedLanguages by remember { mutableStateOf(false) }
    var expandedFeatures by remember { mutableStateOf(false) }
    var expandedTraits by remember { mutableStateOf(false) }
    var expandedPreparedSpells by remember { mutableStateOf(false) }
    var expandedKnownSpells by remember { mutableStateOf(false) }
    val characterInsertar: CharacterRealm? = null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        //Name
        val name = Mostrar1TextField(textoMostrar = "Name")
        characterInsertar?.name = name

        //Level
        val level = Mostrar1TextField(textoMostrar = "Level")
        characterInsertar?.level = level.toInt()

        //Inspiration
        val inspiration = MostrarCheckBox()
        characterInsertar?.inspiration = inspiration

        //Background
        val background = Mostrar1TextField(textoMostrar = "Background")
        characterInsertar?.background = background

        //Race
        val racesName: MutableList<String> = mutableListOf()
        racesList!!.forEach {
            racesName.add(it.name)
        }
        val race = MostrarDropDowns(list1 = racesName, "Race")
        val raceInsertar: EmRaceCh? = null
        val raceFiltrado = racesList.filter { it.name == race }.firstOrNull()
        raceInsertar?.name = raceFiltrado?.name
        raceInsertar?.size = raceFiltrado?.size
        raceInsertar?.speed = raceFiltrado?.speed
        raceInsertar?.subrace = raceFiltrado?.subraces?.get(0)?.name
        characterInsertar?.race = raceInsertar


        //Alignment
        val alignmentsName: MutableList<String> = mutableListOf()
        alignmentsList!!.forEach {
            alignmentsName.add(it.name)
        }
        val alignment = MostrarDropDowns(list1 = alignmentsName, "Alignment")
        val alignmentInsertar: EmAlignmentCh? = null
        val alignmentFiltrado = alignmentsList.filter { it.name == race }.firstOrNull()
        alignmentInsertar?.name = alignmentFiltrado?.name
        alignmentInsertar?.abbreviation = alignmentFiltrado?.abbreviation
        characterInsertar?.alignment = alignmentInsertar

        //HitPoint
        Text(text = "HitPoints")
        val inputvalueHitPointsMaximum = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueHitPointsCurrent = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueHitPointsTemporary = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitPointsMaximum.value,
                    onValueChange = { inputvalueHitPointsMaximum.value = it },
                    label = { Text("Maximum") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitPointsCurrent.value,
                    { inputvalueHitPointsCurrent.value = it },
                    label = { Text("Current") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitPointsTemporary.value,
                    { inputvalueHitPointsTemporary.value = it },
                    label = { Text("Temporary") })
            }
        }
        val HitPointsMaximum = inputvalueHitPointsMaximum.value.text
        val HitPointsCurrent = inputvalueHitPointsCurrent.value.text
        val HitPointsTemporary = inputvalueHitPointsTemporary.value.text
        //Esto es lo que se inserta
        val hitPointsCh: EmHitPointsCh? = null

        hitPointsCh?.maximum = HitPointsMaximum.toInt()
        hitPointsCh?.current = HitPointsCurrent.toInt()
        hitPointsCh?.temporary = HitPointsTemporary.toInt()

        characterInsertar?.hitPoints = hitPointsCh

        //HitDie
        Text(text = "HitDie")
        val inputvalueHitDieType = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueHitDieQuantity = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitDieType.value,
                    onValueChange = { inputvalueHitDieType.value = it },
                    label = { Text("Type") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueHitDieQuantity.value,
                    onValueChange = { inputvalueHitDieQuantity.value = it },
                    label = { Text("Quantity") })
            }
        }
        val HitDieType = inputvalueHitDieType.value.text
        val HitDieQuantity = inputvalueHitDieQuantity.value.text
        //Esto es lo que se inserta
        val hitDieCh: EmHitDieCh? = null
        hitDieCh?.type = HitDieType
        hitDieCh?.type = HitDieQuantity

        val hitDieListInsertar: RealmList<EmHitDieCh>? = null
        if (hitDieCh != null) {
            hitDieListInsertar?.add(hitDieCh)
        }
        characterInsertar?.hit_die = hitDieListInsertar

        //DeathSaves
        Text(text = "DeathSaves")
        val inputvalueDeathSavesSuccess = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueDeathSavesFailures = remember { mutableStateOf(TextFieldValue()) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueDeathSavesSuccess.value,
                    onValueChange = { inputvalueDeathSavesSuccess.value = it },
                    label = { Text("Success") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueDeathSavesFailures.value,
                    onValueChange = { inputvalueDeathSavesFailures.value = it },
                    label = { Text("Failures") })
            }
        }
        val DeathSavesSuccess = inputvalueDeathSavesSuccess.value.text
        val DeathSavesFailures = inputvalueDeathSavesFailures.value.text
        //Esto es lo que se inserta
        val deathSavesCh: EmDeathSavesCh? = null
        deathSavesCh?.success = DeathSavesSuccess.toInt()
        deathSavesCh?.failures = DeathSavesFailures.toInt()

        characterInsertar?.death_saves = deathSavesCh

        //ArmorClass
        Text(text = "ArmorClass")
        val inputvalueArmorClassName = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueArmorClassType = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueArmorClassValue = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueArmorClassName.value,
                    onValueChange = { inputvalueArmorClassName.value = it },
                    label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueArmorClassType.value,
                    onValueChange = { inputvalueArmorClassType.value = it },
                    label = { Text("Type") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueArmorClassValue.value,
                    onValueChange = { inputvalueArmorClassValue.value = it },
                    label = { Text("Value") })
            }
        }

        val ArmorClassName = inputvalueArmorClassName.value.text
        val ArmorClassType = inputvalueArmorClassType.value.text
        val ArmorClassValue = inputvalueArmorClassValue.value.text
        //Esto es lo que se inserta
        val armorClassCh: EmArmorClassCh? = null
        armorClassCh?.name = ArmorClassName
        armorClassCh?.type = ArmorClassType
        armorClassCh?.value = ArmorClassValue.toInt()

        characterInsertar?.armor_Class = armorClassCh

        //Classes
        val classesName: MutableList<String> = mutableListOf()
        classeslist!!.forEach {
            classesName.add(it.name)
        }
        val classes = MostrarDropDowns(list1 = classesName, textoMostrar = "Classes")

        val classeListInsertar: RealmList<EmClassItemCh>? = null
        val classeFiltrar = classeslist.filter { it.name == classes }.firstOrNull()
        val classeInsertar: EmClassItemCh? = null
        classeInsertar?.name = classeFiltrar?.name
        classeInsertar?.subclass = classeFiltrar?.subclasses?.get(0)?.name
        classeListInsertar?.add(classeInsertar!!)
        characterInsertar?.classes = classeListInsertar

        //Stats
        Text(
            text = "Stats", modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
        )
        val inputvalueStatsName = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueStatsValue = remember { mutableStateOf(TextFieldValue()) }
        // TextFields para "Name" y "Value"
        Row(Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueStatsName.value,
                    onValueChange = { inputvalueStatsName.value = it },
                    label = { Text("Name") })
            }

            Spacer(modifier = Modifier.width(4.dp))

            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueStatsValue.value,
                    onValueChange = { inputvalueStatsValue.value = it },
                    label = { Text("Value") })
            }
        }

        val StatsName = inputvalueStatsName.value.text
        val StatsValue = inputvalueStatsValue.value.text
        val statsCh: EmStatCh? = null
        statsCh?.name = StatsName
        statsCh?.value = StatsValue.toInt()

        val statsListInsertar: RealmList<EmStatCh>? = null
        statsListInsertar?.add(statsCh!!)

        characterInsertar?.stats = statsListInsertar

        //SkillProficiencies
        Text(text = "SkillProficiencies")
        val inputvalueSkillProficienciesName = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueSkillProficienciesBonus = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSkillProficienciesName.value,
                    onValueChange = { inputvalueSkillProficienciesName.value = it },
                    label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSkillProficienciesBonus.value,
                    onValueChange = { inputvalueSkillProficienciesBonus.value = it },
                    label = { Text("Bonus") })
            }
        }

        val SkillProficienciesName = inputvalueSkillProficienciesName.value.text
        val SkillProficienciesBonus = inputvalueSkillProficienciesBonus.value.text
        val skillProficiencyCh: EmSkillProficiencyCh? = null
        skillProficiencyCh?.name = SkillProficienciesName
        skillProficiencyCh?.bonus = SkillProficienciesBonus.toInt()

        val skillProficiencyListInsertar: RealmList<EmSkillProficiencyCh>? = null
        skillProficiencyListInsertar?.add(skillProficiencyCh!!)

        characterInsertar?.skill_proficiencies = skillProficiencyListInsertar

        //Languages
        val languagesName: MutableList<String> = mutableListOf()
        languagesList!!.forEach {
            languagesName.add(it.name)
        }
        val languages = MostrarDropDowns(list1 = languagesName, textoMostrar = "Languages")

        val languagesFiltrar = languagesList.filter { it.name == languages }.firstOrNull()
        val languagesInsertar: EmLanguageCh? = null
        languagesInsertar?.name = languagesFiltrar?.name
        languagesInsertar?.type = languagesFiltrar?.type
        val languagesListInsertar: RealmList<EmLanguageCh>? = null
        languagesListInsertar?.add(languagesInsertar!!)

        characterInsertar?.languages = languagesListInsertar

        //OtherProficiencies
        val otherProficienciesName: MutableList<String> = mutableListOf()
        proficienciesList!!.forEach {
            otherProficienciesName.add(it.name)
        }
        val otherProficiencies =
            MostrarDropDowns(list1 = otherProficienciesName, textoMostrar = "Other Proficiencies")

        val otherProficienciesFiltrar =
            proficienciesList.filter { it.name == otherProficiencies }.firstOrNull()

        val otherProficienciesInsertar: EmProficiencyCh? = null
        otherProficienciesInsertar?.name = otherProficienciesFiltrar?.name
        otherProficienciesInsertar?.type = otherProficienciesFiltrar?.type

        val otherProficienciesInsertarList: RealmList<EmProficiencyCh>? = null
        otherProficienciesInsertarList?.add(otherProficienciesInsertar!!)

        characterInsertar?.other_proficiencies = otherProficienciesInsertarList

        //Equipment
        val equipmentName: MutableList<String> = mutableListOf()
        equipmentList!!.forEach {
            equipmentName.add(it.name)
        }

        val equipment = MostrarDropDowns(list1 = equipmentName, textoMostrar = "Equipment")

        val equipmentFiltrar = equipmentList.filter { it.name == equipment }.firstOrNull()

        val equipmentInsertar: EmEquipmentCh? = null
        equipmentInsertar?.name = equipmentFiltrar?.name
        equipmentInsertar?.equipment_category = equipmentFiltrar?.equipmentCategory?.name
        equipmentInsertar?.quantity = equipmentFiltrar?.quantity

        val equipmentInsertarList: RealmList<EmEquipmentCh>? = null
        equipmentInsertarList?.add(equipmentInsertar!!)

        characterInsertar?.equipment = equipmentInsertarList

        //CoinPouch
        Text(text = "CoinPouch")
        val inputvalueCoinPouchName = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueCoinPouchQuantity = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueCoinPouchName.value,
                    onValueChange = { inputvalueCoinPouchName.value = it },
                    label = { Text("Name") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueCoinPouchQuantity.value,
                    onValueChange = { inputvalueCoinPouchQuantity.value = it },
                    label = { Text("Quantity") })
            }
        }

        val coinPouchName = inputvalueCoinPouchName.value.text
        val coinPouchQuantity = inputvalueCoinPouchQuantity.value.text

        val coinPouch: EmCoinCh? = null
        coinPouch?.name = coinPouchName
        coinPouch?.quantity = coinPouchQuantity.toInt()

        val coinPouchInsertarList: RealmList<EmCoinCh>? = null
        coinPouchInsertarList?.add(coinPouch!!)

        characterInsertar?.coin_pouch = coinPouchInsertarList

        //Features
        val featuresName: MutableList<String> = mutableListOf()
        featuresList!!.forEach {
            featuresName.add(it.name)
        }
        val features = MostrarDropDowns(list1 = featuresName, textoMostrar = "Features")

        val featuresFilter = featuresList.filter { it.name == features }.firstOrNull()

        val featuresInsertar: EmFeatureCh? = null
        featuresInsertar?.name = featuresFilter?.name
        featuresInsertar?.description = featuresFilter?.description?.firstOrNull()

        val featuresInsertarList: RealmList<EmFeatureCh>? = null
        featuresInsertarList?.add(featuresInsertar!!)

        characterInsertar?.features = featuresInsertarList

        //Traits
        val traitsName: MutableList<String> = mutableListOf()
        traitsList!!.forEach {
            traitsName.add(it.name)
        }
        val traits = MostrarDropDowns(list1 = traitsName, textoMostrar = "Traits")

        val traitsFilter = traitsList.filter { it.name == traits }.firstOrNull()

        val traitsInsertar: EmTraitCh? = null
        traitsInsertar?.name = traitsFilter?.name
        traitsInsertar?.description = traitsFilter?.description?.firstOrNull()

        val traitsInsertarList: RealmList<EmTraitCh>? = null
        traitsInsertarList?.add(traitsInsertar!!)

        characterInsertar?.traits = traitsInsertarList

        //SpellAbilities
        Text(text = "SpellAbilities")
        val inputvalueSpellAbilitiesSpellcastingAbility =
            remember { mutableStateOf(TextFieldValue()) }
        val inputvalueSpellAbilitiesSpellSaveDC = remember { mutableStateOf(TextFieldValue()) }
        val inputvalueSpellAbilitiesSpellAttackBonus = remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSpellAbilitiesSpellcastingAbility.value,
                    onValueChange = { inputvalueSpellAbilitiesSpellcastingAbility.value = it },
                    label = { Text("SpellcastingAbility") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSpellAbilitiesSpellSaveDC.value,
                    onValueChange = { inputvalueSpellAbilitiesSpellSaveDC.value = it },
                    label = { Text("SpellSaveDC") })
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                TextField(value = inputvalueSpellAbilitiesSpellAttackBonus.value,
                    onValueChange = { inputvalueSpellAbilitiesSpellAttackBonus.value = it },
                    label = { Text("SpellAttackBonus") })
            }
        }

        val SpellAbilitiesSpellcastingAbility =
            inputvalueSpellAbilitiesSpellcastingAbility.value.text
        val SpellAbilitiesSpellSaveDC = inputvalueSpellAbilitiesSpellSaveDC.value.text
        val SpellAbilitiesSpellAttackBonus = inputvalueSpellAbilitiesSpellAttackBonus.value.text
        val spellAbilityCh: EmSpellAbilityCh? = null
        spellAbilityCh?.spellcasting_ability = SpellAbilitiesSpellcastingAbility
        spellAbilityCh?.spell_save_dc = SpellAbilitiesSpellSaveDC.toInt()
        spellAbilityCh?.spell_attack_bonus = SpellAbilitiesSpellAttackBonus.toInt()

        val spellAbilityInsertarList : RealmList<EmSpellAbilityCh>? = null
        spellAbilityInsertarList?.add(spellAbilityCh!!)

        characterInsertar?.spell_abilities = spellAbilityInsertarList

        //PreparedSpells
        val preparedSpellsName: MutableList<String> = mutableListOf()
        spellList!!.forEach {
            preparedSpellsName.add(it.name)
        }
        val preparedSpells =
            MostrarDropDowns(list1 = preparedSpellsName, textoMostrar = "PreparedSpells")

        val preparedSpellsFilter = spellList.filter { it.name == preparedSpells }.firstOrNull()

        val preparedSpellsInsertar : EmSpellCh? = null
        preparedSpellsInsertar?.name = preparedSpellsFilter?.name
        preparedSpellsInsertar?.casting_time = preparedSpellsFilter?.castingTime
        preparedSpellsInsertar?.level = preparedSpellsFilter?.level
        preparedSpellsInsertar?.description = preparedSpellsFilter?.description?.firstOrNull()
        preparedSpellsInsertar?.school = preparedSpellsFilter?.from?.name
        preparedSpellsInsertar?.duration = preparedSpellsFilter?.duration

        val preparedSpellsInsertarList : RealmList<EmSpellCh>? = null
        preparedSpellsInsertarList?.add(preparedSpellsInsertar!!)

        characterInsertar?.prepared_spells = preparedSpellsInsertarList

        //KnownSpells
        val knownSpellsName: MutableList<String> = mutableListOf()
        spellList!!.forEach {
            knownSpellsName.add(it.name)
        }
        val knownSpells = MostrarDropDowns(list1 = knownSpellsName, textoMostrar = "KnownSpells")

        val knownSpellsFilter = spellList.filter { it.name == knownSpells }.firstOrNull()

        val knownSpellsInsertar : EmSpellCh? = null
        knownSpellsInsertar?.name = knownSpellsFilter?.name
        knownSpellsInsertar?.casting_time = knownSpellsFilter?.castingTime
        knownSpellsInsertar?.level = knownSpellsFilter?.level
        knownSpellsInsertar?.description = knownSpellsFilter?.description?.firstOrNull()
        knownSpellsInsertar?.school = knownSpellsFilter?.from?.name
        knownSpellsInsertar?.duration = knownSpellsFilter?.duration

        val knownSpellsInsertarList : RealmList<EmSpellCh>? = null
        knownSpellsInsertarList?.add(knownSpellsInsertar!!)

        characterInsertar?.known_spells = knownSpellsInsertarList

        //SavingThrows
        val savingThrows = MostrarTextFieldArray("SavingThrows")

        characterInsertar?.saving_throws = savingThrows

        //Exhaustion
        characterInsertar?.exhaustion = Mostrar1TextField(textoMostrar = "Exhaustion").toInt()

        //ExperienciePoints
        characterInsertar?.experience_Points =
            Mostrar1TextField(textoMostrar = "ExperienciePoints").toInt()

        //PassiveWisdom
        characterInsertar?.passive_wisdom =
            Mostrar1TextField(textoMostrar = "PassiveWisdom").toInt()

        //Initiative
        characterInsertar?.initiative = Mostrar1TextField(textoMostrar = "Initiative").toInt()

        //Speed
        characterInsertar?.speed = Mostrar1TextField(textoMostrar = "Speed").toInt()

        //ProficiencyBonus
        characterInsertar?.proficiency_bonus =
            Mostrar1TextField(textoMostrar = "ProficiencyBonus").toInt()

        //PersonalityTraits
        characterInsertar?.personality_traits =
            Mostrar1TextField(textoMostrar = "PersonalityTraits")

        //Ideals
        characterInsertar?.ideals = Mostrar1TextField(textoMostrar = "Ideals")

        //Bonds
        characterInsertar?.bonds = Mostrar1TextField(textoMostrar = "Bonds")

        //Claws
        characterInsertar?.flaws = Mostrar1TextField(textoMostrar = "Flaws")

        //CharacterBackstory
        characterInsertar?.character_backstory =
            Mostrar1TextField(textoMostrar = "CharacterBackstory")

        //AlliesOrganizations
        characterInsertar?.allies_organizations =
            Mostrar1TextField(textoMostrar = "AlliesOrganizations")

        //Symbol
        characterInsertar?.symbol = Mostrar1TextField(textoMostrar = "Symbol")

        //Boton añadir character
        BotonAnadir(
            context = context, characterInsertar = characterInsertar!!, viewModel = viewModel
        )
    }
}

//Esto mostrará un textfield para que se escriba el string
@Composable
private fun Mostrar1TextField(textoMostrar: String): String {
    val context = LocalContext.current
    val res = remember { mutableStateOf(("")) }
    Text(text = textoMostrar)
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            TextField(value = inputValue.value,
                onValueChange = { inputValue.value = it },
                label = { Text(textoMostrar) })
        }
    }
    Spacer(modifier = Modifier.width(8.dp))
    return inputValue.value.text
}

//Esto mostrará dos exposeddropdownmenusbox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MostrarDropDowns(list1: List<String>, textoMostrar: String): String {
    val context = LocalContext.current
    var expanded1 by remember { mutableStateOf(false) }
    var selectedText1 by remember { mutableStateOf(list1[0]) }

    Text(text = textoMostrar)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            ExposedDropdownMenuBox(expanded = expanded1, onExpandedChange = {
                expanded1 = it
            }) {
                TextField(value = selectedText1,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded1) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(expanded = expanded1,
                    onDismissRequest = { expanded1 = false }) {
                    list1.forEach { item ->
                        DropdownMenuItem(text = { Text(text = item) }, onClick = {
                            selectedText1 = item
                            expanded1 = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            }
        }
    }
    return selectedText1
}

@Composable
private fun MostrarTextFieldArray(textoMostrar: String): RealmList<String> {
    Text(text = textoMostrar)
    var stringArray by rememberSaveable { mutableStateOf(mutableListOf("")) }
    Row(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {

            Column {
                stringArray.forEachIndexed { index, item ->
                    OutlinedTextField(value = item, onValueChange = { newValue ->
                        val newList = stringArray.toMutableList()
                        newList[index] = newValue
                        stringArray = newList
                    }, label = {
                        Text(
                            "Saving Throws $index",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }, colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    )
                    )
                }
                Button(onClick = {
                    val newList = stringArray.toMutableList()
                    newList.add("")
                    stringArray = newList
                }) {
                    Text("Afegir element", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(onClick = {
                    val newList = stringArray.toMutableList()
                    newList.remove("")
                    stringArray = newList
                }) {
                    Text("Eliminar element", color = MaterialTheme.colorScheme.onBackground)
                }
                //Con este código se almacena la información
                Button(onClick = {
                    val stringArrayArray: Array<String> = stringArray.toTypedArray()
                    // Use the stringArrayArray variable as a String array
                    println(stringArrayArray.contentToString())
                }) {
                    Text("Save to String array", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
    return stringArray.toRealmList()
}

@Composable
private fun MostrarCheckBox(): Boolean {
    val inspirationRemember = remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Inspiration")
        Checkbox(checked = inspirationRemember.value,
            onCheckedChange = { inspirationRemember.value = it })
    }
    return inspirationRemember.value
}

@Composable
private fun BotonAnadir(
    context: Context, characterInsertar: CharacterRealm, viewModel: MainViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            //Almacenar todos los elementos
            Button(onClick = {
                val data = try {
                    viewModel.insertNewCharacter(characterInsertar)
                    Toast.makeText(
                        context, "Has afegit character", Toast.LENGTH_LONG
                    ).show()
                } catch (error: Throwable) {
                    Toast.makeText(
                        context, "No se ha podido insertar el character", Toast.LENGTH_LONG
                    ).show()
                    null
                }
            }) {
                Text("Afegir Character", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
}