package com.example.householdaccount.data

data class ItemData(
    var name: String,
    var price: String,
    var date: String){
    constructor(): this("","","")
}