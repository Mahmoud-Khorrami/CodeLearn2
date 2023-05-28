package com.mahapp1397.codelearn2.screens.s3_register

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mahapp1397.codelearn2.R
import com.mahapp1397.codelearn2.screens.common.TopAppBar1
import com.mahapp1397.codelearn2.utils.Constants.RETURN_RESULT_KEY
import com.mahapp1397.codelearn2.view_models.S3ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(navHostController: NavHostController,
                   s3ViewModel: S3ViewModel = hiltViewModel())
{
    val snackBarHostState = remember { SnackbarHostState() }
    var phoneNumber by remember { mutableStateOf("") }
    var isError1 by remember { mutableStateOf(false) }
    var isError2 by remember { mutableStateOf(false) }
    var visible1 by rememberSaveable { mutableStateOf(true) }
    var visible2 by rememberSaveable { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val timer by s3ViewModel.time
    var randomCode = 0
    var vCode by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Scaffold(modifier = Modifier.fillMaxSize(),
            snackbarHost = {SnackbarHost(snackBarHostState)},
            topBar = {TopAppBar1(title = stringResource(id = R.string.register))}) { padding->

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

            BoxWithConstraints(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {

                LaunchedEffect(key1 = true){
                    delay(100)
                    visible1 = true
                }

                ConstraintLayout {

                    val (av1, av2) = createRefs()

                    AnimatedVisibility(visible = visible1,
                                       modifier = Modifier
                                           .fillMaxWidth()
                                           .height(300.dp)
                                           .constrainAs(av1) {
                                               top.linkTo(anchor = parent.top)
                                               start.linkTo(anchor = parent.start)
                                               end.linkTo(anchor = parent.end)
                                           },
                                       enter = scaleIn(animationSpec = tween(1500)),
                                       exit = scaleOut(animationSpec = tween(1500))) {

                        Content1(phoneNumber = phoneNumber,
                                 onValueChange = {phone->
                                     phoneNumber = phone
                                     isError1 = false },
                                 onClick = {
                                     if (phoneNumber == "")
                                     {
                                         isError1 = true
                                         errorText = "شماره همراه را وارد کنید."
                                     }

                                     else if (phoneNumber.length < 10)
                                     {
                                         isError1 = true
                                         errorText = "شماره همراه حداقل باید 10 رقم باشد."
                                     }

                                     else
                                     {
                                         focusManager.clearFocus()

                                         scope.launch {
                                             visible1 = false
                                             delay(1000)
                                             visible2 = true

                                             randomCode = (1000..9999).random()
                                             snackBarHostState.showSnackbar(message = "کد " +
                                                                                      "فعالسازی :" +
                                                                                      " $randomCode")
                                         }

                                         s3ViewModel.startTimer()
                                     }
                                 },
                                 isError = isError1,
                                 errorText = errorText)

                    }

                    AnimatedVisibility(visible = visible2,
                                       modifier = Modifier
                                           .fillMaxWidth()
                                           .wrapContentHeight()
                                           .constrainAs(av2) {
                                               top.linkTo(anchor = parent.top)
                                               start.linkTo(anchor = parent.start)
                                               end.linkTo(anchor = parent.end)
                                           },
                                       enter = scaleIn(animationSpec = tween(1500)),
                                       exit = scaleOut(animationSpec = tween(1500))) {

                        Content2(timer = timer,
                                 vCode = vCode,
                                 onValueChange = {code ->
                                     vCode = code
                                     isError2 = false},
                                 onApproveClick = {
                                         if (vCode == "" || vCode == "0" || vCode.toInt() != randomCode)
                                         {
                                             isError2 = true
                                             errorText = "کد فعالسازی اشتباه است."
                                         }

                                         else
                                         {
                                             Toast.makeText(context, "شماره همراه ثبت شد.", Toast.LENGTH_SHORT).show()
                                             s3ViewModel.savePhoneNumber(phoneNumber)
                                             navHostController.popBackStack()
                                             navHostController.currentBackStackEntry?.savedStateHandle?.set(RETURN_RESULT_KEY, "success")
                                         }
                                 },
                                 onResendClick = {
                                     s3ViewModel.startTimer()
                                     scope.launch {
                                         randomCode = (1000..9999).random()
                                         snackBarHostState.showSnackbar(message = "کد فعالسازی: " +
                                                                                  "$randomCode")
                                     }
                                                 },
                                isError = isError2,
                                errorText = errorText)

                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content1(phoneNumber: String,
             onValueChange: (phone: String)-> Unit,
             onClick: ()-> Unit,
             isError: Boolean,
             errorText: String)
{
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (card, btn) = createRefs()

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .height(200.dp)
            .constrainAs(card) {
                top.linkTo(anchor = parent.top)
                start.linkTo(anchor = parent.start)
                end.linkTo(anchor = parent.end)
            },
             shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(10.dp)) {

            Column(modifier = Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(value = phoneNumber,
                          onValueChange = {phone-> onValueChange(phone)},
                          modifier = Modifier.padding(8.dp),
                          label = { Text(modifier = Modifier.fillMaxWidth(),
                                         text = stringResource(R.string.phone_number),
                                         textAlign = TextAlign.Center)},
                          isError = isError,
                          supportingText = {
                              if (isError)
                                Text(modifier = Modifier.fillMaxWidth(),
                                     text = errorText)},
                          trailingIcon = {if (isError)
                              Icon(imageVector = Icons.Outlined.Info,
                                   contentDescription = "error",
                                   tint = MaterialTheme.colorScheme.error)},
                          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
            }
        }

        Button(onClick = { onClick() },
               modifier = Modifier
                   .fillMaxWidth()
                   .height(50.dp)
                   .offset(y = (- 50).dp)
                   .padding(start = 70.dp, end = 70.dp)
                   .constrainAs(btn) {
                       top.linkTo(anchor = card.bottom)
                       start.linkTo(anchor = card.start)
                       end.linkTo(anchor = card.end)
                   }) {
            Text(text = stringResource(R.string.register))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content2(timer: Int,
             vCode: String,
             onValueChange: (code: String) -> Unit,
             onApproveClick: ()-> Unit,
             onResendClick: ()-> Unit,
             isError: Boolean,
             errorText: String)
{
    Box {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(30.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(10.dp) ,) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(value = vCode,
                                  onValueChange = {code-> onValueChange(code)},
                                  modifier = Modifier.padding(8.dp),
                                  label = { Text(modifier = Modifier.fillMaxWidth(),
                                                 text = stringResource(R.string.validation_code),
                                                 textAlign = TextAlign.Center)},

                                  isError = isError,
                                  supportingText = {
                                      if (isError)
                                          Text(modifier = Modifier.fillMaxWidth(),
                                               text = errorText)},
                                  trailingIcon = {if (isError)
                                      Icon(imageVector = Icons.Outlined.Info,
                                           contentDescription = "error",
                                           tint = MaterialTheme.colorScheme.error)},
                                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

                Button(onClick = { onApproveClick() },
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                           .height(40.dp)) {
                    Text(text = stringResource(R.string.approve))
                }

                Text(text = stringResource(R.string.remain_time),
                     modifier = Modifier.padding(top = 20.dp))

                Text(text = (if(((timer / 1000) - 1)< 0) 0 else ((timer / 1000) - 1)).toString(),
                     modifier = Modifier.padding(all = 20.dp))

                if (timer < 2000)
                    Row(modifier = Modifier
                        .padding(all = 30.dp)) {

                        Spacer(modifier = Modifier.weight(2f))

                        Button(onClick = { onResendClick() },
                               modifier = Modifier.height(40.dp)) {
                            Text(text = stringResource(R.string.re_send))
                        }
                    }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview()
{
    RegisterScreen(rememberNavController())
}