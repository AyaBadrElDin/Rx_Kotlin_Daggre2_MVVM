package com.example.filedownloadtask.data.remote.local.room

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.filedownloadtask.common.getAssetJsonData
import com.example.filedownloadtask.common.subscribeOnBackground
import com.example.filedownloadtask.data.remote.dto.ResponseDto
import com.example.filedownloadtask.domain.model.FileType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(entities = [FileDownload::class], version = 1)
abstract class FileDownloadDatabase : RoomDatabase() {


    abstract fun fileDownloadDao(): FileDownloadDao

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: FileDownloadDatabase? = null

        @SuppressLint("StaticFieldLeak")

        private var getContext: Context? = null

        @Synchronized
        fun getInstance(context: Context): FileDownloadDatabase {
            this.getContext = context
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FileDownloadDatabase::class.java,
                    "file_download_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: FileDownloadDatabase) {
            val fileDownloadDao = db.fileDownloadDao()

            subscribeOnBackground {
                val data = getContext?.let { getAssetJsonData(it) }
                val type = object : TypeToken<List<ResponseDto>>() {}.type
                val fileResponse: List<ResponseDto> = Gson().fromJson(data, type)
                for (e in fileResponse) {

                    fileDownloadDao.insert(
                        FileDownload(
                            e.id,
                            e.name,
                            if (e.type == FileType.PDF.toString()) FileType.PDF else FileType.VIDEO,
                            e.url
                        )
                    )

                }
            }
        }
    }
}