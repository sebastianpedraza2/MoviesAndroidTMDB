package com.pedraza.sebastian.core.utils

sealed class Result<T>(
    val data: T? = null,
    open val message: UiText? = null
) {
    class Success<T>(val value: T) : Result<T>(value)
    class Error<T>(override val message: UiText, value: T? = null) : Result<T>(value, message)
}

