package ru.vlyashuk.myshop.features.catalog.data

import ru.vlyashuk.myshop.domain.model.Product
import ru.vlyashuk.myshop.domain.repository.CatalogRepository
import ru.vlyashuk.myshop.features.catalog.data.remote.CatalogApi
import ru.vlyashuk.myshop.features.catalog.data.remote.dto.ProductDto

class CatalogRepositoryImpl(
    private val catalogApi: CatalogApi
) : CatalogRepository {

    override suspend fun getProducts(limit: Int): List<Product> = catalogApi
        .getProducts(limit)
        .products
        .map(ProductDto::toDomain)
}

private fun ProductDto.toDomain() = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    discountPercentage = discountPercentage,
    rating = rating,
    stock = stock,
    brand = brand,
    category = category,
    thumbnail = thumbnail
)