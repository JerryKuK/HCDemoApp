package com.jerrypeng31.hcdemoapp.model

import com.jerrypeng31.hcdemoapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModel {
    companion object{
        const val COUNT = 49
        val colorList = listOf(
            ItemData.ItemColorData(R.color.selector_text_red, R.drawable.shape_bg_red),
            ItemData.ItemColorData(R.color.selector_text_blue, R.drawable.shape_bg_blue),
            ItemData.ItemColorData(R.color.selector_text_green, R.drawable.shape_bg_green))
    }

    @Singleton
    @Provides
    fun getData(): List<ItemData>{
        val list = mutableListOf<ItemData>()

        var colorIndex = 0
        for (position in 1..COUNT){
            val c1 = position / 10
            val c2 = position % 10

            list.add(ItemData(position, false, colorList.get(colorIndex)))
            if(c1 % 2 == 0 && c2 % 2 == 0 ||
               c1 % 2 != 0 && (c2 == 0 || c2 == 9 || (c2 - 1) % 2 == 0)){
                colorIndex++
                if(colorIndex >= 3){
                    colorIndex = 0
                }
            }
        }
        return list
    }
}