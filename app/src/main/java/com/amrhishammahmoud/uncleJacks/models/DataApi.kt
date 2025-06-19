package com.amrhishammahmoud.uncleJacks.models

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object KtorClient {

    val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }


    val httpClient = HttpClient(CIO){

        install(HttpTimeout){
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 3000
            socketTimeoutMillis = 3000
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("amr", "log: $message ")
                }

            }
        }

      /*  install(JsonFeature){
            serializer = KotlinxSerializer(json)
        }*/

        install(ContentNegotiation){
            json(Json{
                encodeDefaults = true
                ignoreUnknownKeys = true
                isLenient = true
                explicitNulls = false
            })
        }


        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }


    }


}



