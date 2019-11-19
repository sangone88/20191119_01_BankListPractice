package com.tjoeun.a20191119_01_banklistpractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tjoeun.a20191119_01_banklistpractice.R
import com.tjoeun.a20191119_01_banklistpractice.datas.Delivery

class DeliveryAdapter(context: Context, res:Int, list:ArrayList<Delivery>)
    : ArrayAdapter<Delivery> (context, res, list) {

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list: ArrayList<Delivery>) : this(context, R.layout.delivery_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.delivery_list_item, null)
        }

        var row = tempRow!!

        var logoImg = row.findViewById<ImageView>(R.id.logoImg)
        var deliveryCompanyNameTxt = row.findViewById<TextView>(R.id.deliveryCompanyNameTxt)

        var data = mList.get(position)

        Glide.with(mContext).load(data.logo).into(logoImg)
        deliveryCompanyNameTxt.text = data.name

        return row
    }
}