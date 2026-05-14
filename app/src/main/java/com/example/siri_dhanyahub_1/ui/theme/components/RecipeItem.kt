package com.example.siri_dhanyahub_1.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siri_dhanyahub_1.R
import com.example.siri_dhanyahub_1.data.Recipe
import com.example.siri_dhanyahub_1.ui.theme.SiriDhanyaHub1Theme

@Composable
fun RecipeItem(recipe: Recipe, onClick: () -> Unit) {
    var isSaved by remember { mutableStateOf(false) }

    val imageRes = when (recipe.id) {
        1 -> R.drawable.idli
        2 -> R.drawable.roti
        3 -> R.drawable.mudde
        4 -> R.drawable.khichdi
        5 -> R.drawable.upma
        else -> R.drawable.idli
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = { onClick() }
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = recipe.milletType,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = recipe.healthBenefit,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Tap to see full recipe →",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
                IconButton(onClick = { isSaved = !isSaved }) {
                    Icon(
                        imageVector = if (isSaved) Icons.Filled.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = "Save",
                        tint = if (isSaved) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeItemPreview() {
    SiriDhanyaHub1Theme {
        RecipeItem(
            recipe = Recipe(
                id = 1,
                name = "Navane Idli",
                milletType = "Navane",
                imageUrl = "idli",
                healthBenefit = "Healthy",
                description = "Desc",
                steps = listOf("Step 1")
            ),
            onClick = {}
        )
    }
}
