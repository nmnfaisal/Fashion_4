package com.noman.fashion.ui.screens.products

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.noman.fashion.domain.model.Product
import com.noman.fashion.ui.screens.details.ProductDetailsActivity
import com.noman.fashion.ui.theme.FashionProductsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make the status bar transparent and set the color
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.Transparent.toArgb() // Set transparent or desired color

        setContent {
            val productState = viewModel.productList.collectAsState().value
            val searchQuery = viewModel.searchQuery.collectAsState().value

            // Apply your custom theme
            FashionProductsTheme {
                // Apply a modifier to the root Column to adjust padding for system bars
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = WindowInsets.statusBars
                                .asPaddingValues()
                                .calculateTopPadding()
                        ) // Add padding to avoid content behind status bar
                ) {
                    // Search TextField
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = { query ->
                            viewModel.updateSearchQuery(query)
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        ), // White text color
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp), // Padding optimization
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Color(0xFFE78A3A),
                                    )
                                    .padding(all = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                IconButton(onClick = { /* Handle menu click */ }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu icon",
                                        tint = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                Box(
                                    modifier = Modifier.weight(1f),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    if (searchQuery.isEmpty()) {
                                        Text(
                                            text = "Search here...",
                                            color = Color.White.copy(alpha = 0.5f),
                                            fontSize = 16.sp
                                        )
                                    }
                                    innerTextField()
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                // Search icon button on the right
                                IconButton(onClick = { /* Handle search icon click */ }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search icon",
                                        tint = Color.White
                                    )
                                }

                                // Three dots icon button on the right
                                IconButton(onClick = { /* Handle more options click */ }) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = "More options icon",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    )

                    // Loading and product display logic
                    if (productState.loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(150.dp)
                        )
                    }
                    if (productState.data.isNotEmpty()) {
                        LazyColumn {
                            items(productState.data) { product ->
                                ProductItem(
                                    product = product,
                                ) {
                                    launchDetailActivity(product)
                                }
                            }
                        }
                    } else if (!productState.error.isNullOrEmpty()) {
                        Toast.makeText(LocalContext.current, productState.error, Toast.LENGTH_LONG)
                            .show()
                        Log.e("Error", productState.error)
                    }
                }
            }
        }
    }

    private fun launchDetailActivity(product: Product) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}
