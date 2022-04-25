package com.jerrypeng31.hcdemoapp

import android.view.View
import com.jerrypeng31.hcdemoapp.model.ItemData

fun View.setItemData(itemData: ItemData){
    tag = itemData
}

fun View.getItemData(): ItemData?{
    return if(tag is ItemData){
        tag as ItemData
    } else {
        null
    }
}