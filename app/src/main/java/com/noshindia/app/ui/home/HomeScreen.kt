package com.noshindia.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class Feature(val title: String, val detail: String)

private val features = listOf(
    Feature("100% Natural", "No preservatives, ever"),
    Feature("Small Batch", "Handmade in small batches"),
    Feature("Fresh Daily", "Prepared fresh, delivered warm"),
    Feature("50 Years", "A family recipe, three generations")
)

@Composable
fun HomeScreen(
    onOrderNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item { HeroSection(onOrderNow) }
        item { FeatureGrid() }
        item { HeritageSection() }
        item { DeliverySection() }
    }
}

@Composable
private fun HeroSection(onOrderNow: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Text(
            text = "A 50-YEAR-OLD FAMILY RECIPE",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Authentic homemade mithai, delivered warm & fresh.",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
        )
        Text(
            text = "Taste the legacy of three generations. From our family kitchen to your " +
                "doorstep, we bring you the finest handcrafted Indian sweets in Australia.",
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = onOrderNow, modifier = Modifier.padding(top = 20.dp)) {
            Text("View Menu & Order")
        }
    }
}

@Composable
private fun FeatureGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(140.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(features.size) { index ->
            val feature = features[index]
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = feature.title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                    Text(text = feature.detail, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
private fun HeritageSection() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Text(
            text = "A HERITAGE OF 50 YEARS",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Every piece, a family legacy",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
        )
        Text(
            text = "Every single piece is made from an authentic, cherished 50-year-old family " +
                "recipe passed down through generations — crafted with patience and strict " +
                "attention to traditional technique, using pure khoya and slow-simmered rose " +
                "and cardamom syrup.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun DeliverySection() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Text(
            text = "DELIVERY INFORMATION",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Free local delivery, coordinated with you",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
        )
        Text(
            text = "We currently offer free local delivery on all orders. Since fresh sweets are " +
                "best enjoyed warm, we coordinate delivery times directly with you via WhatsApp " +
                "or phone to make sure someone is home to receive them.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
