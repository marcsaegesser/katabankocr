package org.saegesser.katabankocr.impl

case class OCRDigit(inputData: String) {
  def value = OCRDigit.parse(inputData)
  def isInvalid = !value.isEmpty
  def char = value getOrElse ('?')
}

object OCRDigit {
  def parse(in: String): Option[Int] = {
    in match {
      case OCRDigit.zero => Some(0)
      case OCRDigit.one => Some(1)
      case OCRDigit.two => Some(2)
      case OCRDigit.three => Some(3)
      case OCRDigit.four => Some(4)
      case OCRDigit.five => Some(5)
      case OCRDigit.six => Some(6)
      case OCRDigit.seven => Some(7)
      case OCRDigit.eight => Some(8)
      case OCRDigit.nine => Some(9)
      case _ => None 
    }
  }
  
  val zero = " _ " +
             "| |" +
             "|_|"
  val one = "   " +
  		    "  |" +
  		    "  |"
  val two = " _ " +
            " _|" +
            "|_ "
  val three = " _ " +
              " _|" +
              " _|"
  val four = "   " +
             "|_|" +
             "  |"
  val five = " _ " +
             "|_ " +
             " _|"
  val six = " _ " +
            "|_ " +
            "|_|"
  val seven = " _ " +
              "  |" +
              "  |"
  val eight = " _ " +
              "|_|" +
              "|_|"
  val nine = " _ " +
             "|_|" +
             " _|"
}