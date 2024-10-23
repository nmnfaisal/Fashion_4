package com.noman.fashion.ui.screens.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.noman.fashion.domain.model.Product
import com.noman.fashion.ui.screens.products.ProductViewModel
import com.noman.fashion.ui.theme.FashionProductsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailsActivity : ComponentActivity() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val product: Product? = intent.extras?.getParcelable("product")
            FashionProductsTheme {

                ProductDetailScreen(productDetail = product!!)

            }
        }
    }

}