This source code is an example of calling the print service. The print service is essentially a Service component. It is connected to it through the bindService API. After the connection is established, Epson commands can be sent for printing.

This project is a demo that show how to call printer service,the printer service is a Android Service Component.
According to the bindService API,your application can connect with the printer service.Once you have connected
With the printer service, you can send the Epson command to print.


Instructions for use:
1. Add the AIDL file PrinterInterface.aidl to the package recieptservice.com.recieptservice.
2. Use the following code to bind the printing service
Intent intent=new Intent();
intent.setClassName("recieptservice.com.recieptservice","recieptservice.com.recieptservice.service.PrinterService");
bindService(intent, new ServiceConnection(),Service.BIND_AUTO_CREATE);
3. After the binding is successful, you can start calling the relevant API interface for printing.



AIDL documentation explains:

Epson command printing interface:
void printEpson(in byte []data);

Get the print service version number:
String getServiceVersion();

Print String text:
void printText(String text);

Print BitMap pictures:
void printBitmap(in Bitmap pic);

Print barcode:
data: barcode content
symbology: barcode type
     0 -- UPC-A
     1 -- UPC-E
     2 -- JAN13(EAN13)
     3 -- JAN8(EAN8)
     4 -- CODE39
     5 --ITF
     6 -- CODABAR
     7 -- CODE93
     8 -- CODE128
height: barcode height 1-255 default 162
width: barcode width 2-6, default 2
void printBarCode(String data, int symbology, int height, int width);

Print QR code:
data: barcode content
modulesize: QR code block size 1-16
errorlevel: QR code error correction level 0-3
void printQRCode(String data, int modulesize, int errorlevel);

Alignment direction:
alignment 0 left 1 center 2 right
void setAlignment(int alignment);

Set font size:
void setTextSize(float textSize);

Change N lines:
void nextLine(int line);

Print form:
Text table content
weight row width weight
alignment Alignment direction of each row
void printTableText(in String[] text,in int []weight,in int []alignment);

Set the font to be bold:
void setTextBold(boolean bold);