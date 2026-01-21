package ru.vlyashuk.myshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.vlyashuk.myshop.core.di.KoinInitializer
import ru.vlyashuk.myshop.features.catalog.presentation.CatalogRoute
import ru.vlyashuk.myshop.theme.AppTheme

@Preview
@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    KoinInitializer.init()
    CatalogRoute()
}