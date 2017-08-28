package com.workshop

// match on a tuple (divisibleBy3, divisibleBy5)
object FizzBuz {
  def eval(n: Int): String = {
    val divisibleBy3 = n % 3 == 0
    val divisibleBy5 = n % 5 == 0
    (divisibleBy3, divisibleBy5) match {
      case (true, true) => "FizzBuzz"
      case (true, false) => "Fizz"
      case (false, true) => "Buzz"
      case (_, _) => n.toString
    }
  }
}
