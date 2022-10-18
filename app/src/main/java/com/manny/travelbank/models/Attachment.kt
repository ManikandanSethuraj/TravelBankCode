package com.manny.travelbank.models

data class Attachment(
    val _id: String,
    val filename: String,
    val hash: String,
    val mime: String,
    val original: String,
    val size: Int,
    val thumbnails: Thumbnails,
    val userId: String
)