package com.jerrypeng31.hcdemoapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.jerrypeng31.hcdemoapp.databinding.ActivityMainBinding
import com.jerrypeng31.hcdemoapp.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter

    private val viewModel by viewModels<DataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        adapter = ItemAdapter { id, isEnable ->
            viewModel.updateClick(id, isEnable)
        }

        val rv = activityMainBinding.rv

        rv.layoutManager = GridLayoutManager(this, 5)
        rv.adapter = adapter

        initViewModelObserve(adapter)
    }

    private fun initViewModelObserve(adapter: ItemAdapter){
        viewModel.getDataList()
        viewModel.updateItemData.observe(this, {
            adapter.submitList(it)
        })
    }
}