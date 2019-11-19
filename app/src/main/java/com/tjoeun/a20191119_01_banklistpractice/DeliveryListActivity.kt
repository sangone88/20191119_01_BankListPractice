package com.tjoeun.a20191119_01_banklistpractice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tjoeun.a20191119_01_banklistpractice.adapters.DeliveryAdapter
import com.tjoeun.a20191119_01_banklistpractice.datas.Delivery
import com.tjoeun.a20191119_01_banklistpractice.utils.DeliveryServerUtil
import com.tjoeun.a20191119_01_banklistpractice.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_delivery_list.*
import org.json.JSONObject

class DeliveryListActivity : BaseActivety() {

    var deliveryList = ArrayList<Delivery>()
    var deliveryAdapter:DeliveryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_list)
        setupEvent()
        setValuse()
    }

    override fun setupEvent() {

    }

    override fun setValuse() {
        getDeliverysFromServer()

        deliveryAdapter = DeliveryAdapter(mContext, deliveryList)
        deliveryCompanyListView.adapter = deliveryAdapter
    }

    fun getDeliverysFromServer() {
        DeliveryServerUtil.getRequestDeliveryList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("응답확인", json.toString())
                val code = json.getInt("code")

                if (code == 200) {
                    val data = json.getJSONObject("data")
                    val company = data.getJSONArray("company")

                    for (i in 0..company.length()-1) {
                        val deliveryJSONObject = company.getJSONObject(i)
                        val deliveryData = Delivery.getBankFromJsonObject(deliveryJSONObject)
                        deliveryList.add(deliveryData)
                    }

                    runOnUiThread {
                        deliveryAdapter?.notifyDataSetChanged()
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
