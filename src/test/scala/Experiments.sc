package org.saegesser.katabankocr.impl

object Experiments {
  val data =
    "    _  _  _  _  _  _  _  _ " +
    "| ||_|| || || || || || || |" +
    "|_||_||_||_||_||_||_||_||_|"                 //> data  : java.lang.String = "    _  _  _  _  _  _  _  _ | ||_|| || || || || |
                                                  //| | || ||_||_||_||_||_||_||_||_||_|"
   val data2 =
     "    _  _     _  _  _  _  _ " +
     "  | _| _||_||_ |_   ||_||_|" +
     "  ||_  _|  | _||_|  ||_| _|"                //> data2  : java.lang.String = "    _  _     _  _  _  _  _   | _| _||_||_ |_   
                                                  //| ||_||_|  ||_  _|  | _||_|  ||_| _|"
  
  val data3 =
    "    _  _  _  _  _  _  _  _ " +
    "|_||_   ||_ | ||_|| || || |" +
    "  | _|  | _||_||_||_||_||_|"                 //> data3  : java.lang.String = "    _  _  _  _  _  _  _  _ |_||_   ||_ | ||_|| 
                                                  //| || || |  | _|  | _||_||_||_||_||_|"

  val data4 =
    " _  _     _  _        _  _ " +
    "|_ |_ |_| _|  |  ||_||_||_ " +
    "|_||_|  | _|  |  |  | _| _|"                 //> data4  : java.lang.String = " _  _     _  _        _  _ |_ |_ |_| _|  |  ||_
                                                  //| ||_||_ |_||_|  | _|  |  |  | _| _|"
  /*
  457508000
664371495 ERR
86110??36 ILL*/
  
  new OCRAccountNumber(data)                      //> res0: org.saegesser.katabankocr.impl.OCRAccountNumber = ?80000000 ILL
  new OCRAccountNumber(data2)                     //> res1: org.saegesser.katabankocr.impl.OCRAccountNumber = 123456789
  new OCRAccountNumber(data3)                     //> res2: org.saegesser.katabankocr.impl.OCRAccountNumber = 457508000
  new OCRAccountNumber(data4)                     //> res3: org.saegesser.katabankocr.impl.OCRAccountNumber = 664371495 ERR
 
}