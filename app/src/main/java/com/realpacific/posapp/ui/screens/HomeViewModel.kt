package com.realpacific.posapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.realpacific.posapp.ReceiptData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class BusInfo(
  val plateNumber: String,
  val beginRoute: String,
  val destinationRoute: String,
)

data class UserInfo(
  val name: String,
)

data class TransactionInfo(
  val tappedIn: String,
  val tappedOut: String,
  val charge: String,
)

sealed class HomeUIState {
  data object Empty : HomeUIState()

  class UserTappedIn(
    val userInfo: UserInfo,
    val busInfo: BusInfo,
  ) : HomeUIState()

  class UserTappedOut(
    val userInfo: UserInfo,
    val busInfo: BusInfo,
    val transactionInfo: TransactionInfo,
  ) : HomeUIState() {
    fun toReceiptData(): ReceiptData {
      return ReceiptData(
        title = "Nepal Bus Service",
        receiptMeta = mapOf(
          "Customer Name" to userInfo.name,
          "Bill Date" to "2023/Dec/22",
          "Bus Number" to busInfo.plateNumber,
          "Route" to "${busInfo.beginRoute}-${busInfo.destinationRoute}",
          "Tapped In Station" to transactionInfo.tappedIn,
          "Tapped Out Station" to transactionInfo.tappedOut,
          "Min Balance" to "Rs. 30",
        ),
        tableHeaderNames = listOf("Route", "NetAmt."),
        tableContents = listOf(
          listOf("${transactionInfo.tappedIn}-${transactionInfo.tappedOut}", "15"),
        )
      )
    }
  }
}

class HomeViewModel : ViewModel() {

  private val _uiState = MutableStateFlow<HomeUIState>(HomeUIState.Empty)
  val uiState = _uiState.asStateFlow()

  fun userTappedIn() {
    _uiState.value = HomeUIState.UserTappedIn(
      userInfo = UserInfo("Prashant Barahi"),
      busInfo = BusInfo(
        "BA 1 PA 1292",
        "Baneshwor",
        "Ratnapark"
      )
    )
  }

  fun handleUserTappedOut() {
    _uiState.value = HomeUIState.UserTappedOut(
      userInfo = UserInfo("Prashant Barahi"),
      busInfo = BusInfo(
        "BA 1 PA 1292",
        "Baneshwor",
        "Ratnapark"
      ),
      transactionInfo = TransactionInfo(
        "Baneshwor",
        "Koteshwor",
        "Rs. 15"
      )
    )
//    viewModelScope.launch {
//      delay(3000L)
//      _uiState.value = HomeUIState.Empty
//    }
  }

  fun exit() {
    viewModelScope.launch {
      delay(3000L)
      _uiState.value = HomeUIState.Empty
    }
  }
}