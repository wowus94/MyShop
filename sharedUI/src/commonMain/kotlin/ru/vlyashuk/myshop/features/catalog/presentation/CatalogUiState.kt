package ru.vlyashuk.myshop.features.catalog.presentation

import ru.vlyashuk.myshop.domain.model.Product

data class CatalogUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)