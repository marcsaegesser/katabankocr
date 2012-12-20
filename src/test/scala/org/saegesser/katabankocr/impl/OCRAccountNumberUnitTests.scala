package org.saegesser.katabankocr.impl

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.WordSpec
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
  }
}