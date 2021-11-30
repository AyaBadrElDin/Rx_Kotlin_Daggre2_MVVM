package com.example.filedownloadtask.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.filedownloadtask.R
import com.example.filedownloadtask.data.remote.local.room.FileDownload
import com.example.filedownloadtask.databinding.ActivityMainBinding
import com.example.filedownloadtask.presentation.adapter.FileListAdapter
import com.example.filedownloadtask.presentation.viewModel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var dataBinding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        viewModel.getAllFilesList().observe(this) {
            submitList(it)
        };
    }

    private fun submitList(it: List<FileDownload>?) {
        val adapter = it?.let { it1 -> FileListAdapter(it1) }
        dataBinding.recycleViewList.adapter = adapter
        dataBinding.recycleViewList.setHasFixedSize(true)

    }
}