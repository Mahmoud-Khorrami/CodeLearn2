package com.mahapp1397.codelearn2.screens.s6_details

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.ui.theme.topAppBarColor
import com.mahapp1397.codelearn2.view_models.S6ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navHostController: NavHostController,
                  s6ViewModel: S6ViewModel = hiltViewModel())
{
    val profile by s6ViewModel.profiles.collectAsState()
    var isExpand by remember { mutableStateOf(false) }
    val imageSize by animateDpAsState(targetValue = if (isExpand) 200.dp else 150.dp)

    profile?.let {profile1->

        Scaffold(
            topBar = { S6TopBar(onBackClick = {navHostController.navigateUp()}) }) {


            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {

                Card(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.Top),
                     elevation = CardDefaults.cardElevation(8.dp),
                     shape = RoundedCornerShape(12.dp)
                    ) {

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center) {
                        ProfilePicture(profile1.avatar, imageSize)
                        ProfileContent(profile = profile1,
                                       alignment = Alignment.CenterHorizontally,
                                       isExpand = isExpand,
                                       onArrowClick = {isExpand = !isExpand})
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun S6TopBar(onBackClick: ()-> Unit)
{
    TopAppBar(title = { Text(text = "Profile Details") },
              colors = TopAppBarDefaults.smallTopAppBarColors(topAppBarColor),
              navigationIcon = { IconButton(onClick = onBackClick) {
                  Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
              } })
}

@Composable
fun ProfilePicture(image: Int, imageSize: Dp)
{
    AsyncImage(
        modifier = Modifier
            .padding(8.dp)
            .size(imageSize),
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = "")
}

@Composable
fun ProfileContent(profile: Profile,
                   alignment: Alignment.Horizontal,
                   isExpand: Boolean,
                   onArrowClick : ()-> Unit)
{
    Column(modifier = Modifier.padding(8.dp),
           horizontalAlignment = alignment) {

        Text(text = profile.name,
             style = MaterialTheme.typography.titleLarge)

        Text(text = profile.nationalCode,
             style = MaterialTheme.typography.bodyMedium )

        Text(text = profile.phoneNumber,
             style = MaterialTheme.typography.bodyMedium )

        Text(text = profile.description,
             modifier = Modifier.padding(top = 10.dp),
             style = MaterialTheme.typography.bodySmall,
             maxLines = if (isExpand) 10 else 2)

        Icon(imageVector = if (isExpand) Icons.Default.KeyboardArrowUp
        else Icons.Default.KeyboardArrowDown,
             contentDescription = "",
             modifier = Modifier.clickable { onArrowClick() })
    }
}
