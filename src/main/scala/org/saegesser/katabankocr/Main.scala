package org.saegesser.katabankocr

import org.saegesser.katabankocr.impl.OCRAccountNumber
import scala.io.Source

object Main {
  def main(args: Array[String]) = {
    val s = Source.fromFile(args(0))
    val accountNumbers = s.getLines.grouped(4)
      .map { _.mkString.substring(0, 81) }
      .filter { _.length == 81 }
      .map { new OCRAccountNumber(_) }
    accountNumbers.foreach (println(_))
  }
}

/*
try {
      val s = Source.fromFile(args(0))
      val acctNumbers = s.getLines
        .grouped(4)
        .map { _.mkString.substring(0, 81) }
        .filter { _.length == 81 }
        .map { s => new OCRAccountNumber(s) }
      acctNumbers foreach {println(_)}
    } catch {
      case t: Throwable => println("Main:  " + t)
    } */
