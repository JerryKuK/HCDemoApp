package com.jerrypeng31.hcdemoapp.model

data class ItemData(
    val id: Int,
    val isEnabled: Boolean,
    val dataColorData: ItemColorData) {
        data class ItemColorData(
            val resIdTextColor: Int,
            val resIdBgColor: Int
        )
}
