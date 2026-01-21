package ru.vlyashuk.myshop.features.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.vlyashuk.myshop.domain.repository.CatalogRepository

class CatalogViewModel(
    private val repository: CatalogRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CatalogUiState())
    val state: StateFlow<CatalogUiState> = _state

    private var loadJob: Job? = null

    init {
        loadProducts()
    }

    fun refresh() = loadProducts(force = true)

    private fun loadProducts(force: Boolean = false) {
        if (loadJob?.isActive == true && !force) return
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            runCatching { repository.getProducts() }
                .onSuccess { products ->
                    _state.value = CatalogUiState(products = products)
                }
                .onFailure { throwable ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = throwable.message ?: "Unknown error"
                    )
                }
        }
    }
}