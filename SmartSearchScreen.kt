package com.example.worldtatu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.worldtatu.ui.components.TagChip
import com.example.worldtatu.ui.components.TattooCard
import com.example.worldtatu.ui.data.artistsResults
import com.example.worldtatu.ui.data.bodyResults
import com.example.worldtatu.ui.model.TattooWork
import kotlin.math.roundToInt

private enum class SearchTab(val title: String) { Sketch("Эскизы"), Body("На теле"), Artists("Мастера") }

@Composable
fun SmartSearchScreen(sketchesList: List<TattooWork>, modifier: Modifier = Modifier) {
    val (query, setQuery) = remember { mutableStateOf("Японский дракон на руке") }
    val (selectedTab, setTab) = remember { mutableStateOf(SearchTab.Sketch) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text("Умный поиск", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = query,
            onValueChange = setQuery,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Опишите идею словами") },
            trailingIcon = {
                Icon(Icons.Filled.Mic, contentDescription = "Голосом")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TagChip("#dragon")
            TagChip("#arm")
            TagChip("#irezumi")
        }
        Spacer(modifier = Modifier.height(12.dp))
        TabRow(selectedTabIndex = selectedTab.ordinal) {
            SearchTab.values().forEachIndexed { index, tab ->
                Tab(selected = selectedTab.ordinal == index, onClick = { setTab(tab) }, text = { Text(tab.title) })
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        when (selectedTab) {
            SearchTab.Sketch -> LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(sketchesList) { TattooCard(it) }
            }

            SearchTab.Body -> LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(bodyResults) { TattooCard(it) }
            }

            SearchTab.Artists -> LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(artistsResults) { artist ->
                    ArtistRow(
                        name = artist.name,
                        style = artist.style,
                        distance = artist.distanceKm,
                        rating = artist.rating,
                        priceFrom = artist.priceFrom
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
private fun ArtistRow(
    name: String,
    style: String,
    distance: Double,
    rating: Double,
    priceFrom: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.colorScheme.surface
                    )
                ),
                shape = AssistChipDefaults.shape
            )
            .padding(16.dp)
    ) {
        Text(name, style = MaterialTheme.typography.titleMedium)
        Text(style, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(6.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SuggestionChip(onClick = {}, label = { Text("★ ${(rating * 10).roundToInt() / 10.0}") })
            SuggestionChip(onClick = {}, label = { Text("от ${priceFrom}₽") })
            SuggestionChip(onClick = {}, label = { Text("${"%.1f".format(distance)} км") })
        }
    }
}

