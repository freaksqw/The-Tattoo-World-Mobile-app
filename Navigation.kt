package com.example.worldtatu.ui

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Feed : Destination("feed", "Лента", Icons.Filled.Home)
    data object SmartSearch : Destination("smartSearch", "Поиск", Icons.Filled.Search)
    data object Map : Destination("map", "Карта", Icons.Filled.Map)
    data object Catalog : Destination("catalog", "Каталог", Icons.Filled.Explore)
    data object Profile : Destination("profile", "Профиль", Icons.Filled.AccountCircle)
}

val bottomDestinations = listOf(
    Destination.Feed,
    Destination.SmartSearch,
    Destination.Map,
    Destination.Catalog,
    Destination.Profile
)

