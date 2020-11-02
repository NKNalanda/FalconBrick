package com.nknalanda.falconbrick.utils

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nknalanda.falconbrick.datamodel.BlockModel
import io.realm.Realm
import io.realm.RealmConfiguration
import java.lang.reflect.Type

class MyApplication : Application() {
    private var gson = Gson()
    private var realm: Realm? = null

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        realm = Realm.getDefaultInstance()
        val configuration = RealmConfiguration.Builder()
            .name("test.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(configuration)
        val modelList: ArrayList<BlockModel> = ArrayList()
        val type: Type = object : TypeToken<ArrayList<BlockModel>>() {}.type
        val arr = Constant.getJsonArrFromAssets(this, "data.json")
        if (arr != null) {
            for (i in 0 until arr.length()) {
                //modelList.addAll(gson.fromJson(arr[i].toString(), type))
                val blockModel = BlockModel()
                blockModel.block_name = "Tower Abc"
                modelList.add(blockModel)
            }
        }

        for (model in modelList) {
            try {
                //Auto increment id
                var nextId: Long
                val currentId: Number? = realm?.where(BlockModel::class.java)?.max("id")
                nextId = if (currentId == null) {
                    1
                } else {
                    currentId.toLong() + 1
                }
                model.id = nextId
                realm?.executeTransaction(Realm.Transaction { realm -> // This will create a new object in Realm or throw an exception if the
                    // object already exists (same primary key)
                    // realm.copyToRealm(obj);

                    // This will update an existing object with the same primary key
                    // or create a new object if an object with no primary key = 42
                    realm.copyToRealmOrUpdate(model)
                })
            } catch (e: Exception) {
                Log.e("RealmFailure", e.toString())
            }

        }
    }
}