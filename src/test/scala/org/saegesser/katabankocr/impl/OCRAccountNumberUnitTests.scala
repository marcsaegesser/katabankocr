package org.saegesser.katabankocr.impl

import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class OCRAccountNumberUnitTests extends WordSpec with ShouldMatchers {
  "an account number" should {
    "parse a valid account number string" in {
      val data =
        "    _  _     _  _  _  _  _ " +
        "  | _| _||_||_ |_   ||_||_|" +
        "  ||_  _|  | _||_|  ||_| _|"

      val acctnum = new OCRAccountNumber(data)
      acctnum.isInvalid should be (false)
      acctnum.toString should equal("123456789")
    }
    "print an illegal account number with ?s and an ILL flag" in {
      val data =
        "    _  _     _  _  _  _  _ " +
        " _||_| _||_||_ |_   ||_||_|" +
        "  ||_ |_|  | _||_|  ||_| _|"

      val acctnum = new OCRAccountNumber(data)
      acctnum.toString should equal("???456789 ILL")
    }
    "fix a single segment error (111111111) -> (711111111)" in {
      val data =
        "                           " +
        "  |  |  |  |  |  |  |  |  |" +
        "  |  |  |  |  |  |  |  |  |"

      val acctnum = new OCRAccountNumber(data)
      acctnum.toString should equal("711111111")
    }
    "fix a single segment error (777777777) -> (777777177)" in {
      val data =
        " _  _  _  _  _  _  _  _  _ " +
        "  |  |  |  |  |  |  |  |  |" +
        "  |  |  |  |  |  |  |  |  |"

      val acctnum = new OCRAccountNumber(data)
      acctnum.toString should equal("777777177")
    }
    "fix a single segment error (200000000) -> (200800000)" in {
      val data =
        " _  _  _  _  _  _  _  _  _ " +
        " _|| || || || || || || || |" +
        "|_ |_||_||_||_||_||_||_||_|"

      val acctnum = new OCRAccountNumber(data)
      acctnum.toString should equal("200800000")
    }
    "Report ambiguous single segment errors (888888888) -> (888886888, 888888880, 888888988)" in {
      val data =
        " _  _  _  _  _  _  _  _  _ " +
        "|_||_||_||_||_||_||_||_||_|" +
        "|_||_||_||_||_||_||_||_||_|"

      val acctnum = new OCRAccountNumber(data)
        val expectedAmbiguous = Set(string2Digits("888886888"), string2Digits("888888880"), string2Digits("888888988"))
      acctnum.digits should equal (string2Digits("888888888"))
      acctnum.ambiguities.toSet should equal (expectedAmbiguous)
    }
    "Report ambiguous single segment errors (490067715) -> (490067115, 490067719, 490867715)" in {
      val data =
        "    _  _  _  _  _  _     _ " +
        "|_||_|| || ||_   |  |  ||_ " +
        "  | _||_||_||_|  |  |  | _|"

      val acctnum = new OCRAccountNumber(data)
      val expectedAmbiguous = Set(string2Digits("490067115"), string2Digits("490067719"), string2Digits("490867715"))
      acctnum.digits should equal (string2Digits("490067715"))
      acctnum.ambiguities.toSet should equal (expectedAmbiguous)
    }
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
