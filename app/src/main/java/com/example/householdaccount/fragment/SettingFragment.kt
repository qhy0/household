package com.example.householdaccount.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.householdaccount.R
import com.example.householdaccount.contract.SettingContract
import com.example.householdaccount.data.ItemData
import net.konyan.frameworkandextensions.extension.setup

class SettingFragment: Fragment(),SettingContract.View{
    override lateinit  var presenter: SettingContract.Presenter
    lateinit var textMessage: TextView
    lateinit var rvList: RecyclerView
    lateinit var imgDelete: ImageButton

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
        var view= inflater.inflate(R.layout.fragment_setting,container,false)
        with(view){
             textMessage= view.findViewById<TextView>(R.id.item_message)
             rvList= view.findViewById<RecyclerView>(R.id.rv_list)
            imgDelete= view.findViewById<ImageButton>(R.id.img_delete)
        }

        imgDelete.setOnClickListener {
            context?.let { it1 ->
                AlertDialog.Builder(it1)
                    .setMessage("Are you sure delete all item?")
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        presenter.deleteAllItemData()
                        rvList.visibility= View.GONE
                    }
                    .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .show()
            }

        }

        return view
    }

    override fun showItemMessage() {
        textMessage.visibility= View.VISIBLE
    }

    override fun showItemList(list: List<ItemData>) {
        textMessage.visibility= View.GONE
        rvList.visibility= View.VISIBLE
        imgDelete.visibility= View.VISIBLE
        rvList.setup(list,R.layout.layout_item_data,{item->
            var tvName= findViewById<TextView>(R.id.tv_item)
            var tvPrice= findViewById<TextView>(R.id.tv_price)
            var tvDate= findViewById<TextView>(R.id.tv_date)
            tvName.text= item.name
            tvPrice.text= item.price
            tvDate.text= item.date

        })
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    companion object{
        fun newInstance()= SettingFragment()
    }
}