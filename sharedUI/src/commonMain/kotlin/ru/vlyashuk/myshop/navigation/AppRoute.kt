package ru.vlyashuk.myshop.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {

    @Serializable
    data object Catalog : AppRoute

    @Serializable
    data class ProductDetails(val title: String) : AppRoute
}