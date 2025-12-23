package com.example.worldtatu.ui.data

import com.example.worldtatu.R
import com.example.worldtatu.ui.model.Artist
import com.example.worldtatu.ui.model.Place
import com.example.worldtatu.ui.model.StyleCategory
import com.example.worldtatu.ui.model.TattooWork
import kotlin.random.Random

// Локальные ресурсы под твои три японские тату (нужно добавить файлы в res/drawable).
// Названия файлов должны совпадать с этими (см. инструкцию ниже в ответе):
//  - tattoo_irezumi_1.jpg
//  - tattoo_irezumi_2.jpg
//  - tattoo_irezumi_3.jpg
private val irezumiResIds: List<Int> = listOf(
    R.drawable.tattoo_irezumi_1,
    R.drawable.tattoo_irezumi_2,
    R.drawable.tattoo_irezumi_3
)

// Эскизы: локальные файлы tattoo_irezumi_4.jpg и tattoo_irezumi_5.jpg в drawable.

val feedWorks = listOf(
    TattooWork(
        id = "feed-irezumi-1",
        imageResId = irezumiResIds[0],
        artist = "Мастер 1",
        studio = "Студия A",
        style = "Японский (irezumi)",
        tags = listOf("#dragon", "#irezumi", "#sleeve"),
        likes = 531,
        bodyPart = "Плечо"
    ),
    TattooWork(
        id = "feed-irezumi-2",
        imageResId = irezumiResIds[1],
        artist = "Мастер 2",
        studio = "Студия B",
        style = "Японский (irezumi)",
        tags = listOf("#irezumi", "#backpiece"),
        likes = 680,
        bodyPart = "Спина"
    ),
    TattooWork(
        id = "feed-irezumi-3",
        imageResId = irezumiResIds[2],
        artist = "Мастер 3",
        studio = "Студия C",
        style = "Японский (irezumi)",
        tags = listOf("#dragon", "#koi", "#leg"),
        likes = 742,
        bodyPart = "Ноги"
    )
)

val sketchResults = List(6) { index ->
    val useArm = index % 2 == 0
    TattooWork(
        id = "sketch-local-$index",
        imageResId = if (useArm) R.drawable.tattoo_irezumi_4 else R.drawable.tattoo_irezumi_5,
        artist = "Эскиз‑мастер",
        studio = "Мир Тату",
        style = "Японский эскиз",
        tags = if (useArm) listOf("#dragon", "#irezumi", "#sketch", "#arm") else listOf("#irezumi", "#sketch", "#leg"),
        likes = 80 + index * 7,
        bodyPart = if (useArm) "Рука" else "Нога",
        isSketch = true
    )
}

val bodyResults = listOf(
    TattooWork(
        id = "body-irezumi-1",
        imageResId = irezumiResIds[0],
        artist = "Мастер 7",
        studio = "Студия G",
        style = "Японский",
        tags = listOf("#dragon", "#irezumi", "#shoulder"),
        likes = 610,
        bodyPart = "Плечо"
    ),
    TattooWork(
        id = "body-irezumi-2",
        imageResId = irezumiResIds[1],
        artist = "Мастер 8",
        studio = "Студия H",
        style = "Японский",
        tags = listOf("#irezumi", "#backpiece"),
        likes = 540,
        bodyPart = "Спина"
    ),
    TattooWork(
        id = "body-irezumi-3",
        imageResId = irezumiResIds[2],
        artist = "Мастер 9",
        studio = "Студия I",
        style = "Японский",
        tags = listOf("#irezumi", "#leg", "#fullsleeve"),
        likes = 720,
        bodyPart = "Ноги"
    )
)

val artistsResults = List(6) { index ->
    Artist(
        id = "artist-$index",
        name = "Тату-мастер ${index + 1}",
        style = "Irezumi / Black&Grey",
        distanceKm = Random.nextDouble(1.2, 12.0),
        priceFrom = Random.nextInt(6000, 20000),
        rating = Random.nextDouble(4.5, 5.0),
        avatar = "https://picsum.photos/seed/artist$index/200/200"
    )
}

val styleCategories = listOf(
    StyleCategory("traditional", "Традишнл", listOf("Розы", "Черепа", "Комиксы")),
    StyleCategory("realism", "Реализм", listOf("Портреты", "Животные", "Космос")),
    StyleCategory("linework", "Лайнворк", listOf("Минимал", "Геометрия", "Флора")),
    StyleCategory("watercolor", "Акварель", listOf("Птицы", "Цветы", "Абстракция"))
)

val mapPlaces = listOf(
    Place(
        id = "place-1",
        title = "Ink District",
        style = "Blackwork / Irezumi",
        rating = 4.9,
        distanceKm = 1.2,
            address = "Невский пр., 12",
            imageUrl = "" // карта пока использует заглушку, можно позже тоже перевести на resId
    ),
    Place(
        id = "place-2",
        title = "NeoTattoo",
        style = "Нео-традишнл / Реализм",
        rating = 4.7,
        distanceKm = 3.4,
            address = "Лиговский, 45",
            imageUrl = ""
    ),
    Place(
        id = "place-3",
        title = "Studio Hanami",
        style = "Irezumi / Color",
        rating = 4.8,
        distanceKm = 5.1,
            address = "Малая Морская, 7",
            imageUrl = ""
    )
)

