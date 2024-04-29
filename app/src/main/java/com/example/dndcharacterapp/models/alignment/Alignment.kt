package com.example.dndcharacterapp.models.alignment

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Alignment: RealmObject{
    @PrimaryKey
    val abbreviation: String? = null
    val description: String? = null
    val id: ObjectId = ObjectId()
    val index: String? = null
    val name: String? = null
}