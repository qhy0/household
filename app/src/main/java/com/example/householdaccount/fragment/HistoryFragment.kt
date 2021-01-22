package com.example.householdaccount.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.householdaccount.R
import com.example.householdaccount.contract.HistoryContract
import com.example.householdaccount.data.ItemData
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.item_history_data.view.*
import net.konyan.frameworkandextensions.extension.setup
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment: Fragment(),HistoryContract.View {
   override lateinit var presenter: HistoryContract.Presenter
    lateinit var tvTitle: TextView
    lateinit var rvHistory: RecyclerView
    lateinit var tvTotalAmount: TextView


    override fun setLoading(active: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showMessage(message: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= inflater.inflate(R.layout.fragment_history,container,false)
        with(view){
            tvTitle= view.findViewById(R.id.tv_month)
            rvHistory= view.findViewById(R.id.rv_history)
            tvTotalAmount= view.findViewById(R.id.tv_toal_amount)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun getDate() {
        var date= Calendar.getInstance()
        var smfM= SimpleDateFormat("MM")
        var smfY= SimpleDateFormat("yyyy")
        var year= smfY.format(date.time)
        var month= smfM.format(date.time)
        presenter.getDataList(year,month)
        Log.d("TimeData","$year$month")
    }

    override fun showMonth(name: String) {
        var rName= ""
        when(name){
            "1","01"->{rName="January"}
            "2","02"->{rName="February"}
            "3","03"->{rName="March"}
            "4","04"->{rName="April"}
            "5","05"->{rName="May"}
            "6","06"->{rName="June"}
            "7","07"->{rName="July"}
            "8","08"->{rName="Augest"}
            "9","09"->{rName="September"}
            "10"->{rName="October"}
            "11"->{rName="November"}
            "12"->{rName="December"}
        }
        tvTitle.text= rName
    }

    override fun showHistoryData(list: List<ItemData>) {
        rvHistory.setup(list,R.layout.item_history_data,{item->
            var date= findViewById<TextView>(R.id.tv_data_date)
            var amount= findViewById<TextView>(R.id.tv_data_amount)
            var name= findViewById<TextView>(R.id.tv_data_name)

            date.text= item.date
            amount.text= item.price
            name.text= item.name

        })

    }

    override fun sumAmount(list: List<Int>) {
        var amount= 0
        for (i in list){
            amount+= i
        }
        tvTotalAmount.text= amount.toString()
    }

    companion object{
        fun newInstance()= HistoryFragment()
    }
}