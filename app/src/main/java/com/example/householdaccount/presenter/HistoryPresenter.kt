package com.example.householdaccount.presenter

import android.util.Log
import com.example.householdaccount.CoreRepository
import com.example.householdaccount.contract.HistoryContract
import com.example.householdaccount.data.ItemData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryPresenter(var view: HistoryContract.View,var coreRepository: CoreRepository): HistoryContract.Presenter {
    init {
        view.presenter= this
    }
    override fun start() {
        view.getDate()
    }

    override fun getDataList(year: String, month: String) {
        var list= coreRepository.getItemInfo()

        var itemData= ItemData()
        var iteratorList= list.iterator()
        var saveDataList= ArrayList<ItemData>()
        var saveAmountData= ArrayList<Int>()
        var monthName=""
        while (iteratorList.hasNext()) {
            var testitem = iteratorList.next()

            var calendar = Calendar.getInstance()
            var smD = SimpleDateFormat("dd/MM/yyyy")
            var simpleDateFormatM = SimpleDateFormat("MM")
            var simpleDateFormatY = SimpleDateFormat("yyyy")
            var itemDate = testitem.date
            var dd = smD.parse(itemDate)
            Log.d("TimeM", "${simpleDateFormatM.format(dd)}")
            Log.d("TimeM", "${simpleDateFormatY.format(dd)}")
            var listmonth = simpleDateFormatM.format(dd)
            var listyear = simpleDateFormatY.format(dd)


            if (listyear==year){
                if (listmonth==month){
                    saveDataList.add(testitem)
                    saveAmountData.add(testitem.price.toInt())
                    monthName=month
                }
            }else{

            }

        }

        view.showMonth(monthName)
        view.showHistoryData(saveDataList)
        view.sumAmount(saveAmountData)
    }
}