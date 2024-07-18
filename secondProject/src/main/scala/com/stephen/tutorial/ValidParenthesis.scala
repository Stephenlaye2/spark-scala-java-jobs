package com.stephen.tutorial

import scala.collection.immutable.Stack

object ValidParenthesis extends App{
  /*
  Write a function that takes a string of parentheses, and determines if the order of the parentheses is valid.
  The function should return true if the string is valid, and false if it's invalid.
   */

  def isValidParenthesis(par: String): Boolean = {
    var lst: List[Char] = List()
    try {
      if(0 <= par.length  && par.length <= 100) {
        for (char <- par) {
          char match {
          case '(' => lst = '(' :: lst
          case ')' => if (lst.isEmpty) return false
            lst = lst.tail
          case _ => // Nothing
         }
       }
      }else{
        throw new StringIndexOutOfBoundsException
      }
    } catch {
      case e: StringIndexOutOfBoundsException => println(e)
    }
    lst.isEmpty
  }

  val isValid1 = isValidParenthesis("()")
  val isValid2 = isValidParenthesis(")(()))")
  val isValid3 = isValidParenthesis("(")
  val isValid4 = isValidParenthesis("(())((()())())")
  val lst = List(isValid1, isValid2, isValid3, isValid4)
  for (f <- lst){
    println(f)
  }
}
