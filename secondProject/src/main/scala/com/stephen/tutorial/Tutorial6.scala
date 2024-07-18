package com.stephen.tutorial

import akka.actor.{Actor, ActorSystem, Props}

case class WhoToGreet(who: String)
class Tutorial6 extends Actor{
  def receive = {
    case WhoToGreet(wh) => println("Hello " + wh)
  }

}

object HelloAkka extends App{
  val greeting = ActorSystem("Hello-Akka")
  val greet = greeting.actorOf(Props[Tutorial6], "greeter")
  greet ! WhoToGreet("Akka")
}