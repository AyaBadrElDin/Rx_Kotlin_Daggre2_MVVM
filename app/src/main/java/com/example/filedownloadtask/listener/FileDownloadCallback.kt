package com.example.filedownloadtask.listener

import com.example.filedownloadtask.data.remote.local.room.FileDownload
import com.example.filedownloadtask.presentation.customeView.ProgressButton

interface FileDownloadCallback {
    fun startDownload(download: FileDownload, progressButton: ProgressButton)
    fun openFile(download: FileDownload)
}