package com.example.worldtatu.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.worldtatu.ui.components.TattooCard
import com.example.worldtatu.ui.data.feedWorks
import com.example.worldtatu.ui.model.Artist
import com.example.worldtatu.ui.model.TattooWork

@Composable
fun ProfileScreen(
    userEmail: String,
    useDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onLogout: () -> Unit,
    liked: List<TattooWork>,
    favoriteMasters: List<Artist>,
    onCreateSketch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TopBar(useDarkTheme = useDarkTheme, onToggleTheme = onToggleTheme, onLogout = onLogout)
        Spacer(modifier = Modifier.height(8.dp))
        UserCard(userEmail = userEmail)
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Виктория Ито", style = MaterialTheme.typography.titleMedium)
                Text("Irezumi, Black&Grey • 8 лет опыта", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.padding(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {}) { Text("Записаться") }
                    Button(onClick = {}) { Text("Чат") }
                    Button(onClick = onCreateSketch) { Text("Новый эскиз") }
                }
            }
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Text("Портфолио", style = MaterialTheme.typography.titleMedium)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(feedWorks.take(4)) { TattooCard(it) }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("Понравившиеся эскизы", style = MaterialTheme.typography.titleMedium)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(vertical = 8.dp)) {
            items(liked) { work ->
                Card(
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth(0.6f),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(work.imageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text("Понравившиеся мастера", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(top = 6.dp)) {
            items(favoriteMasters) { artist ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.large)
                        .padding(12.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(artist.avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .height(48.dp)
                            .clip(CircleShape)
                    )
                    Column {
                        Text(artist.name, style = MaterialTheme.typography.titleMedium)
                        Text(artist.style, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("★ ${"%.1f".format(artist.rating)} • ${"%.1f".format(artist.distanceKm)} км")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 80.dp))
    }
}

@Composable
private fun TopBar(useDarkTheme: Boolean, onToggleTheme: () -> Unit, onLogout: () -> Unit) {
    val (menuOpen, setMenuOpen) = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Профиль мастера", style = MaterialTheme.typography.titleLarge)
        Box {
            IconButton(onClick = { setMenuOpen(true) }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Настройки")
            }
            DropdownMenu(expanded = menuOpen, onDismissRequest = { setMenuOpen(false) }) {
                DropdownMenuItem(
                    text = { Text(if (useDarkTheme) "Светлая тема" else "Тёмная тема") },
                    onClick = {
                        onToggleTheme()
                        setMenuOpen(false)
                    },
                    leadingIcon = {
                        Icon(
                            if (useDarkTheme) Icons.Filled.WbSunny else Icons.Filled.Nightlight,
                            contentDescription = null
                        )
                    }
                )
                DropdownMenuItem(
                    text = { Text("Изменить профиль (заглушка)") },
                    onClick = { setMenuOpen(false) }
                )
                DropdownMenuItem(
                    text = { Text("Выйти") },
                    onClick = {
                        setMenuOpen(false)
                        onLogout()
                    },
                    leadingIcon = { Icon(Icons.Filled.Logout, contentDescription = null) }
                )
            }
        }
    }
}

@Composable
private fun UserCard(userEmail: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://picsum.photos/seed/profile/200/200"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(64.dp)
                    .clip(CircleShape)
            )
            Column {
                Text("Виктория Ито", style = MaterialTheme.typography.titleMedium)
                Text(userEmail, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Irezumi / Black&Grey • Санкт-Петербург", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

