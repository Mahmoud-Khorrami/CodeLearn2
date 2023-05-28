package com.mahapp1397.codelearn2.screens.s5_form

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mahapp1397.codelearn2.R

@Composable
fun S5Dialog(openDialog: MutableState<Boolean>,
             onClick: (id: Int) -> Unit)
{
    Dialog(onDismissRequest = { openDialog.value = false }) {
        DialogUI(onClick)
    }
}

@Composable
fun DialogUI(onClick: (id: Int) -> Unit)
{
    Card(shape = RoundedCornerShape(10.dp),
         elevation = CardDefaults.cardElevation(10.dp)) {
        Column {
            S5Row(id1 = R.drawable.woman1,
                  id2 = R.drawable.man1,
                  id3 = R.drawable.woman2,
                  onClick = onClick)

            S5Row(id1 = R.drawable.man2,
                  id2 = R.drawable.woman3,
                  id3 = R.drawable.man3,
                  onClick = onClick)
        }
    }
}

@Composable
fun S5Row(id1: Int, id2: Int, id3: Int, onClick: (id: Int) -> Unit)
{
    Row {
        S5Image(id = id1, onClick)
        S5Image(id = id2, onClick)
        S5Image(id = id3, onClick)
    }
}

@Composable
fun S5Image(id: Int, onClick: (id: Int)-> Unit)
{
    Image(painter = painterResource(id = id),
          modifier = Modifier
              .padding(8.dp)
              .size(80.dp)
              .clickable { onClick(id) },
          contentDescription = "")
}

@Preview(showBackground = true)
@Composable
fun X()
{
    DialogUI({})
}