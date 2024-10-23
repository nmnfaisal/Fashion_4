package com.noman.fashion.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.noman.fashion.domain.model.Product


@Composable
fun ProductDetailScreen(productDetail: Product) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            if (!productDetail.image.isNullOrBlank()) {
                Image(
                    painter = rememberAsyncImagePainter("${productDetail.image}"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = productDetail.title!!,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Price: ${productDetail.price}",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Old Price: $${productDetail.oldPrice}",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Overview:",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Text(
            text = productDetail.description ?: "",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Category: ${productDetail.category}",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Rating: ${productDetail.rating}",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Quantity: ${productDetail.quantity}",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

    }
}
