package com.workshop

// Let's do a recursive solution
// Use match on the `n`
// You can use `|` to match on multiple constants
// you can use guard `case x if ... =>`
class Fibonacci {
  def nth(n: Int): Int = n match {
    case fib if fib < 0 => throw new IllegalArgumentException()
    case 0 | 1 => n
    case fib => nth(fib - 1) + nth(fib - 2)
  }
}
