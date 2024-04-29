package com.example.dndcharacterapp.models.alignment

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Alignment: RealmObject{
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var abbreviation: String? = null
    var description: String? = null
    var index: String? = null
    var name: String? = null
}