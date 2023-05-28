package com.mahapp1397.codelearn2.screens.s2_login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.mahapp1397.codelearn2.R
import com.mahapp1397.codelearn2.navigation.Screen
import com.mahapp1397.codelearn2.screens.common.TopAppBar1
import com.mahapp1397.codelearn2.utils.Constants.RETURN_RESULT_KEY
import com.mahapp1397.codelearn2.view_models.S2ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController, navBackStackEntry: NavBackStackEntry)
{
    val s2ViewModel: S2ViewModel = hiltViewModel()
    var phoneNumber by remember { mutableStateOf("") }
    var isError1 by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }
    val savedPhoneNumber by s2ViewModel.phoneNumber.collectAsState()
    val focusManager = LocalFocusManager.current

    val popReturnResult by navBackStackEntry.savedStateHandle.getStateFlow(RETURN_RESULT_KEY, "EMPTY").collectAsState()

    if (popReturnResult == "success")
        s2ViewModel.getPhoneNumber()

    Scaffold(topBar = { TopAppBar1(stringResource(id = R.string.welcome)) }) { padding->

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
        {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {

                S2Content(phoneNumber = phoneNumber,
                          onValueChange = {phone->
                              phoneNumber = phone
                              isError1 = false
                          },
                          onClick = {

                              s2ViewModel.getPhoneNumber()

                              if (phoneNumber == "")
                              {
                                  isError1 = true
                                  errorText = "شماره همراه را وارد کنید."
                              }
                              else if (phoneNumber != savedPhoneNumber)
                              {
                                  isError1 = true
                                  errorText = "این شماره همراه ثبت نشده است."
                              }

                              else
                              {
                                  focusManager.clearFocus()
                                  navHostController.popBackStack()
                                  navHostController.navigate(Screen.Home.rout)
                              }
                          },
                          isError = isError1,
                          errorText = errorText)

                GoToRegisterScreen(onClick = {navHostController.navigate(Screen.Register.rout)})
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun S2Content(
    phoneNumber: String,
    onValueChange: (phone: String)-> Unit,
    onClick: ()-> Unit,
    isError: Boolean,
    errorText: String)
{
    BoxWithConstraints {

        ConstraintLayout {

            val (card, btn) = createRefs()

            Card(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(all = 50.dp)
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                 shape = RoundedCornerShape(15.dp),
                 elevation = CardDefaults.cardElevation(10.dp)) {

                Column(modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally) {

                    OutlinedTextField(value = phoneNumber,
                                      onValueChange = {phone -> onValueChange(phone)},
                                      modifier = Modifier.padding(8.dp),
                                      label = { Text(modifier = Modifier.fillMaxWidth(),
                                                     text = stringResource(R.string.phone_number),
                                                     textAlign = TextAlign.Center)},
                                      shape = TextFieldDefaults.outlinedShape,
                                      isError = isError,
                                      supportingText = {
                                          if (isError)
                                              Text(modifier = Modifier.fillMaxWidth(),
                                                   text = errorText)},
                                      trailingIcon = {if (isError)
                                          Icon(imageVector = Icons.Outlined.Info,
                                               contentDescription = "error",
                                               tint = MaterialTheme.colorScheme.error)
                                      },
                                      maxLines = 1,
                                      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
                }
            }

            Button(onClick = { onClick() },
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(bottom = 25.dp, start = 70.dp, end = 70.dp)
                       .height(40.dp)
                       .constrainAs(btn) {
                           start.linkTo(parent.start)
                           end.linkTo(parent.end)
                           bottom.linkTo(parent.bottom)
                       }) {
                Text(text = stringResource(R.string.enter))
            }
        }
    }

}

@Composable
fun GoToRegisterScreen(onClick: ()-> Unit)
{
    Text(text = stringResource(R.string.register),
         fontSize = MaterialTheme.typography.titleMedium.fontSize,
         modifier = Modifier
             .fillMaxWidth()
             .padding(top = 35.dp)
             .clickable { onClick() }
             .layoutId("txt2"),
         fontWeight = FontWeight.Bold,
         textAlign = TextAlign.Center,
         color = MaterialTheme.colorScheme.primary)
}