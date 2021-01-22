package com.example.householdaccount.contract

import com.example.householdaccount.data.ItemData
import net.konyan.frameworkandextensions.framework.BasePresenter
import net.konyan.frameworkandextensions.framework.BaseView

interface HistoryContract{
    interface Presenter: BasePresenter {
        fun getDataList(year: String,month: String)

    }
    interface View: BaseView<Presenter> {
        fun getDate()
        fun showHistoryData(list: List<ItemData>)
        fun sumAmount(list: List<Int>)
        fun showMonth(name: String)
    }
}