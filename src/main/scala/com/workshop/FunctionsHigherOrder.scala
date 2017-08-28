package com.workshop

object FunctionsHigherOrder {
  // Higher-Order functions - function that return function
  // Functions are 1st class citizen - a val can be also a function

  // This is just a syntactic sugar, so we don't need to type the method definition each time
  type BinaryOperation = (Double, Double) => Double

  val binaryOperationFactory: (Double, Double, BinaryOperation) => Double =
    (a: Double, b: Double, operation: BinaryOperation) => operation(a, b)
  val plus: BinaryOperation = _ + _
  val minus: BinaryOperation = _ - _
  val mult: BinaryOperation = _ * _
  val sum: (Double, Double) => Double = binaryOperationFactory(_, _, plus)

  def minus(a: Double, b: Double): Double = binaryOperationFactory(a, b, minus)

  // create the multiply function using `binaryOperation`
  //val multiply: (Double, Double) => Double = (a,b) => binaryOperationFactory(a, b, mult)
  val multiply: (Double, Double) => Double = binaryOperationFactory(_, _, _ * _)

  // create the plusOne function (use sum?)
  //val plusOne: Double => Double = a => sum(a, 1)
  val plusOne: Double => Double = sum(_, 1)


  val numbers = Seq(1, 2, 3, 4)

  // Could we do it better?
  // Create a sumSeqFactory that receives the operation and returns a function that will do this
  // operation on all elements of the Seq before summing
  // Change the implementation of sumSeq, sumDoubles, sumSquares to use `sumSeqFactory`
  val sumSeqFactory1: (Int => Int, Seq[Int]) => Int = (operation, seq) => {
    seq.map(operation).sum
  }
  val sumSeqFactory: (Int => Int) => Seq[Int] => Int = operation => { seq =>
    seq.map(operation).sum
  }
  def sumSeqFactory2(operation: Int => Int, seq: Seq[Int]): (Seq[Int]) => Int = {
    _.map(operation).sum
  }

  //val sumSeq: (Seq[Int]) => Int = _.map(identity).sum
  val sumSeq: (Seq[Int]) => Int = sumSeqFactory(identity)

  //val sumDoubles: (Seq[Int]) => Int = _.map(_ * 2).sum
  val sumDoubles: (Seq[Int]) => Int = sumSeqFactory(_ * 2)

  //val sumSquares: (Seq[Int]) => Int = _.map(number => Math.pow(number, 2).toInt).sum
  val sumSquares: (Seq[Int]) => Int = sumSeqFactory(number => Math.pow(number, 2).toInt)
}
