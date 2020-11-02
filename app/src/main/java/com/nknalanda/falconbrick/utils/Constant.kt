package com.nknalanda.falconbrick.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

object Constant {

    fun allotEachTabWithEqualWidth(tabLayout: TabLayout) {
        val slidingTabStrip = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabLayout.tabCount) {
            val tab = slidingTabStrip.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = 1f
            tab.layoutParams = layoutParams
        }
    }

    //To get json object from json file
    fun getJsonArrFromAssets(context: Context, fileName: String?): JSONArray? {
        val jsonString: String
        jsonString = try {
            val inputStream: InputStream = context.assets.open(fileName!!)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return JSONArray(jsonString)
    }
}