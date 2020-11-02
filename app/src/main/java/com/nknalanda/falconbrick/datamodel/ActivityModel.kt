package com.nknalanda.falconbrick.datamodel

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ActivityModel: RealmObject() {

    @SerializedName("activity_name")
    var activity_name : String? = null
    @SerializedName("activity_status")
    var activity_status : String? = null
    @SerializedName("current_user_name")
    var current_user_name : String? = null
    @SerializedName("id")
    @PrimaryKey
    var id : String? = null
    @SerializedName("step_name") var step_name : String? = null
    @SerializedName("progress") var progress : Int? = null
    @SerializedName("wf") var wf : String? = null
}