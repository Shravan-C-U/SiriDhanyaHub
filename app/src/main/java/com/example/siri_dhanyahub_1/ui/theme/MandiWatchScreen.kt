package com.example.siri_dhanyahub_1.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siri_dhanyahub_1.data.MandiPrice
import com.example.siri_dhanyahub_1.data.MandiRepository

@Composable
fun MandiWatchScreen() {
    Scaffold(
        topBar = {
            // Earthy Gradient Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = Brush.verticalGradient(colors = listOf(Color(0xFF2E7D32), Color(0xFF4CAF50))))
                    .padding(top = 24.dp, bottom = 24.dp, start = 20.dp, end = 20.dp)
            ) {
                Column {
                    Text(text = "🌾 Mandi Watch", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Live millet prices across Karnataka", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
                }
            }
        },
        containerColor = Color(0xFFFFFDF7)
    ) { innerPadding ->
        // LazyColumn constrained by innerPadding to prevent bottom-clipping
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 32.dp, start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = MandiRepository.sampleMandiData,
                key = { it.id } // Stable keys prevent "disappearing" items during scroll
            ) { item ->
                MandiCardInternal(item)
            }
        }
    }
}

@Composable
fun MandiCardInternal(data: MandiPrice) {
    val isRising = data.currentPrice >= data.prevPrice

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // LEFT: Name, City, Ranges
            Column(modifier = Modifier.weight(1f)) {
                Text(text = data.milletType, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1B5E20))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(6.dp).clip(RoundedCornerShape(50)).background(Color(0xFFFF8F00)))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = data.city, fontSize = 12.sp, color = Color(0xFF757575))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MandiChip(label = "7D High", value = "₹${data.sevenDayHigh.toInt()}", color = Color(0xFF2E7D32), bgColor = Color(0xFFE8F5E9))
                    MandiChip(label = "7D Low", value = "₹${data.sevenDayLow.toInt()}", color = Color(0xFFC62828), bgColor = Color(0xFFFFEBEE))
                }
            }

            // RIGHT: Current Price and Trend
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "₹${data.currentPrice.toInt()}", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color(0xFF1A1A1A))
                Text(text = "/quintal", fontSize = 10.sp, color = Color(0xFF757575))
                Spacer(modifier = Modifier.height(6.dp))

                TrendBadge(isRising)
            }
        }
    }
}

@Composable
fun MandiChip(label: String, value: String, color: Color, bgColor: Color) {
    Column(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(bgColor).padding(horizontal = 8.dp, vertical = 4.dp)) {
        Text(label, fontSize = 9.sp, color = Color(0xFF757575))
        Text(value, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
    }
}

@Composable
fun TrendBadge(isRising: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(if (isRising) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)).padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = if (isRising) Icons.Default.TrendingUp else Icons.Default.TrendingDown,
            contentDescription = null,
            tint = if (isRising) Color(0xFF2E7D32) else Color(0xFFC62828),
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = if (isRising) "Rising" else "Falling", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = if (isRising) Color(0xFF2E7D32) else Color(0xFFC62828))
    }
}