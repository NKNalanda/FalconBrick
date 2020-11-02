package com.nknalanda.falconbrick.datamodel

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BlockModel: RealmObject() {
    @PrimaryKey
    var id: Long? = null
    @SerializedName("block_name")
    var block_name : String? = null
    @SerializedName("units")
    var units : RealmList<UnitModel>? = null
}