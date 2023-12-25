package com.realpacific.posapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen() {
  val navController = rememberNavController()

  val viewModel: HomeViewModel = viewModel()
  val state by viewModel.uiState.collectAsState()

  LaunchedEffect(state) {
    when (state) {
      HomeUIState.Empty -> {
        navController.navigate("tap_instruction")
      }

      is HomeUIState.UserTappedIn -> {
        navController.navigate("user_tapped_in")
      }

      is HomeUIState.UserTappedOut -> {
        navController.navigate("user_tapped_out")
      }

      else -> {}
    }
  }

  NavHost(navController = navController, startDestination = "tap_instruction") {
    composable("tap_instruction") {
      TapInstructionScreen(navController = navController, viewModel = viewModel)
    }
    composable("user_tapped_in") {
      UserTappedScreen(navController = navController, viewModel = viewModel)
    }
    composable("user_tapped_out") {
      UserTappedOutScreen(navController = navController, viewModel = viewModel)
    }
  }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  HomeScreen()
}