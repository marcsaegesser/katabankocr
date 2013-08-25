package org.saegesser.katabankocr.impl

import scala.language.postfixOps

/**
 * Represents an account number.  Account numbers are composed of a
 * sequence of 9 digits.  These digits are parsed from an OCR format
 * made up of 3x3 cells of pipes and underbars.  OCR representations
 * of digits may contain errors.  A checksum mechanism provides the
 * ability to recover from some invalid input but it is possible for
 * ODR derived account numbers to contain ambiguities or unrecoverable
 * errors
 */
class OCRAccountNumber(input: String) {
  import OCRAccountNumber._
  // Parse input into list of digits and potentially a list of ambiguous results
  val (digits, ambiguities) = parse(input)
  lazy val isInvalid = OCRAccountNumber.isInvalid(digits)
  lazy val isError = if (!isInvalid) checksum(digits) != 0 else true
  override def toString = {
    def errorMessage =
      if (!ambiguities.isEmpty) " AMB [ " + (ambiguities map { _.mkString } mkString ", ") + " ]"
      else if (isInvalid) " ILL"
      else if (isError) " ERR"
      else ""

    (digits map { _.char } mkString) + errorMessage
  }
}

object OCRAccountNumber {
  def parse(input: String): (List[OCRDigit], List[List[OCRDigit]]) = {
    val digits = for {
      i <- 0 until 9
      digitOffsetRow0 = i * 3
      digitOffsetRow1 = digitOffsetRow0 + 27
      digitOffsetRow2 = digitOffsetRow1 + 27
      s = input.substring(digitOffsetRow0, digitOffsetRow0 + 3) + input.substring(digitOffsetRow1, digitOffsetRow1 + 3) + input.substring(digitOffsetRow2, digitOffsetRow2 + 3)
    } yield OCRDigit(s)

    if (!isInvalid(digits) && checksum(digits) == 0) (digits.toList, List())
    else
      fixSingleSegmentErrors(digits) match {
        case h :: Nil => (h, List())
        case h :: t => (digits.toList, h :: t)
        case Nil => (digits.toList, List())
      }
  }

  def isInvalid(digits: Seq[OCRDigit]): Boolean = digits exists { _.isInvalid }

  def checksum(digits: Seq[OCRDigit]): Int = {
    def helper(ds: List[OCRDigit], mult: Int, accum: Int): Int =
      if (ds.isEmpty) accum
      else helper(ds.tail, mult - 1, ds.head.value.get * mult + accum)

    helper(digits.toList, 9, 0) % 11
  }

  def fixSingleSegmentErrors(digits: IndexedSeq[OCRDigit]): List[List[OCRDigit]] = {
    def helper(index: Int, accum: List[List[OCRDigit]]): List[List[OCRDigit]] =
      if (index == digits.length) accum
      else {
        val validNums =
          for {
            m <- digits(index).validMutations
            newNumber = digits.updated(index, m).toList
            if (!isInvalid(newNumber) && OCRAccountNumber.checksum(newNumber) == 0)
          } yield newNumber
        helper(index + 1, validNums.toList ::: accum)
      }

    helper(0, List())
  }
}
