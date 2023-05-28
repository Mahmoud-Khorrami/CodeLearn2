package com.mahapp1397.codelearn2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mahapp1397.codelearn2.navigation.SetupNavGraph
import com.mahapp1397.codelearn2.ui.theme.CodeLearn2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity()
{
    private lateinit var navHostController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            CodeLearn2Theme {

                navHostController = rememberAnimatedNavController()
                SetupNavGraph(navHostController = navHostController)

            }
        }
    }
}