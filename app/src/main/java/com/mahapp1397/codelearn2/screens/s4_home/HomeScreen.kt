package com.mahapp1397.codelearn2.screens.s4_home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.navigation.Screen
import com.mahapp1397.codelearn2.view_models.S4ViewModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController)
{
    val scrollState = rememberScrollState()
    val s4ViewModel: S4ViewModel = hiltViewModel()
    val profiles: StateFlow<List<Profile>> = s4ViewModel.profiles

    Scaffold(topBar = { HomeTopBar() },
             floatingActionButton = {HomeFab(scrollState= scrollState,
                                             onClick = {navHostController.navigate(Screen.ProfileForm.rout)})},
             modifier = Modifier.fillMaxSize()) {padding->

        LazyColumn(modifier = Modifier.padding(padding))
        {
            items(profiles.value){
                HomeItem(profile = it) {id ->
                    navHostController.navigate(Screen.Details.passProfileId(id))
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPrev()
{
    HomeScreen(navHostController = rememberNavController())
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDPrev()
{
    HomeScreen(navHostController = rememberNavController())
}