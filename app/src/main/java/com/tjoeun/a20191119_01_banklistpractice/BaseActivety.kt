package com.tjoeun.a20191119_01_banklistpractice

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivety : AppCompatActivity() {
   val mContext = this
    abstract fun setupEvent()
    abstract fun setValuse()
}