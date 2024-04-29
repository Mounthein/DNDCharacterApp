package com.example.dndcharacterapp.models.level

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class ClassSpecific : EmbeddedRealmObject{
    val actionSurge: Int? = null
    val arcaneRecoveryLevel: Int? = null
    val auraRange: Int? = null
    val bardicInspirationDie: Int? = null
    val brutalCriticalDie: Int? = null
    val channelDivinityCharges: Int? = null
    val creatingSpellSlots: RealmList<CreatingSpellSlot> = realmListOf()
    val destroyUndeadCr: Double? = null
    val extraAttacks: Int? = null
    val favoredEnemies: Int? = null
    val favoredTerrain: Int? = null
    val indomitableUses: Int? = null
    val invocationsKnown: Int? = null
    val kiPoints: Int? = null
    val magicSecretsMax5: Int? = null
    val magicSecretsMax7: Int? = null
    val magicSecretsMax9: Int? = null
    val martialArts: MartialArts? = null
    val metamagicKnown: Int? = null
    val mysticArcanumLevel6: Int? = null
    val mysticArcanumLevel7: Int? = null
    val mysticArcanumLevel8: Int? = null
    val mysticArcanumLevel9: Int? = null
    val rageCount: Int? = null
    val rageDamaageBonus: Int? = null
    val sneakAttack: SneakAttack? = null
    val songOfRestDie: Int? = null
    val sorceryPoints: Int? = null
    val unarmoredMovement: Int? = null
    val wildShapeFly: Boolean? = null
    val wildShapeMaxCr: Double? = null
    val wildShapeSwim: Boolean? = null
}