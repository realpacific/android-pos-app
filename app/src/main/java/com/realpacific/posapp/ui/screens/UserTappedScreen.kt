package com.realpacific.posapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun UserTappedScreen(
  modifier: Modifier = Modifier,
  viewModel: HomeViewModel = viewModel(),
  navController: NavController = rememberNavController(),
) {
  val uiState by viewModel.uiState.collectAsState()
  val state = uiState
  if (state is HomeUIState.UserTappedIn) {
    Box {
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
        Spacer(modifier = Modifier.height(10.dp))
        Row {
          Text(
            "Welcome ",
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
      }
      Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent, onClick = {
        viewModel.handleUserTappedOut()
      }) {}
    }
  }
}