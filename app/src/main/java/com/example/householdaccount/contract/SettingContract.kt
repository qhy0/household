package com.example.householdaccount.contract

import com.example.householdaccount.data.ItemData
import net.konyan.frameworkandextensions.framework.BasePresenter
import net.konyan.frameworkandextensions.framework.BaseView

interface SettingContract {
    interface Presenter: BasePresenter {

        fun deleteAllItemData()
    }
    interface View: BaseView<Presenter> {
        fun showItemList(list:List<ItemData>)
        fun showItemMessage()
    }
}