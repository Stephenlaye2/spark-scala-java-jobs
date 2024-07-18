package com.stephen.tutorial

import scala.language.implicitConversions

case class Rectangle (height: Int, width: Int)
case class Meter(value: Double)
case class Centimeter(value: Double)

object executer extends App {
  implicit def calculateArea(area: Rectangle): Int = area.height * area.width

  val findArea: Int = Rectangle(4, 5)
  println(findArea)

  implicit def convertMeterToCentimeter(meter: Meter): Centimeter = Centimeter(meter.value * 100)

  val centimeter: Centimeter = Meter(1.5)
  println("centimeter: " + centimeter)
}
