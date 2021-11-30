package com.example.filedownloadtask.domain.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.filedownloadtask.common.subscribeOnBackground
import com.example.filedownloadtask.data.remote.local.room.FileDownload
import com.example.filedownloadtask.data.remote.local.room.FileDownloadDao
import com.example.filedownloadtask.data.remote.local.room.FileDownloadDatabase

class ResponseRepository(application: Application) {
    private var fileDownloadDao: FileDownloadDao
    private var allFiles: LiveData<List<FileDownload>>

    private val database = FileDownloadDatabase.getInstance(application)

    init {
        fileDownloadDao = database.fileDownloadDao()
        allFiles = fileDownloadDao.getFileDownloadList()
    }


    fun getAllFileDownload(): LiveData<List<FileDownload>> {
        return allFiles
    }

    fun update(fileDownload: FileDownload) {
        subscribeOnBackground {
            fileDownloadDao.update(fileDownload)
        }
    }
}