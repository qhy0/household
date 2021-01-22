package com.example.householdaccount.presenter

import com.example.householdaccount.CoreRepository
import com.example.householdaccount.contract.SettingContract

class SettingPresenter(var view: SettingContract.View,var coreRepository: CoreRepository):SettingContract.Presenter {
    init {
        view.presenter= this
    }
    override fun start() {
       showData()
    }

    private fun showData(){
        var itemList= coreRepository.getItemInfo()
        if (itemList.size>0){
            view.showItemList(itemList)
        }else{
            view.showItemMessage()
        }
    }

    override fun deleteAllItemData() {
        coreRepository.removeItemInfo()
        showData()
    }
}