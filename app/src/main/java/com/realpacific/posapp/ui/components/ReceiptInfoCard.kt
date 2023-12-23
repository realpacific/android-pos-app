package com.realpacific.posapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.realpacific.posapp.ReceiptData

@Composable
fun ReceiptInfoCard(
  receiptData: ReceiptData, modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Card(modifier = modifier.padding(3.dp)) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      InfoCardRow(
        label = "Today's Date",
        value = receiptData.receiptMeta.getValue("Bill Date")
      )
      InfoCardRow(
        label = "Plate Number",
        value = receiptData.receiptMeta.getValue("Bus Number")
      )
      InfoCardRow(label = "Route", value = receiptData.receiptMeta.getValue("Route"))
      InfoCardRow(
        label = "Minimum Balance",
        value = "${receiptData.receiptMeta["Min Balance"]}"
      )
    }
    ElevatedButton(
      enabled = true,
      colors = ButtonDefaults.elevatedButtonColors(),
      modifier = Modifier
        .padding(24.dp)
        .align(Alignment.CenterHorizontally),
      onClick = onClick
    ) {
      Text("Continue")
    }
  }
}