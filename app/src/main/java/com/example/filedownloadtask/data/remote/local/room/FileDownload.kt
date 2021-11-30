package com.example.filedownloadtask.data.remote.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filedownloadtask.domain.model.DownloadStatus
import com.example.filedownloadtask.domain.model.FileType

@Entity(tableName = "fileDownload_table")
data class FileDownload(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "file_name")
    var name: String,

    @ColumnInfo(name = "file_type")
    var type: FileType = FileType.VIDEO,

    @ColumnInfo(name = "file_url")
    var url: String,

    @ColumnInfo(name = "file_absolute_path")
    var fileAbsolutePath: String?=null,

    @ColumnInfo(name = "file_status")
    var fileStatus: DownloadStatus = DownloadStatus.NOT_DOWNLOADED

)