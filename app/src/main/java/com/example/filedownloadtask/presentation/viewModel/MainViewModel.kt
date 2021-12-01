package com.example.filedownloadtask.presentation.viewModel

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.filedownloadtask.data.remote.local.room.FileDownload
import com.example.filedownloadtask.domain.model.DownloadStatus
import com.example.filedownloadtask.domain.repository.ResponseRepository
import com.example.filedownloadtask.presentation.customeView.ProgressButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import zlc.season.rxdownload4.download
import zlc.season.rxdownload4.file
import zlc.season.rxdownload4.utils.safeDispose
import javax.inject.Inject

class MainViewModel @Inject constructor(app: Application) : AndroidViewModel(app){

    private var disposable: Disposable? = null


    private val repository = ResponseRepository(app)
    private val allFiles = repository.getAllFileDownload()

    fun getAllFilesList(): LiveData<List<FileDownload>> {
        return allFiles
    }


    private fun updateFileWhenComplete(fileDownload: FileDownload) {
        fileDownload.fileStatus = DownloadStatus.DOWNLOADED
        update(fileDownload)
    }
    private fun updateFileWhenCFailed(fileDownload: FileDownload) {
        fileDownload.fileStatus = DownloadStatus.FAILED
        update(fileDownload)
    }

    private fun update(fileDownload: FileDownload) {
        repository.update(fileDownload)
    }

    fun downloadFileUsingRxDownload(download: FileDownload, progressButton: ProgressButton) {
        disposable = download.url.download()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    progressButton.text = "${it.percent().toInt()} % "
                    progressButton.setProgress(it)
                },
                onComplete = {
                    updateFileWhenComplete(download)
                },
                onError = {
                    updateFileWhenCFailed(download)

                }
            )

    }

    override fun onCleared() {
        super.onCleared()
        disposable?.safeDispose()

    }
}