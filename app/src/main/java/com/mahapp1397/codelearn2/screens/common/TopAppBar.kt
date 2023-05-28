package com.mahapp1397.codelearn2.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mahapp1397.codelearn2.R

@Composable
fun TopAppBar1(title: String)
{
    BoxWithConstraints {

        ConstraintLayout {

            val (txt) = createRefs()

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.bkg2
                else R.drawable.bkg3),
                contentDescription = null,
                contentScale = ContentScale.FillBounds)

            Text(text = title,
                 fontSize = MaterialTheme.typography.titleLarge.fontSize,
                 modifier = Modifier
                     .padding(top = 25.dp)
                     .constrainAs(txt) {
                         top.linkTo(parent.top)
                         start.linkTo(parent.start)
                         end.linkTo(parent.end)
                     },
                 fontWeight = FontWeight.Bold)
        }

    }
}