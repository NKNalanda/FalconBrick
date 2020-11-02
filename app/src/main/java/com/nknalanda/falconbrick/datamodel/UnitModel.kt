package com.nknalanda.falconbrick.datamodel

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UnitModel: RealmObject() {
    @SerializedName("activities")
    var activities : RealmList<ActivityModel>? = null
    @SerializedName("apt") var apt : Int? = null
    @SerializedName("block_id") var block_id : String? = null
    @SerializedName("block_name") var block_name : String? = null
    @SerializedName("floor") var floor : Int? = null

    @SerializedName("id")
    @PrimaryKey
    var id : String? = null
    @SerializedName("property_id") var property_id : String? = null
    @SerializedName("title") var title : String? = null
    @SerializedName("unit_type") var unit_type : String? = null
}