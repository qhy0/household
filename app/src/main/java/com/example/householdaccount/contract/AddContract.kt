package com.example.householdaccount.contract

import com.example.householdaccount.data.ItemData
import net.konyan.frameworkandextensions.framework.BasePresenter
import net.konyan.frameworkandextensions.framework.BaseView

interface AddContract {
    interface Presenter: BasePresenter{
        fun saveData(name: String,price: String, date: String)
        fun deleteItem(item: ItemData)

    }
    interface View: BaseView<Presenter>{
        fun showItemList(itemList: List<ItemData>)
    }
}