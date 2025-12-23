package com.example.worldtatu.ui.model

data class TattooWork(
    val id: String,
    // Либо ссылка, либо локальный ресурс. Для твоих тату из ленты будем использовать imageResId.
    val imageUrl: String? = null,
    val imageResId: Int? = null,
    val artist: String,
    val studio: String,
    val style: String,
    val tags: List<String>,
    val likes: Int,
    val bodyPart: String,
    val isSketch: Boolean = false,
    val rating: Double = 4.8
)

data class Artist(
    val id: String,
    val name: String,
    val style: String,
    val distanceKm: Double,
    val priceFrom: Int,
    val rating: Double,
    val avatar: String
)

data class StyleCategory(
    val id: String,
    val title: String,
    val items: List<String>
)

data class Place(
    val id: String,
    val title: String,
    val style: String,
    val rating: Double,
    val distanceKm: Double,
    val address: String,
    val imageUrl: String
)

