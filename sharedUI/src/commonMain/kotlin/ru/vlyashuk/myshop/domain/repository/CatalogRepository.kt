package ru.vlyashuk.myshop.domain.repository

import ru.vlyashuk.myshop.domain.model.Product

interface CatalogRepository {
    suspend fun getProducts(limit: Int = 30): List<Product>
}