package org.saegesser.katabankocr.impl

object Experiments {
  val data =
    " _  _  _  _  _  _  _  _  _ " +
    "| ||_|| || || || || || || |" +
    "|_||_||_||_||_||_||_||_||_|"                 //> data  : java.lang.String = " _  _  _  _  _  _  _  _  _ | ||_|| || || || || |
                                                  //| | || ||_||_||_||_||_||_||_||_||_|"
  
  val acctNum = new OCRAccountNumber(data)        //> acctNum  : org.saegesser.katabankocr.impl.OCRAccountNumber = 080000000
  acctNum.isInvalid                               //> res0: Boolean = false
}