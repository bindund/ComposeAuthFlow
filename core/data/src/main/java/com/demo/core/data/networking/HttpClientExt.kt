package com.demo.core.data.networking

import android.provider.ContactsContract.Data
import com.demo.core.domain.util.AppResult
import com.demo.core.domain.util.DataError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException


suspend inline fun <reified Response: Any> HttpClient.get(
    route: String,
    queryParameters: Map<String, Any?> = mapOf()
): AppResult<Response, DataError.Network> {
    return safeCall {
        get {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified Request, reified Response: Any> HttpClient.post(
    route: String,
    body: Request
): AppResult<Response, DataError.Network> {
    return safeCall {
        post {
            url(constructRoute(route))
            setBody(body)
        }
    }
}

suspend inline fun <reified Response: Any> HttpClient.delete(
    route: String,
    queryParameters: Map<String, Any?> = mapOf()
): AppResult<Response, DataError.Network> {
    return safeCall {
        delete {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): AppResult<T, DataError.Network> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        e.printStackTrace()
        return AppResult.Error(DataError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return AppResult.Error(DataError.Network.SERIALIZATION)
    } catch(e: Exception) {
        if(e is CancellationException) throw e
        e.printStackTrace()
        return AppResult.Error(DataError.Network.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): AppResult<T, DataError.Network> {
    return when(response.status.value) {
        in 200..299 -> AppResult.Success(response.body<T>())
        401 -> AppResult.Error(DataError.Network.UNAUTHORIZED)
        408 -> AppResult.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> AppResult.Error(DataError.Network.CONFLICT)
        413 -> AppResult.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        429 -> AppResult.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> AppResult.Error(DataError.Network.SERVER_ERROR)
        else -> AppResult.Error(DataError.Network.UNKNOWN)
    }
}

private object LocalConfig {
    var BASE_URL: String = "localhost:8081"
}

//fun constructRoute(route: String): String {
////    return when {
//////        route.contains(BuildConfig.BASE_URL) -> route
//////        route.startsWith("/") -> BuildConfig.BASE_URL + route
//////        else -> BuildConfig.BASE_URL + "/$route"
////    }
//    val baseUrl = LocalConfig.BASE_URL
//    return when {
//        route.startsWith("http") -> route
//        route.startsWith("/") -> "$baseUrl$route"
//        else -> "$baseUrl/$route"
//    }
//}

fun constructRoute(route: String): String {
    val baseUrl = LocalConfig.BASE_URL
    return when {
        route.startsWith("http") -> route
        route.startsWith("/") -> "$baseUrl$route"
        else -> "$baseUrl/$route"
    }
}