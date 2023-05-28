package com.mahapp1397.codelearn2.screens.s5_form

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mahapp1397.codelearn2.R
import com.mahapp1397.codelearn2.ui.theme.topAppBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun S5TopBar()
{
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.register_profile)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(topAppBarColor))
}