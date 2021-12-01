package com.example.filedownloadtask.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filedownloadtask.R
import com.example.filedownloadtask.data.remote.local.room.FileDownload
import com.example.filedownloadtask.databinding.RowFileListBinding
import com.example.filedownloadtask.domain.model.DownloadStatus
import com.example.filedownloadtask.domain.model.FileType
import com.example.filedownloadtask.listener.FileDownloadCallback

class FileListAdapter constructor(
    private val fileList: List<FileDownload>,
    fileDownloadCallback: FileDownloadCallback
) :
    RecyclerView.Adapter<FileListAdapter.FileViewHolder>() {
    private var listener: FileDownloadCallback? = null

    init {
        listener = fileDownloadCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowFileListBinding.inflate(inflater)

        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val fileDownload = fileList[position]
        listener?.let {
            holder.bind(fileDownload, it)
        }
    }

    override fun getItemCount() = fileList.size

    class FileViewHolder(private val binding: RowFileListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(item: FileDownload, fileDownloadCallback: FileDownloadCallback) {
            binding.fileDownload = item
            if (item.type == FileType.PDF)
                binding.imgVideoPdf.setImageDrawable(
                    binding.root.context.getDrawable(
                        R.drawable.ic_baseline_file_open_24
                    )
                )
            else
                binding.imgVideoPdf.setImageDrawable(binding.root.context.getDrawable(R.drawable.ic_baseline_live_tv_24))

            if (item.fileStatus == DownloadStatus.DOWNLOADED) {
                binding.imgMarkDownload.visibility = View.VISIBLE
                binding.buttonProgress.visibility = View.GONE
            } else {
                binding.imgMarkDownload.visibility = View.GONE
                binding.buttonProgress.visibility = View.VISIBLE
            }
            binding.buttonProgress.setOnClickListener{
                fileDownloadCallback.startDownload(item,binding.buttonProgress)
            }
            binding.imgMarkDownload.setOnClickListener{
                fileDownloadCallback.openFile(item)
            }
            binding.executePendingBindings()
        }

    }

}