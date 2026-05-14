package com.example.siri_dhanyahub_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siri_dhanyahub_1.ui.theme.*
import com.example.siri_dhanyahub_1.ui.theme.MandiWatchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SiriDhanyaHub1Theme {
                SiriDhanyaHubApp()
            }
        }
    }
}

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SiriDhanyaHubApp() {
    var currentScreen by remember { mutableStateOf("mandi") }
    var previousScreen by remember { mutableStateOf("mandi") }

    val navItems = listOf(
        NavItem("Mandi", Icons.AutoMirrored.Filled.TrendingUp, "mandi"),
        NavItem("Recipes", Icons.Default.Restaurant, "recipe"),
        NavItem("Health", Icons.Default.Favorite, "health"),
        NavItem("Direct Buy", Icons.Default.ShoppingCart, "directbuy")
    )

    Scaffold(
        bottomBar = {
            Surface(
                shadowElevation = 16.dp,
                color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White,
                                    Color.White
                                )
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(ForestGreen)
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        navItems.forEach { item ->
                            val isSelected = currentScreen == item.route
                            val animatedWeight by animateFloatAsState(
                                targetValue = if (isSelected) 1.5f else 1f,
                                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                                label = "weight"
                            )

                            Column(
                                modifier = Modifier
                                    .weight(animatedWeight)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(
                                        if (isSelected) GoldenAmber
                                        else Color.Transparent
                                    )
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                IconButton(
                                    onClick = {
                                        previousScreen = currentScreen
                                        currentScreen = item.route
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.label,
                                        tint = if (isSelected) Color.White
                                        else Color.White.copy(alpha = 0.6f),
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Text(
                                    text = item.label,
                                    color = if (isSelected) Color.White
                                    else Color.White.copy(alpha = 0.6f),
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold
                                    else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        },
        containerColor = CreamWhite
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = {
                    (slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(350, easing = FastOutSlowInEasing)
                    ) + fadeIn(tween(350))).togetherWith(
                        slideOutHorizontally(
                            targetOffsetX = { -it },
                            animationSpec = tween(350, easing = FastOutSlowInEasing)
                        ) + fadeOut(tween(350))
                    )
                },
                label = "screen_transition"
            ) { screen ->
                when (screen) {
                    "mandi"     -> MandiWatchScreen()
                    "recipe"    -> RecipeLabScreen()
                    "health"    -> HealthBenefitsScreen()
                    "directbuy" -> DirectBuyScreen()
                }
            }
        }
    }
}