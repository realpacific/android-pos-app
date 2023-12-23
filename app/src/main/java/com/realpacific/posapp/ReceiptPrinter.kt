package com.realpacific.posapp

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import recieptservice.com.recieptservice.PrinterInterface

data class ReceiptData(
    val title: String,
    val receiptMeta: Map<String, String>,
    val tableHeaderNames: List<String>,
    val tableContents: List<List<String?>>,
)

const val TEXT_ALIGNMENT_LEFT = 0
const val TEXT_ALIGNMENT_RIGHT = 2
const val TEXT_ALIGNMENT_CENTER = 1

class PrintManager(private val context: Context) {
  fun print(receiptData: ReceiptData) {
    val intent = Intent()
    intent.setClassName(
      "recieptservice.com.recieptservice",
      "recieptservice.com.recieptservice.service.PrinterService"
    )
    val connection = object : ServiceConnection {
      override fun onServiceConnected(name: ComponentName, service: IBinder) {
        aidl(service) {
          nextLine(1)

          setTextSize(23f)
          setAlignment(TEXT_ALIGNMENT_CENTER)


          withBold {
            printText(receiptData.title)
          }

          nextLine(1)

          setTextSize(20f)

          setAlignment(TEXT_ALIGNMENT_LEFT)

          receiptData.receiptMeta.forEach { (key, value) ->
            printText("$key: $value")
            nextLine(1)
          }

          printText("-------------------------------------")
          nextLine(1)

          withBold {
            printTableText(
              receiptData.tableHeaderNames.toTypedArray(),
              IntArray(receiptData.tableHeaderNames.size) { 2 },
              IntArray(receiptData.tableHeaderNames.size) {
                when (it) {
                  0 -> TEXT_ALIGNMENT_LEFT
                  receiptData.tableHeaderNames.lastIndex -> TEXT_ALIGNMENT_RIGHT
                  else -> TEXT_ALIGNMENT_CENTER
                }
              },
            )
          }

          printText("-------------------------------------")
          nextLine(1)

          setTextSize(18f)
          receiptData.tableContents.forEach { row ->
            printTableText(
              row.map { it ?: "" }.toTypedArray(),
              IntArray(row.size) { 1 },
              IntArray(row.size) {
                when (it) {
                  0 -> TEXT_ALIGNMENT_LEFT
                  receiptData.tableHeaderNames.lastIndex -> TEXT_ALIGNMENT_RIGHT
                  else -> TEXT_ALIGNMENT_CENTER
                }
              },
            )
          }
          printText("---------------------------")
          nextLine(2)
        }
      }

      override fun onServiceDisconnected(name: ComponentName) {}
    }

    context.bindService(intent, connection, Service.BIND_AUTO_CREATE)
  }

  fun PrinterInterface.withBold(block: PrinterInterface.() -> Any) {
    setTextBold(true)
    block.invoke(this)
    setTextBold(false)
  }

  fun aidl(service: IBinder, block: PrinterInterface.() -> Any) {
    block.invoke(PrinterInterface.Stub.asInterface(service))
  }
}