package com.example.siri_dhanyahub_1.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siri_dhanyahub_1.R
import com.example.siri_dhanyahub_1.data.Recipe
import com.example.siri_dhanyahub_1.ui.theme.components.milletColor
import androidx.compose.material.icons.filled.Share

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(recipe: Recipe, onBack: () -> Unit) {
    var isSaved by remember { mutableStateOf(false) }
    val context = androidx.compose.ui.platform.LocalContext.current

    val imageRes = when (recipe.imageUrl) {
        "idli" -> R.drawable.idli
        "roti" -> R.drawable.roti
        "mudde" -> R.drawable.mudde
        "khichdi" -> R.drawable.khichdi
        "upma" -> R.drawable.upma
        else -> R.drawable.idli
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    // Share button
                    IconButton(onClick = {
                        val shareText = """
🌾 *${recipe.name}* - Siri-Dhanya Hub

🥗 Millet Type: ${recipe.milletType}
💚 Health Benefit: ${recipe.healthBenefit}

📋 *Steps:*
${recipe.steps.mapIndexed { i, step -> "${i + 1}. $step" }.joinToString("\n")}

🌾 Shared from Siri-Dhanya Hub App
        """.trimIndent()

                        val intent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(android.content.Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(
                            android.content.Intent.createChooser(intent, "Share Recipe via")
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }

                    // Favourite button
                    IconButton(onClick = { isSaved = !isSaved }) {
                        Icon(
                            imageVector = if (isSaved) Icons.Default.Favorite
                            else Icons.Default.FavoriteBorder,
                            contentDescription = "Save",
                            tint = if (isSaved) Color.Red else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Hero image section
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = recipe.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // Dark overlay for text readability
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.35f))
                    )
                    // Text overlay at bottom
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = recipe.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Surface(
                            color = milletColor(recipe.milletType),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = recipe.milletType,
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 2.dp
                                )
                            )
                        }
                    }
                }
            }

            // Health benefit card
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFEAF4EA)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("💚", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = recipe.healthBenefit,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF2E5D2E),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            // About section
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "About this dish",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = recipe.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Step-by-Step Recipe",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Recipe steps
            itemsIndexed(recipe.steps) { index, step ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                color = milletColor(recipe.milletType),
                                shape = RoundedCornerShape(50)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = step,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 6.dp)
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    SiriDhanyaHub1Theme {
        RecipeDetailScreen(
            recipe = Recipe(
                id = 1,
                name = "Navane Idli",
                milletType = "Navane",
                imageUrl = "idli",
                healthBenefit = "Rich in fiber",
                description = "Soft and healthy",
                steps = listOf("Step 1", "Step 2")
            ),
            onBack = {}
        )
    }
}
