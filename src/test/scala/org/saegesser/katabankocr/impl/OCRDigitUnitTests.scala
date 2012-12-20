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
    "find valid mutations of normal digits" in {
      OCRDigit0.validMutations should equal (Set(OCRDigit8))
      OCRDigit1.validMutations should equal (Set(OCRDigit7))
      OCRDigit2.validMutations should equal (Set())
      OCRDigit3.validMutations should equal (Set(OCRDigit9))
      OCRDigit4.validMutations should equal (Set())
      OCRDigit5.validMutations should equal (Set(OCRDigit6, OCRDigit9))
      OCRDigit7.validMutations should equal (Set(OCRDigit1))
      OCRDigit8.validMutations should equal (Set(OCRDigit0, OCRDigit6, OCRDigit9))
      OCRDigit9.validMutations should equal (Set(OCRDigit3, OCRDigit5, OCRDigit8))
    }
    "find valid mutations of invalid digits" in {
      val segs2 =
        " _ " +
        "|_|" +
        "  |"
      OCRDigit(segs2).validMutations should equal(Set(OCRDigit4, OCRDigit9))
      val segs3 =
        " _ " +
        "|  " +
        "  |"
      OCRDigit(segs3).validMutations should equal(Set())
    }
  }
}