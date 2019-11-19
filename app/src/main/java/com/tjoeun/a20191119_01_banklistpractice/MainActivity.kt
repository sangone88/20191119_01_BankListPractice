package com.tjoeun.a20191119_01_banklistpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tjoeun.a20191119_01_banklistpractice.adapters.BankAdapter
import com.tjoeun.a20191119_01_banklistpractice.datas.Bank
import com.tjoeun.a20191119_01_banklistpractice.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivety() {

    var bankList = ArrayList<Bank>()
    var bankAdapter:BankAdapter? = null

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

        bankAdapter = BankAdapter(mContext, bankList)
        bankListView.adapter = bankAdapter


    }

    fun getBanksFromServer() {
        ServerUtil.getRequestBankList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("응답확인", json.toString())
                val code = json.getInt("code")

                if (code == 200) {
                    val data = json.getJSONObject("data")
                    val banks = data.getJSONArray("banks")

                    for (i in 0..banks.length()-1) {
                        val bankJSONObject = banks.getJSONObject(i)
                        val bankData = Bank.getBankFromJsonObject(bankJSONObject)
                        bankList.add(bankData)
                    }

                    runOnUiThread {
                        bankAdapter?.notifyDataSetChanged()
                    }

                }
                else{
//                    this@MainActivity -> mContext
                    Toast.makeText(mContext, "서버 통신에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }


}
