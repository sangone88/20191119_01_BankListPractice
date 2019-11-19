package com.tjoeun.a20191119_01_banklistpractice.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class DeliveryServerUtil {

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {
        var BASE_URL = "http://192.168.0.26:5000"

        fun getRequestDeliveryList(context: Context, handler: ServerUtil.JsonResponseHandler) {
            var client = OkHttpClient()
            var urlBuilder = HttpUrl.parse("${BASE_URL}/info/company")!!.newBuilder()
//            파라미터 첨부가 필요 없다.
//            urlBuilder.addEncodedQueryParameter()

            val requestUrl = urlBuilder.build().toString()

//            Intent 와 비슷한 개념. 어디로 갈지 세팅 완료.
//            실제 출발은 아직 안함
            val request = Request.Builder()
                .url(requestUrl)
//                .header() => 필요 없어서 (서버API가 요구하지 않아서) 첨부 안함.
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("서버통신실패", e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)
                }

            })
        }
    }
}