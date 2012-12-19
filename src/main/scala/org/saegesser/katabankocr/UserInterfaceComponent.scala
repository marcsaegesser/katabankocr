package org.saegesser.katabankocr

trait UserInterfaceComponent {
  val userInterface: UserInterface
  
  trait UserInterface {
    def run(args: Array[String])
  }
}