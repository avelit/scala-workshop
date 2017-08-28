package com.workshop

object PersonTitle {
  // match on tuple of (gender, status)
  def genderTitle(gender: Gender, status: Option[Status]): String = (gender, status) match {
    case (Male, _) => "Mr"
    case (Female, Some(Single)) => "Miss"
    case (Female, Some(Married)) => "Mrs"
    case (Female, None) => "Ms"
  }
}


sealed trait Gender

case object Male extends Gender

case object Female extends Gender

sealed trait Status

case object Married extends Status

case object Single extends Status

