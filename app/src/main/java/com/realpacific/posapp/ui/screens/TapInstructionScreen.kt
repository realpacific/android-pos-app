package com.realpacific.posapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.realpacific.posapp.R

@Composable
fun TapInstructionScreen(
  modifier: Modifier = Modifier,
  viewModel: HomeViewModel = viewModel(),
  navController: NavController = rememberNavController(),
) {
  Box {
    Column(
      modifier = modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = R.drawable.img_contactless_pay), contentDescription = "Test"
      )
      Spacer(modifier = Modifier.height(10.dp))
      Text(
        "Please tap the card",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
      )
      Text(
        "To pay, please tap your card", fontSize = 14.sp, fontWeight = FontWeight.Medium
      )
    }
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent, onClick = {
      viewModel.userTappedIn()
    }) {}
  }
}