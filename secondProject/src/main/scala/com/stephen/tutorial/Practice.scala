package com.stephen.tutorial

object Practice extends App {
  val states1 = Map("TN" -> "TamilNadu", "TS" -> "Telangana", "KA" -> "Karnataka")
  val states2 = Map("KL" -> "Kerala", "KA" -> "Karnataka", "TS" -> "Telangana")
  var states = states1 ++ states2
  println("result : " + states)
}
