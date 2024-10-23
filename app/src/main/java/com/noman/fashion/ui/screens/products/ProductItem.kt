package com.noman.fashion.ui.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.noman.fashion.domain.model.Product

@Composable
fun ProductItem(
    product: Product,
    onItemClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(product.id) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)

        ) {
            Image(
                painter = rememberAsyncImagePainter("${product.image}"),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(10.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = product.title.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.description.toString(),
                        color = Color(0xFF9F9F9F),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f) // Use weight to take available space
                    )
                    Text(
                        text = "Price: $${product.price}",
                        color = Color(0xFF9F9F9F),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 4.dp) // Optional padding between texts
                    )
                }

                // Optional: Add some space if needed
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 0.dp),
            thickness = 1.dp,
            color = Color(0xFF9F9F9F)
        )
    }
}
