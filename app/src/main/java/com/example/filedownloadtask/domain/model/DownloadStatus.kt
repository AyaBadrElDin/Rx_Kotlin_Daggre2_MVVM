package com.example.filedownloadtask.domain.model

enum class DownloadStatus {
    DOWNLOADED,
    NOT_DOWNLOADED,
    IN_PROGRESS,
    WAITING,
    FAILED;

    override fun toString(): String {
        return when {
            this == DOWNLOADED -> "Downloaded"
            this == NOT_DOWNLOADED -> "Not Download"
            this == IN_PROGRESS -> "In Progress"
            this == WAITING -> "Waiting"
            this == WAITING -> "Failed"
            else -> "Not Download"
        }
    }
}