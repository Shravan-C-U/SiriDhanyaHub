package com.example.siri_dhanyahub_1.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.siri_dhanyahub_1.data.MandiPrice

@Composable
fun MandiItem(data: MandiPrice) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = data.milletType,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = data.city,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "₹${data.currentPrice}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    val isUp = data.currentPrice >= data.prevPrice
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (isUp) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                            contentDescription = null,
                            tint = if (isUp) Color(0xFF2E7D32) else Color(0xFFC62828),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (isUp) "Rising" else "Falling",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (isUp) Color(0xFF2E7D32) else Color(0xFFC62828)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "7D High: ₹${data.sevenDayHigh}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "7D Low: ₹${data.sevenDayLow}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
