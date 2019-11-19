package com.tjoeun.a20191119_01_banklistpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjoeun.a20191119_01_banklistpractice.datas.Bank

class MainActivity : BaseActivety() {

    var bankList = ArrayList<Bank>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvent()
        setValuse()
    }

    override fun setupEvent() {

    }

    override fun setValuse() {
        getBanksFromServer()

    }

    fun getBanksFromServer() {

    }


}
