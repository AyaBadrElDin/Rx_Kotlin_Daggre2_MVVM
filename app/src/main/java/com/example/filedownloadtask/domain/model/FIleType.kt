package com.example.filedownloadtask.domain.model

enum class FileType {
    VIDEO,
    PDF;
    override fun toString(): String {
        return when{
            this== VIDEO ->"VIDEO"
            this== PDF ->"PDF"
            else -> "VIDEO"
        }
    }


}