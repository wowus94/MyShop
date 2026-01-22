package ru.vlyashuk.myshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import ru.vlyashuk.myshop.core.di.KoinInitializer
import ru.vlyashuk.myshop.features.catalog.presentation.CatalogRoute
import ru.vlyashuk.myshop.features.catalog.presentation.ProductDetailsRoute
import ru.vlyashuk.myshop.navigation.AppRoute
import ru.vlyashuk.myshop.theme.AppTheme

@Preview
@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    KoinInitializer.init()

    val backStack = remember { mutableStateListOf<AppRoute>(AppRoute.Catalog) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { route ->
            when (route) {
                AppRoute.Catalog -> NavEntry(route) {
                    CatalogRoute { product ->
                        backStack.add(AppRoute.ProductDetails(product.title))
                    }
                }

                is AppRoute.ProductDetails -> NavEntry(route) {
                    ProductDetailsRoute(
                        productTitle = route.title,
                        onBack = { backStack.removeLastOrNull() }
                    )
                }
            }
        }
    )
}