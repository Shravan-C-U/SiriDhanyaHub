package com.example.siri_dhanyahub_1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class HealthBenefit(
    val milletName: String,
    val benefit: String,
    val detail: String,
    val emoji: String
)

val healthBenefits = listOf(
    HealthBenefit("Navane (Foxtail)", "Good for Diabetics",
        "Navane has a very Low Glycemic Index (GI), meaning it releases sugar slowly into the blood. This prevents glucose spikes and makes it an ideal rice substitute for diabetics.", "🌾"),
    HealthBenefit("Ragi (Finger Millet)", "Strengthens Bones",
        "Ragi is one of the richest plant-based sources of Calcium. Regular consumption helps prevent osteoporosis and is especially beneficial for growing children and elderly people.", "💪"),
    HealthBenefit("Sajje (Pearl Millet)", "Boosts Energy",
        "Sajje is high in Iron and protein, making it an excellent energy booster. It is traditionally consumed in Northern Karnataka during summers as a natural body coolant.", "⚡"),
    HealthBenefit("Baragu (Proso)", "Aids Digestion",
        "Baragu is rich in dietary fibre which promotes healthy digestion, prevents constipation, and supports gut health. It is also gluten-free, making it safe for people with gluten intolerance.", "🌿"),
    HealthBenefit("Ture (Kodo)", "Heart Healthy",
        "Ture millet is rich in antioxidants and helps reduce bad cholesterol (LDL) levels. Regular consumption supports cardiovascular health and reduces the risk of heart disease.", "❤️"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthBenefitsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Health Benefits") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Why Millets are Superfoods 🌾",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(healthBenefits) { benefit ->
                HealthBenefitCard(benefit)
            }
        }
    }
}

@Composable
fun HealthBenefitCard(benefit: HealthBenefit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Text(text = benefit.emoji, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = benefit.milletName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = benefit.benefit,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF4A7C4A),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color(0xFFEAF4EA), thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = benefit.detail,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
