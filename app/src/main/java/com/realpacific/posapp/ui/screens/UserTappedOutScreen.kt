package com.realpacific.posapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.realpacific.posapp.PrintManager
import com.realpacific.posapp.R
import com.realpacific.posapp.ui.components.ReceiptInfoCard


@Composable
fun UserTappedOutScreen(
  viewModel: HomeViewModel = viewModel(),
  modifier: Modifier = Modifier,
  navController: NavController = rememberNavController(),
) {
  val uiState by viewModel.uiState.collectAsState()
  val state = uiState
  val printManager = PrintManager(LocalContext.current)

  LaunchedEffect(Unit) {
    viewModel.exit()
  }

  if (state is HomeUIState.UserTappedOut) {
    Column(
      modifier = modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = R.drawable.check_circle_24),
        contentDescription = "Test",
        modifier = Modifier
          .animateContentSize()
          .fillMaxWidth()
          .size(120.dp)
      )
      Spacer(modifier = Modifier.height(20.dp))
      Row {
        Text(
          "Thank you, ",
          fontSize = 20.sp,
          fontWeight = FontWeight.Medium,
          color = MaterialTheme.colorScheme.primary,
          modifier = Modifier.alignByBaseline()
        )
        Text(
          state.userInfo.name,
          fontSize = 25.sp,
          fontWeight = FontWeight.Black,
          color = MaterialTheme.colorScheme.primary,
          modifier = Modifier.alignByBaseline()
        )
      }
      ReceiptInfoCard(
        receiptData = state.toReceiptData(),
        modifier = Modifier.padding(16.dp),
        onClick = {
          try {
            printManager.print(state.toReceiptData())
          } catch (ex: Exception) {
          }
          viewModel.exit()
        }
      )
    }
  }
}