package com.manny.travelbank.models

data class User(
    val _logs: List<Any>,
    val _meta: Meta,
    val agencyId: String,
    val channels: List<String>,
    val created: String,
    val email: String,
    val failedLoginAttemptsInARow: List<Any>,
    val id: String,
    val isInvited: Boolean,
    val lastKnownIp: String,
    val lockedOut: Boolean,
    val reportingStatus: String,
    val status: String,
    val type: String,
    val updated: String
)