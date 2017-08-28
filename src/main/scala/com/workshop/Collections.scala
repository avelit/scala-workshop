package com.workshop

import scala.math.Ordering

object Collections {
  // map (1, 2, 3) -> (2, 4, 6)
  def doubleEachElement(seq: Seq[Int]): Seq[Int] = {
    seq.map(_ * 2)
  }

  // filter
  def onlySmaller(seq: Seq[Int], smallerThan: Int): Seq[Int] = {
    seq.filter(_ < smallerThan)
  }

  // filter
  def keepOddNumberOnly(seq: Seq[Int]): Seq[Int] = seq.filter(_ % 2 != 0)

  // zipWithIndex, filter, map. filter&map = collect
  def oddPositionsZeroBased(seq: Seq[Int]): Seq[Int] =
    seq.zipWithIndex.collect{
      case (value, index) if index % 2 != 0 => value
    }

  // maxBy
  def longest(seq: Seq[String]): String = seq.maxBy(_.length)

  // forAll, Char.isUpper
  def isAllUpper(str: String): Boolean = str.forall(_.isUpper)

  // recursion - make a call that will remove an item and put on the other side of the Seq
  def reverse[T](seq: Seq[T]): Seq[T] = seq match {
    case head :: tail => reverse(tail) :+ head
    case Nil => Nil
  }
}
