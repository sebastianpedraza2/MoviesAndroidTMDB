package com.pedraza.sebastian.core.utils

sealed class Result<T> {
    class Success<T>(val value: T) : Result<T>()
    class Error<T>(val message: UiText) : Result<T>()
}