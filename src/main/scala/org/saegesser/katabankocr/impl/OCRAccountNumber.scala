package org.saegesser.katabankocr.impl

class OCRAccountNumber (input: String) {
  val digits = OCRAccountNumber.parse(input)
  lazy val isInvalid = digits exists {!_.isInvalid}  
  override def toString = digits map {_.char} mkString
}

object OCRAccountNumber {
  def parse(input: String): List[OCRDigit] = {
    (for {i <- 0 until 9
         val s = input.substring(i*3, (i*3)+3) + input.substring((i*3) + 27, (i*3) + 27 + 3) + input.substring((i*3) + 54, (i*3) + 54 + 3) 
      }yield OCRDigit(s)).toList
  }
}