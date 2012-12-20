package org.saegesser.katabankocr.impl

class OCRAccountNumber (input: String) {
  val digits = OCRAccountNumber.parse(input)
  lazy val isInvalid = digits exists {_.isInvalid}
  lazy val isError = if (!isInvalid) OCRAccountNumber.checksum(digits) != 0 else true
  override def toString =  {
    def errorMessage = if(isInvalid) " ILL" else if(isError) " ERR" else ""
    (digits map {_.char} mkString) + errorMessage    
  }
}

object OCRAccountNumber {
  def parse(input: String): List[OCRDigit] = {
    (for {i <- 0 until 9
         val s = input.substring(i*3, (i*3)+3) + input.substring((i*3) + 27, (i*3) + 27 + 3) + input.substring((i*3) + 54, (i*3) + 54 + 3) 
      }yield OCRDigit(s)).toList
  }
  
  def checksum(digits: List[OCRDigit]): Int = {
    def helper(ds: List[OCRDigit], mult: Int, accum: Int): Int =
    	if(ds.isEmpty) accum
    	else helper(ds.tail, mult-1, ds.head.value.get*mult + accum)
    	
    helper(digits, 9, 0) % 11
  }
}