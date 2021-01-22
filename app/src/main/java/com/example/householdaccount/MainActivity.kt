package com.example.householdaccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.householdaccount.fragment.AddFragment
import com.example.householdaccount.fragment.HistoryFragment
import com.example.householdaccount.fragment.SettingFragment
import com.example.householdaccount.presenter.AddPresenter
import com.example.householdaccount.presenter.HistoryPresenter
import com.example.householdaccount.presenter.SettingPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigation: BottomNavigationView
    lateinit var coreRepository: CoreRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation= findViewById(R.id.bottom_nav)
        coreRepository= CoreRepository(this)
        var presenter= AddFragment.newInstance()
        openFragment(presenter)
        AddPresenter(presenter,coreRepository)
        bottomNavigation.setOnNavigationItemSelectedListener {
            var rValue= false
            when(it.itemId){
               R.id.add ->{
                   var presenter= AddFragment.newInstance()
                   openFragment(presenter)
                   AddPresenter(presenter,coreRepository)
                   rValue= true
               }
                R.id.history->{
                    var presenter= HistoryFragment.newInstance()
                    openFragment(presenter)
                    HistoryPresenter(presenter,coreRepository)
                    rValue= true
                }
                R.id.setting->{
                    var presenter= SettingFragment.newInstance()
                    openFragment(presenter)
                    SettingPresenter(presenter,coreRepository)
                    rValue= true
                }
                else -> {
                    rValue= false
                }
            }
            rValue
        }
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }
}
