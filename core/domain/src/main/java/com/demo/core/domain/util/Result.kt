package com.demo.core.domain.util

sealed interface AppResult<out D, out E : Error> {
    data class Success<out D>(val data: D) : AppResult<D, Nothing>
    data class Error<out E : com.demo.core.domain.util.Error>(val error: E) : AppResult<Nothing, E>
}

inline fun <T, E : Error, R> AppResult<T, E>.map(map: (T) -> R): AppResult<R, E> {
    return when (this) {
        is AppResult.Error -> AppResult.Error(error)
        is AppResult.Success -> AppResult.Success(map(data))
    }
}

fun <T, E : Error> AppResult<T, E>.asEmptyDataResult(): EmptyDataResult<E> {
    return when (this) {
        is AppResult.Success -> AppResult.Success(Unit)
        is AppResult.Error -> AppResult.Error(error)
    }
}

typealias EmptyDataResult<E> = AppResult<Unit, E>
