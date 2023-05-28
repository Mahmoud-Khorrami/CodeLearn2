package com.mahapp1397.codelearn2.screens.s1_splash_screen

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mahapp1397.codelearn2.R
import com.mahapp1397.codelearn2.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController)
{

    LaunchedEffect(key1 = true){

        delay(1500)

        navHostController.popBackStack()
        navHostController.navigate(Screen.Login.rout)
    }

    val transition = rememberInfiniteTransition()
    val alphaAnim = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f ,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500,
                              easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse))

    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float)
{

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bkg1),
            contentDescription = "",
            contentScale = ContentScale.Crop)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                modifier = Modifier.size(100.dp).alpha(alpha),
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "",
                contentScale = ContentScale.Crop)

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Code Learn")
        }
    }
}

@Preview
@Composable
fun SplashScreenPrev()
{
    SplashScreen(navHostController = rememberNavController())
}