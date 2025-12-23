package com.example.worldtatu.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldtatu.ui.screens.CatalogScreen
import com.example.worldtatu.ui.screens.FeedScreen
import com.example.worldtatu.ui.screens.MapScreen
import com.example.worldtatu.ui.screens.ProfileScreen
import com.example.worldtatu.ui.screens.SmartSearchScreen
import com.example.worldtatu.ui.screens.AuthScreen
import com.example.worldtatu.ui.screens.CreateSketchScreen
import com.example.worldtatu.ui.data.feedWorks
import com.example.worldtatu.ui.data.sketchResults
import com.example.worldtatu.ui.model.TattooWork

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldTatuApp(
    useDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    val isAuthed = rememberSaveable { mutableStateOf(false) }
    val userEmail = rememberSaveable { mutableStateOf<String?>(null) }

    val feedList = remember { mutableStateListOf<TattooWork>().apply { addAll(feedWorks) } }
    val sketchesList = remember { mutableStateListOf<TattooWork>().apply { addAll(sketchResults) } }

    if (!isAuthed.value) {
        AuthScreen(
            onSuccess = { email ->
                isAuthed.value = true
                userEmail.value = email
            }
        )
        return
    }

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route
                bottomDestinations.forEach { dest ->
                    NavigationBarItem(
                        selected = currentRoute == dest.route,
                        onClick = { navController.navigate(dest.route) },
                        icon = { Icon(dest.icon, contentDescription = dest.label) },
                        label = { Text(dest.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Destination.Feed.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Destination.Feed.route) { FeedScreen(items = feedList) }
            composable(Destination.SmartSearch.route) { SmartSearchScreen(sketchesList = sketchesList) }
            composable(Destination.Map.route) { MapScreen() }
            composable(Destination.Catalog.route) { CatalogScreen() }
            composable(Destination.Profile.route) {
                ProfileScreen(
                    userEmail = userEmail.value ?: "user",
                    useDarkTheme = useDarkTheme,
                    onToggleTheme = onToggleTheme,
                    onLogout = {
                        isAuthed.value = false
                        userEmail.value = null
                    },
                    liked = feedList.take(3),
                    favoriteMasters = com.example.worldtatu.ui.data.artistsResults.take(3),
                    onCreateSketch = { navController.navigate("createSketch") }
                )
            }
            composable("createSketch") {
                CreateSketchScreen(
                    onPublish = { work ->
                        sketchesList.add(0, work)
                        feedList.add(0, work)
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

