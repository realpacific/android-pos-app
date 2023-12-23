package com.realpacific.posapp.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.realpacific.posapp.ReceiptData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _receiptData = MutableStateFlow(
        ReceiptData(
            title = "Nepal Bus Service",
            receiptMeta = mapOf(
                "Customer Name" to "Prashant Barahi",
                "Bill Date" to "2023/Dec/22",
                "Bus Number" to "BA 1 PA 1292",
                "Route" to "Baneshwor-Ratnapark",
                "Tapped In Station" to "Baneshwor",
                "Tapped Out Station" to "Koteshwor",
                "Min Balance" to "Rs. 30",
            ),
            tableHeaderNames = listOf("Route", "NetAmt."),
            tableContents = listOf(
                listOf("Baneshwor-Koteshwor", "15"),
            )
        )
    )

    val receiptData = _receiptData.asStateFlow()
}