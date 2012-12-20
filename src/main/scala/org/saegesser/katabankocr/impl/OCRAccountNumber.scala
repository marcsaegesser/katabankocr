package org.saegesser.katabankocr.impl

import scala.collection._

class OCRAccountNumber (input: String) {
  val (digits, ambiguities) = OCRAccountNumber.parse(input)
  lazy val isInvalid = OCRAccountNumber.isInvalid(digits)
  lazy val isError = if (!isInvalid) OCRAccountNumber.checksum(digits) != 0 else true
  override def toString =  {
    def errorMessage = 
      if(!ambiguities.isEmpty) " AMB [ " + (ambiguities map {_ mkString} mkString ", ") + " ]"
      else if(isInvalid) " ILL" 
      else if(isError) " ERR" 
      else ""
        
    (digits map {_.char} mkString) + errorMessage    
  }
}

object OCRAccountNumber {
  def parse(input: String): (List[OCRDigit], List[List[OCRDigit]]) = {
    val digits = for {i <- 0 until 9
         val s = input.substring(i*3, (i*3)+3) + input.substring((i*3) + 27, (i*3) + 27 + 3) + input.substring((i*3) + 54, (i*3) + 54 + 3) 
      }yield OCRDigit(s)
      
    if(!isInvalid(digits) && checksum(digits) == 0) (digits.toList, List())
    else
      fixSingleSegmentErrors(digits) match {
        case h :: Nil => (h, List())
        case h :: t   => (digits.toList, h :: t)
        case Nil      => (digits.toList, List())
      }
  }
  
  def isInvalid(digits: Seq[OCRDigit]): Boolean = digits exists {_.isInvalid}
  
  def checksum(digits: Seq[OCRDigit]): Int = {
    def helper(ds: List[OCRDigit], mult: Int, accum: Int): Int =
    	if(ds.isEmpty) accum
    	else helper(ds.tail, mult-1, ds.head.value.get*mult + accum)
    	
    helper(digits.toList, 9, 0) % 11
  }
  
  def fixSingleSegmentErrors(digits: IndexedSeq[OCRDigit]): List[List[OCRDigit]] = {
    def helper(index: Int, accum: List[List[OCRDigit]]): List[List[OCRDigit]] =
      if(index == digits.length) accum
      else {
        val validNums =
          for { m <- digits(index).validMutations
            val newNumber = digits.updated(index, m).toList
            if(!isInvalid(newNumber) &&  OCRAccountNumber.checksum(newNumber) == 0)
          }yield newNumber
        helper(index+1, validNums.toList ::: accum)
      }
    
    helper(0, List())
  }

  def string2Digits(s: String): List[OCRDigit] = {
    s.toList map {
      _ match {
        case '0' => OCRDigit0
        case '1' => OCRDigit1
        case '2' => OCRDigit2
        case '3' => OCRDigit3
        case '4' => OCRDigit4
        case '5' => OCRDigit5
        case '6' => OCRDigit6
        case '7' => OCRDigit7
        case '8' => OCRDigit8
        case '9' => OCRDigit9
        case _   => throw new Error("Invalid digit value")
      }
    }
  }
}