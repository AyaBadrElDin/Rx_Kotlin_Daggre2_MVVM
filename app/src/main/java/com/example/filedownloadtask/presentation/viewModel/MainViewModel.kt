package com.example.filedownloadtask.presentation.viewModel

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.filedownloadtask.data.remote.local.room.FileDownload
import com.example.filedownloadtask.domain.model.DownloadStatus
import com.example.filedownloadtask.domain.repository.ResponseRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(app: Application) : AndroidViewModel(app){

    private var disposable: Disposable? = null


    private val repository = ResponseRepository(app)
    private val allFiles = repository.getAllFileDownload()

    fun getAllFilesList(): LiveData<List<FileDownload>> {
        return allFiles
    }


    private fun updateFile(fileDownload: FileDownload) {
        repository.update(fileDownload)
    }

}