package com.example.siri_dhanyahub_1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siri_dhanyahub_1.data.Recipe
import com.example.siri_dhanyahub_1.data.RecipeRepository
import com.example.siri_dhanyahub_1.ui.theme.components.RecipeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeLabScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }

    val filteredRecipes = RecipeRepository.sampleRecipes.filter {
        it.milletType.contains(searchQuery, ignoreCase = true) ||
                it.name.contains(searchQuery, ignoreCase = true)
    }

    // If a recipe is selected, show detail screen
    if (selectedRecipe != null) {
        RecipeDetailScreen(
            recipe = selectedRecipe!!,
            onBack = { selectedRecipe = null }
        )
        return
    }

    // Otherwise show the list
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipe Lab - Millet Superfoods") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Search by Millet Type (e.g. Navane)") },
                placeholder = { Text("Enter millet name...") }
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredRecipes) { recipe ->
                    RecipeItem(
                        recipe = recipe,
                        onClick = { selectedRecipe = recipe }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeLabScreenPreview() {
    SiriDhanyaHub1Theme {
        RecipeLabScreen()
    }
}
