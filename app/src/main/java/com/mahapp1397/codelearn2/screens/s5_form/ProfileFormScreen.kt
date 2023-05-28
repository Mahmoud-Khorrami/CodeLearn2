package com.mahapp1397.codelearn2.screens.s5_form

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mahapp1397.codelearn2.R
import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.view_models.S5ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileFormScreen(navHostController: NavHostController)
{
    val s5ViewModel: S5ViewModel = hiltViewModel()
    val scrollState = rememberScrollState()


    Scaffold(topBar = { S5TopBar() },
             modifier = Modifier.fillMaxSize()) { padding->
        
        Content(padding = padding,
                scrollState = scrollState,
               onApproveClick = {profile ->
                    s5ViewModel.saveProfile(profile)
                    navHostController.popBackStack()})
    }
}

@Composable
fun Content(padding: PaddingValues,
            scrollState: ScrollState,
            onApproveClick: (profile: Profile) -> Unit)
{
    var name by remember { mutableStateOf("") }
    var nationalCode by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var avatar by remember { mutableIntStateOf(R.drawable.woman1) }
    var errorText by remember { mutableStateOf("") }
    var isError1 by remember { mutableStateOf(false) }
    var isError2 by remember { mutableStateOf(false) }
    var isError3 by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value)
        S5Dialog(openDialog = openDialog) { id->
            avatar = id
            openDialog.value = false
        }

    Column(modifier = Modifier
        .padding(padding)
        .verticalScroll(scrollState)) {

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            ConstraintLayout {

                val (card, img, btn) = createRefs()

                Image(painter = painterResource(id = avatar),
                      modifier = Modifier
                          .padding(start = 20.dp, top = 20.dp)
                          .size(80.dp)
                          .zIndex(2f)
                          .constrainAs(img) {
                              top.linkTo(card.top)
                              start.linkTo(card.start)
                          }
                          .clickable { openDialog.value = true },
                      contentDescription = "Avatar")

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp)
                    .constrainAs(card) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                     elevation = CardDefaults.cardElevation(10.dp)) {
                    
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp, start = 10.dp, end = 10.dp, bottom = 10.dp))
                    {
                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
                        {
                            ProfileTextField(value = name,
                                             onValueChange = { value ->
                                                 name = value
                                                 isError1 = false},
                                             label = stringResource(R.string.name_and_family),
                                             isError = isError1,
                                             errorText = errorText,
                                             keyboardType = KeyboardType.Text)

                            ProfileTextField(value = nationalCode,
                                             onValueChange = { value ->
                                                 nationalCode = value
                                                 isError2 = false },
                                             label = stringResource(R.string.national_code),
                                             isError = isError2,
                                             errorText = errorText,
                                             keyboardType = KeyboardType.Number)

                            ProfileTextField(value = phoneNumber,
                                             onValueChange = { value ->
                                                 phoneNumber = value
                                                 isError3 = false },
                                             label = stringResource(R.string.phone_number),
                                             isError = isError3,
                                             errorText = errorText,
                                             keyboardType = KeyboardType.Phone)
                        }
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 60.dp, end = 60.dp)
                        .offset(y = (- 70).dp)
                        .constrainAs(btn) {
                            top.linkTo(card.bottom)
                            start.linkTo(card.start)
                            end.linkTo(card.end)
                        },
                    onClick = {

                        when
                        {
                            name == "" ->
                            {
                                isError1 = true
                                errorText = context.getString(R.string.enter_name)
                            }
                            nationalCode == "" ->
                            {
                                isError2 = true
                                errorText = context.getString(R.string.enter_national_code)
                            }
                            phoneNumber == "" ->
                            {
                                isError3 = true
                                errorText = context.getString(R.string.enter_phone_number)
                            }
                            else ->
                            {
                                val description = context.getString(R.string.description)
                                val profile = Profile(0, name, nationalCode, phoneNumber, avatar, description)
                                onApproveClick(profile)
                            }
                        }

                    }) {
                        Text(text = stringResource(id = R.string.approve))
                }

            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(value: String,
                      onValueChange: (value: String) -> Unit,
                      label: String,
                      isError: Boolean,
                      errorText: String,
                      keyboardType: KeyboardType)
{
    OutlinedTextField(value = value,
                      onValueChange = {value1-> onValueChange(value1)},
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(15.dp),
                      label = { Text(modifier = Modifier.fillMaxWidth(),
                                     text = label,
                                     textAlign = TextAlign.Center)
                      },
                      isError = isError,
                      supportingText = {
                          if (isError)
                              Text(modifier = Modifier.fillMaxWidth(),
                                   text = errorText)
                      },
                      trailingIcon = {if (isError)
                          Icon(imageVector = Icons.Outlined.Info,
                               contentDescription = "error",
                               tint = MaterialTheme.colorScheme.error)
                      },
                      keyboardOptions = KeyboardOptions(keyboardType = keyboardType))
}

@Preview
@Composable
fun HomeScreenPrev()
{
    ProfileFormScreen(navHostController = rememberNavController())
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun HomeScreenDPrev()
//{
//    ProfileFormScreen(navHostController = rememberNavController())
//}