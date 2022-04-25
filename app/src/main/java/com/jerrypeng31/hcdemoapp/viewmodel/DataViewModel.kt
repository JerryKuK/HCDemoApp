package com.jerrypeng31.hcdemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jerrypeng31.hcdemoapp.model.ItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val dataList: MutableList<ItemData>) : ViewModel() {
    private val _updateItemData: MutableLiveData<List<ItemData>> = MutableLiveData()
    val updateItemData: LiveData<List<ItemData>> = _updateItemData

    fun getDataList(){
        _updateItemData.value = dataList
    }

    fun updateClick(id: Int, isChecked: Boolean){
        val colorData = dataList.get(id - 1).dataColorData
        dataList[id- 1] = ItemData(id, isChecked, colorData)

        _updateItemData.value = dataList
    }
}