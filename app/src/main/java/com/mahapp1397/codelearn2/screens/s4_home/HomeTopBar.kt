package com.mahapp1397.codelearn2.screens.s4_home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.mahapp1397.codelearn2.ui.theme.topAppBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar()
{
    TopAppBar(
        title = { Text(text = "Home") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(topAppBarColor),
        navigationIcon = {
            Icon(imageVector = Icons.Default.Home,
                 contentDescription = "Home")
        },
        actions = {
            IconButton(onClick = {})
            {
                Icon(imageVector = Icons.Default.Search,
                     contentDescription = null)
            }
        })
}