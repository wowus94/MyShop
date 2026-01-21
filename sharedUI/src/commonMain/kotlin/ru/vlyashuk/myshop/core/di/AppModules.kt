package ru.vlyashuk.myshop.core.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.vlyashuk.myshop.core.network.HttpClientFactory
import ru.vlyashuk.myshop.domain.repository.CatalogRepository
import ru.vlyashuk.myshop.features.catalog.data.CatalogRepositoryImpl
import ru.vlyashuk.myshop.features.catalog.data.remote.CatalogApi
import ru.vlyashuk.myshop.features.catalog.presentation.CatalogViewModel

private val networkModule = module {
    single { HttpClientFactory().create() }
    single { CatalogApi(get()) }
}

private val catalogModule = module {
    single<CatalogRepository> { CatalogRepositoryImpl(get()) }
    viewModelOf(::CatalogViewModel)
}

fun appModules() = listOf(networkModule, catalogModule)
