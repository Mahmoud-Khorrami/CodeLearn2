package com.mahapp1397.codelearn2.screens.s4_home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mahapp1397.codelearn2.models.Profile

@Composable
fun HomeItem(profile: Profile, onclick: (id: Int)-> Unit)
{
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight(align = Alignment.Top)
        .clickable { onclick(profile.id) },
         elevation = CardDefaults.cardElevation(8.dp),
         shape = CutCornerShape(topEnd = 24.dp),
        ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            ProfilePicture(profile.avatar)
            ProfileContent(profile = profile,
                           alignment = Alignment.CenterHorizontally)
        }
    }
}

@Composable
fun ProfilePicture(image: Int)
{
    AsyncImage(
        modifier = Modifier
            .padding(8.dp)
            .size(70.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = "")
}

@Composable
fun ProfileContent(profile: Profile,
                   alignment: Alignment.Horizontal)
{
    Column(modifier = Modifier.fillMaxWidth().padding(15.dp),
           horizontalAlignment = alignment) {

        Text(text = profile.name,
             style = MaterialTheme.typography.titleLarge)

        Text(text = profile.nationalCode,
             style = MaterialTheme.typography.bodyMedium, )
    }
}
