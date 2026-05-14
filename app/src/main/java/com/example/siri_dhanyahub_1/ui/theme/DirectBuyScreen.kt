package com.example.siri_dhanyahub_1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class FPO(
    val name: String,
    val location: String,
    val millets: String,
    val contact: String,
    val isOnline: Boolean
)

val fpoList = listOf(
    FPO("Davangere Millets FPO", "Davangere, Karnataka",
        "Navane, Baragu, Sajje", "+91 98765 43210", true),
    FPO("Sahyadri Grain Producers", "Hubli, Karnataka",
        "Ragi, Ture, Baragu", "+91 87654 32109", true),
    FPO("Karnataka Millet Federation", "Bengaluru, Karnataka",
        "All Varieties", "+91 76543 21098", false),
    FPO("Bidar Siri-Dhanya Co-op", "Bidar, Karnataka",
        "Sajje, Navane", "+91 65432 10987", true),
    FPO("Mysuru Organic Millets", "Mysuru, Karnataka",
        "Ragi, Navane", "+91 54321 09876", false),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectBuyScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Direct Buy – FPO Connect") },
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
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEAF4EA)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "🌾 Connect directly with Farmer Producer Organizations (FPOs) and buy heritage millets without middlemen.",
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF2E5D2E)
                    )
                }
            }
            items(fpoList) { fpo ->
                FPOCard(fpo)
            }
        }
    }
}

@Composable
fun FPOCard(fpo: FPO) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = fpo.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = if (fpo.isOnline) Color(0xFF2E7D32) else Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (fpo.isOnline) "Online" else "Offline",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (fpo.isOnline) Color(0xFF2E7D32) else Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "📍 ${fpo.location}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "🌾 Available: ${fpo.millets}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Call, contentDescription = null,
                    modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Contact Producer (Simulated)")
            }
        }
    }
}
