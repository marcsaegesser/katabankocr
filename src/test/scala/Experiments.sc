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
  val data5 =
    "                           " +
    "  |  |  |  |  |  |  |  |  |" +
    "  |  |  |  |  |  |  |  |  |"                 //> data5  : java.lang.String = "                             |  |  |  |  |  |  
                                                  //| |  |  |  |  |  |  |  |  |  |  |  |"
  /*
  457508000
664371495 ERR
86110??36 ILL*/
  
//  new OCRAccountNumber(data)
  new OCRAccountNumber(data2)                     //> res0: org.saegesser.katabankocr.impl.OCRAccountNumber = 123456789
  new OCRAccountNumber(data3)                     //> res1: org.saegesser.katabankocr.impl.OCRAccountNumber = 457508000
  new OCRAccountNumber(data4)                     //> res2: org.saegesser.katabankocr.impl.OCRAccountNumber = 664371485
  val d5 = new OCRAccountNumber(data5)            //> d5  : org.saegesser.katabankocr.impl.OCRAccountNumber = 711111111
 
  val data6 =
    " _  _  _  _  _  _  _  _  _ " +
    "|_||_||_||_||_||_||_||_||_|" +
    "|_||_||_||_||_||_||_||_||_|"                 //> data6  : java.lang.String = " _  _  _  _  _  _  _  _  _ |_||_||_||_||_||_||_
                                                  //| ||_||_||_||_||_||_||_||_||_||_||_|"

  val acctnum = new OCRAccountNumber(data6)       //> acctnum  : org.saegesser.katabankocr.impl.OCRAccountNumber = 888888888 AMB 
                                                  //| [ 888888880, 888888988, 888886888 ]
  import OCRAccountNumber._
  acctnum.digits == OCRAccountNumber.string2Digits("888888888")
                                                  //> res3: Boolean = true
  
	OCRDigit9.validMutations                  //> res4: Set[org.saegesser.katabankocr.impl.OCRDigit] = Set(5, 8, 3)
      val segs =
        "   " +
        "  |" +
        "  |"                                     //> segs  : java.lang.String = "     |  |"
      OCRDigit(segs).validMutations               //> res5: Set[org.saegesser.katabankocr.impl.OCRDigit] = Set(7)
}