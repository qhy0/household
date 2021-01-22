package com.example.householdaccount.presenter

import android.util.Log
import com.example.householdaccount.CoreRepository
import com.example.householdaccount.contract.AddContract
import com.example.householdaccount.data.ItemData

class AddPresenter(var view: AddContract.View,var coreRepository: CoreRepository):AddContract.Presenter {
    init {
        view.presenter= this
    }
    override fun start() {
        var itemList= coreRepository.getItemInfo()
        if (itemList.size>0){
            view.showItemList(itemList)
        }else{

        }
    }

    override fun deleteItem(item: ItemData) {
        var itemList= coreRepository.getItemInfo()
        var itemData=ItemData()
        var iteratorList= itemList.iterator()
        while (iteratorList.hasNext()){
            var testitem= iteratorList.next()
            if (testitem.name== item.name && testitem.date== item.date && testitem.price== item.price  ){
                itemData= item
                iteratorList.remove()
                /*list.remove(
                    item
                )*/
            }else{
                Log.d("SizeCondition",item.name)
            }
        }
        coreRepository.saveItemInfo(itemList)
        var list= coreRepository.getItemInfo()
        view.showItemList(list)//view.removeDownloadedData(downloadData)
        //coreRepository.removeItemInfo()
        //itemList.saveItemData(itemList)
        //view.showDownloadList(itemList)
    }

    override fun saveData(name: String, price: String, date: String) {
        var itemList=coreRepository.getItemInfo()
        itemList.add(ItemData(name,price,date))
        coreRepository.saveItemInfo(itemList)
        if (itemList.size>0){
            view.showItemList(itemList)
        }else{

        }
    }
}