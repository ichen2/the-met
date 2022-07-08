package com.ichen.themet.models

enum class Status {
    NOT_STARTED,
    LOADING,
    SUCCESS,
    ERROR,
}

data class Field<T>(
    var data: T,
    var status: Status
) {
    fun withData(data: T) = Field(data, status)

    fun withStatus(status: Status) = Field(data, status)
}