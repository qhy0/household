package com.example.householdaccount

import android.content.Context
import com.example.householdaccount.data.ItemData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CoreRepository(var context: Context) {
    private val prefName = context.packageName
    private val saveItem="Item"


     fun saveItemInfo(infoList: List<ItemData>) {
        val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(saveItem, Gson().toJson(infoList))
        editor.apply()    }

     fun getItemInfo(): ArrayList<ItemData> {
        val emptyList = Gson().toJson(ArrayList<ItemData>())
        val sharedPreferences=context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return Gson().fromJson(
            sharedPreferences.getString(saveItem, emptyList),
            object : TypeToken<ArrayList<ItemData>>() {
            }.type
        )
    }

    fun removeItemInfo() {
        val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove(saveItem)
        editor.apply()    }

}