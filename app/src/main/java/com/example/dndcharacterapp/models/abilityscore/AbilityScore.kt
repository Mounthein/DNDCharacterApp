package com.example.dndcharacterapp.models.abilityscore

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class AbilityScore: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var description: RealmList<String> = realmListOf()
    @Index
    var fullName: String?= null
    var index: String? = null
    var name: String? = null
    var skills: RealmList<Skill>? = realmListOf()
}
