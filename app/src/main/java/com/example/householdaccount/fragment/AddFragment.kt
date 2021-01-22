package com.example.householdaccount.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.householdaccount.R
import com.example.householdaccount.contract.AddContract
import com.example.householdaccount.data.ItemData
import net.konyan.frameworkandextensions.extension.setup
import net.konyan.frameworkandextensions.extension.toast
import java.time.format.DateTimeFormatter
import java.util.*


class AddFragment: Fragment(),AddContract.View{
    override lateinit var presenter: AddContract.Presenter
    lateinit var itemName: EditText
    lateinit var itemPrice: EditText
    lateinit var itemDate: TextView
    lateinit var btAdd: Button
    lateinit var rvList: RecyclerView
    var mMonth: Int=0
    var mDay:Int=0
    var mYear: Int=0

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
        var view= inflater.inflate(R.layout.fragment_add,container,false)
        with(view){
            itemName= view.findViewById(R.id.et_name)
            itemPrice= view.findViewById(R.id.et_price)
            itemDate= view.findViewById(R.id.tv_date)
            btAdd= view.findViewById(R.id.bt_add)
            rvList= view.findViewById(R.id.rv_list)
        }

        itemDate.setOnClickListener {
            showDatePickerDialog()
        }

        btAdd.setOnClickListener {
            var name= itemName.text.toString()
            var price= itemPrice.text.toString()
            var date= itemDate.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty() && date.isNotEmpty()){
                presenter.saveData(itemName.text.toString(),itemPrice.text.toString(),itemDate.text.toString())
                itemDate.text=""
                itemName.text.clear()
                itemPrice.text.clear()
            }else{
                context?.let { it1 -> "Please Enter your Item Data".toast(it1) }
            }
        }
        return view
    }

    private fun showDatePickerDialog(){

        // Get Current Date

        // Get Current Date
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]


        val datePickerDialog = context?.let {
            DatePickerDialog(
                it,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    itemDate.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                    //DateTimeFormatter()
                },
                mYear,
                mMonth,
                mDay
            )
        }
        datePickerDialog?.show()

    }


    override fun showItemList(itemList: List<ItemData>) {
        rvList.setup(itemList,R.layout.layout_item_data,{item->
            var tvName= findViewById<TextView>(R.id.tv_item)
            var tvDate= findViewById<TextView>(R.id.tv_date)
            var tvPrice= findViewById<TextView>(R.id.tv_price)
            var ivDelete= findViewById<ImageView>(R.id.iv_delete)
            ivDelete.visibility= View.VISIBLE

            tvName.text= item.name
            tvDate.text=item.date
            tvPrice.text= item.price.toString()

            ivDelete.setOnClickListener {
                presenter.deleteItem(item)
            }

        },true)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    companion object{
        fun newInstance()= AddFragment()
    }
}