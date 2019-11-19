package com.tjoeun.a20191119_01_banklistpractice.datas

import org.json.JSONObject

class Delivery {
    var id = 0
    var name = ""
    var logo = ""

    companion object {
        fun getBankFromJsonObject(json: JSONObject) : Delivery {
            val delivery = Delivery()

//                파싱 코드들 작성
            delivery.id = json.getInt("id")
            delivery.name = json.getString("name")
            delivery.logo = json.getString("logo")

            return delivery

        }
    }
}