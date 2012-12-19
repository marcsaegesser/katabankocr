package org.saegesser.katabankocr.impl

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class OCRDigitUnitTests extends WordSpec with ShouldMatchers {

  "an OCRDigit" should {
    "parse a zero" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a one" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a two" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a three" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a four" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a five" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a six" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a seven" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a eight" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "parse a nine" in {
      val d = OCRDigit(OCRDigit.zero)
      d.value should equal(Some(0))
      d.isInvalid should be (false)
    }
    "flag in invalid character" in {
      val d = OCRDigit("_________")
      d.value should equal(None)
      d.isInvalid should be (true)
    }
  }
}