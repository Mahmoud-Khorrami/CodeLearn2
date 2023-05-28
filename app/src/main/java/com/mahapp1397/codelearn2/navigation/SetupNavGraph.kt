package com.mahapp1397.codelearn2.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.mahapp1397.codelearn2.screens.s1_splash_screen.SplashScreen
import com.mahapp1397.codelearn2.screens.s2_login.LoginScreen
import com.mahapp1397.codelearn2.screens.s3_register.RegisterScreen
import com.mahapp1397.codelearn2.screens.s4_home.HomeScreen
import com.mahapp1397.codelearn2.screens.s5_form.ProfileFormScreen
import com.mahapp1397.codelearn2.screens.s6_details.DetailsScreen
import com.mahapp1397.codelearn2.utils.Constants.DETAILS_ARGUMENT_KEY

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(navHostController: NavHostController)
{
    AnimatedNavHost(navController = navHostController, startDestination = Screen.Splash.rout)
    {
        composable(route = Screen.Splash.rout,
                   enterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                       .SlideDirection.Left, animationSpec = tween(700))
                   },
                   exitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   popEnterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   },
                   popExitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   }){
            SplashScreen(navHostController)
        }

        composable(route = Screen.Login.rout,
                   enterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   exitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   popEnterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   },
                   popExitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   }){
            LoginScreen(navHostController = navHostController, navBackStackEntry = it)
        }

        composable(route = Screen.Register.rout){
            RegisterScreen(navHostController)
        }

        composable(route = Screen.Home.rout,
                   enterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   exitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   popEnterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   },
                   popExitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   }){
            HomeScreen(navHostController)
        }

        composable(route = Screen.ProfileForm.rout,
                   enterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   exitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   popEnterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   },
                   popExitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   }){
            ProfileFormScreen(navHostController)
        }

        composable(route = Screen.Details.rout,
                   arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY){type = NavType.IntType}),
                   enterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   exitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Left, animationSpec = tween(700))
                   },
                   popEnterTransition = {
                       slideIntoContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   },
                   popExitTransition = {
                       slideOutOfContainer(towards = AnimatedContentTransitionScope
                           .SlideDirection.Right, animationSpec = tween(700))
                   }){
            DetailsScreen(navHostController)
        }
    }
}