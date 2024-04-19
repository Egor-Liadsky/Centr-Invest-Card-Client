package com.turtleteam.core_network

import com.turtleteam.core_network.error.AppError
import com.turtleteam.core_network.error.Code
import com.turtleteam.core_network.error.ServerException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import io.ktor.utils.io.errors.IOException

abstract class BaseRepository(private val httpClient: HttpClient) {

    protected suspend fun executeCall(
        url: String = URL.BASE,
        type: HttpMethod,
        path: String,
        parameters: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        body: String? = null
    ): String {
        val response: HttpResponse
        try {
            response = httpClient.request(url) {
                url {
                    appendPathSegments(path)
                    parameters?.forEach { this.parameters.append(it.key, it.value) }
                }
                method = type
                headers?.forEach { this.headers.append(it.key, it.value) }
                body?.let { setBody(body) }
            }
        } catch (e: SocketTimeoutException) {
            throw e
        } catch (e: IOException) {
            throw e
        }

        if (response.status.value !in 200..299) {
            throw ServerException(response.status.value, response.status.description)
        }
        if (response.status == HttpStatusCode.Conflict) {
            throw AppError(code = Code.CONFLICT, description = "Already exists")
        }
        if (response.status == HttpStatusCode.Unauthorized) {
            throw AppError(code = Code.UNAUTHORIZED, description = "User unauthorized")
        }

        return response.body()
    }
}
