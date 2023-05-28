package com.mahapp1397.codelearn2.screens.s4_home

import androidx.compose.foundation.ScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeFab(scrollState: ScrollState, onClick: ()-> Unit)
{
    if (scrollState.value > 100)
    {
        ExtendedFloatingActionButton(
            text = { Text(text = "Compose")},
            onClick = {onClick()},
            icon = { Icon(imageVector = Icons.Default.Edit , contentDescription = "") })
    }

    else
    {
        FloatingActionButton(onClick = {onClick()})
        {
            Icon(imageVector = Icons.Default.Add , contentDescription = "")
        }
    }
}