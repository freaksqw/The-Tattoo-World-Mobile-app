package com.example.worldtatu.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.worldtatu.ui.model.TattooWork
import kotlin.random.Random

@Composable
fun CreateSketchScreen(
    onPublish: (TattooWork) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (title, setTitle) = remember { mutableStateOf("Эскиз: Дракон на руке") }
    val (style, setStyle) = remember { mutableStateOf("Irezumi / Blackwork") }
    val (bodyPart, setBodyPart) = remember { mutableStateOf("Предплечье") }
    val (tags, setTags) = remember { mutableStateOf("#dragon #arm #irezumi") }
    val (imageUrl, setImageUrl) = remember { mutableStateOf("https://images.unsplash.com/photo-1503389152951-9f343605f61e?auto=format&fit=crop&w=1200&q=80") }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Новый эскиз мастера")
        OutlinedTextField(value = title, onValueChange = setTitle, label = { Text("Название") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = style, onValueChange = setStyle, label = { Text("Стиль") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = bodyPart, onValueChange = setBodyPart, label = { Text("Часть тела") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = tags, onValueChange = setTags, label = { Text("Теги через пробел") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = imageUrl, onValueChange = setImageUrl, label = { Text("Ссылка на изображение") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = onBack, modifier = Modifier.weight(1f)) { Text("Назад") }
            Button(
                onClick = {
                    val work = TattooWork(
                        id = "created-${Random.nextInt()}",
                        imageUrl = imageUrl,
                        artist = "Вы",
                        studio = "Ваша студия",
                        style = style,
                        tags = tags.split(" ").filter { it.isNotBlank() },
                        likes = 0,
                        bodyPart = bodyPart,
                        isSketch = true
                    )
                    onPublish(work)
                    onBack()
                },
                modifier = Modifier.weight(1f)
            ) { Text("Опубликовать") }
        }
    }
}

