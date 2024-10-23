package com.noman.fashion.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noman.fashion.domain.model.Product
import com.noman.fashion.domain.usecase.ProductListUseCase
import com.noman.fashion.domain.usecase.SearchQueryUseCase
import com.noman.fashion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductListScreenState(
    val loading: Boolean = false,
    val error: String? = null,
    val data: List<Product> = emptyList()
)

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productListUseCase: ProductListUseCase,
    private val searchQueryUseCase: SearchQueryUseCase
) : ViewModel() {

    private val _productList = MutableStateFlow(ProductListScreenState())
    val productList: StateFlow<ProductListScreenState> = _productList
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val actualProductList = mutableListOf<Product>()

    var searchJob: Job? = null

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {

            productListUseCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _productList.value = ProductListScreenState(error = resource.error)
                    }

                    is Resource.Loading -> {
                        _productList.value = ProductListScreenState(loading = true)
                    }

                    is Resource.Success -> {
                        _productList.value = ProductListScreenState(data = resource.data.orEmpty())
                        actualProductList.clear()
                        actualProductList.addAll(_productList.value.data)
                    }
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _searchQuery.debounce(200)
                .collectLatest {
                    if (_searchQuery.value.isBlank())
                        _productList.value = ProductListScreenState(data = actualProductList)
                    else
                        _productList.value = ProductListScreenState(
                            data = searchQueryUseCase.invoke(
                                _searchQuery.value,
                                _productList.value.data
                            )
                        )
                }
        }



    }
}