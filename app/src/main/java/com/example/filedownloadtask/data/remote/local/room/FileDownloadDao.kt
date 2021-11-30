package com.example.filedownloadtask.data.remote.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FileDownloadDao {

    @Query("select * from fileDownload_table")
    fun getFileDownloadList(): LiveData<List<FileDownload>>

    @Insert
    fun insert(fileDownload: FileDownload)

    @Update
    fun update(fileDownload: FileDownload)


}