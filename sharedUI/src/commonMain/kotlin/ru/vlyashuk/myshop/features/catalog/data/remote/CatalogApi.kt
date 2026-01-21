package ru.vlyashuk.myshop.features.catalog.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import ru.vlyashuk.myshop.BuildConfig
import ru.vlyashuk.myshop.features.catalog.data.remote.dto.ProductsResponseDto

class CatalogApi(private val client: HttpClient) {
    suspend fun getProducts(limit: Int): ProductsResponseDto = client
        .get("${BuildConfig.DUMMY_BASE_URL}/products") {
            parameter("limit", limit)
        }
        .body()
}