package org.saegesser.katabankocr.impl

trait OCRDigit {
  def data: IndexedSeq[Char]
  def value: Option[Int]
  def isInvalid: Boolean
  def char: Char
  def validMutations = OCRDigit.mutations(data)
  override def toString = "" + char
}

case object OCRDigit0 extends OCRDigit {
  def data = OCRDigit.zero.toIndexedSeq
  def value = Some(0)
  def isInvalid = false
  def char = '0'
}

case object OCRDigit1 extends OCRDigit {
  def data = OCRDigit.one.toIndexedSeq
  def value = Some(1)
  def isInvalid = false
  def char = '1'
}

case object OCRDigit2 extends OCRDigit {
  def data = OCRDigit.two.toIndexedSeq
  def value = Some(2)
  def isInvalid = false
  def char = '2'
}

case object OCRDigit3 extends OCRDigit {
  def data = OCRDigit.three.toIndexedSeq
  def value = Some(3)
  def isInvalid = false
  def char = '3'
}

case object OCRDigit4 extends OCRDigit {
  def data = OCRDigit.four.toIndexedSeq
  def value = Some(4)
  def isInvalid = false
  def char = '4'
}

case object OCRDigit5 extends OCRDigit {
  def data = OCRDigit.five.toIndexedSeq
  def value = Some(5)
  def isInvalid = false
  def char = '5'
}

case object OCRDigit6 extends OCRDigit {
  def data = OCRDigit.six.toIndexedSeq
  def value = Some(6)
  def isInvalid = false
  def char = '6'
}

case object OCRDigit7 extends OCRDigit {
  def data = OCRDigit.seven.toIndexedSeq
  def value = Some(7)
  def isInvalid = false
  def char = '7'
}

case object OCRDigit8 extends OCRDigit {
  def data = OCRDigit.eight.toIndexedSeq
  def value = Some(8)
  def isInvalid = false
  def char = '8'
}

case object OCRDigit9 extends OCRDigit {
  def data = OCRDigit.nine.toIndexedSeq
  def value = Some(9)
  def isInvalid = false
  def char = '9'
}

case class OCRDigitBad(inputData: String) extends OCRDigit {
  def data = inputData.toIndexedSeq
  def value = None
  def isInvalid = true
  def char = '?'
}

object OCRDigit {
  def apply(inputData: String) = parse(inputData)

  def parse(in: String): OCRDigit = {
    in match {
      case OCRDigit.zero  => OCRDigit0
      case OCRDigit.one   => OCRDigit1
      case OCRDigit.two   => OCRDigit2
      case OCRDigit.three => OCRDigit3
      case OCRDigit.four  => OCRDigit4
      case OCRDigit.five  => OCRDigit5
      case OCRDigit.six   => OCRDigit6
      case OCRDigit.seven => OCRDigit7
      case OCRDigit.eight => OCRDigit8
      case OCRDigit.nine  => OCRDigit9
      case _              => OCRDigitBad(in)
    }
  }

  /** Returns a set OCRDigits that can be generated from the input digit
    * by one of two modifications 1) a space where there should be a
    * segment or 2) a segment (of either type) that should be a space.
    */
  def mutations(segments: IndexedSeq[Char]): Set[OCRDigit] = {
    def helper(index: Int, accum: Set[OCRDigit]): Set[OCRDigit] =
      if(index == segments.length) accum
      else
        helper(index+1,
          segments(index) match {
            case ' ' => accum + OCRDigit(segments.updated(index, '|') mkString) + OCRDigit(segments.updated(index, '_') mkString)
            case _   => accum + OCRDigit(segments.updated(index, ' ') mkString)
          })

      helper(0, Set()) filter {!_.isInvalid}
  }                                         //> mutations: (segments: IndexedSeq[Char])List[IndexedSeq[Char]]

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
