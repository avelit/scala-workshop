package com.workshop

import java.util.Locale

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import scala.io.Source

// define a case class that represents a log line

object LogAnalyzer extends App {
  //Read from the log file
  val log = Source.fromInputStream(getClass.getResourceAsStream("/access_log")).getLines()

  case class IP(p1: String, p2: String, p3: String, p4: String) {
  }
  case class Line(ip: IP, date: DateTime, typ: String, status: String) {
  }

  def logLine(splited: Array[String]): Line = {
    val date: DateTime = DateTime.parse(splited(3).substring(1) + splited(4).dropRight(1), DateTimeFormat.forPattern("dd/MMMM/yyyy:HH:mm:ssZ").withLocale(Locale.US))
    val ip: Array[String] = splited(0).split("\\.")
    Line(IP(ip(0), ip(1), ip(2), ip(3)), date, splited(5).substring(1), splited(8))
  }

  // map each line to a case class that represents it
  // Split? Regex?
  // How to parse the datetime? DateTime.parse(dateTimeString, DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ssZ"))
  val logLines = log.map { str =>
    val splited = str.split(" ")
    logLine(splited)
  }.toArray

  println(logLines.length, "web requests")

  val status = logLines.groupBy(x => x.status).mapValues(_.length)
  println(status, "each status code")

  val ips = logLines.map(x => x.ip).distinct.length
  println(ips, "distinct IPs")

  // how many web requests?
  // how many of each status code?
  // how many distinct IPs? Wait, did you use String for IP? Maybe use a case class that will validate?
  //   Maybe some lines are bad? We want to throw them away. (When parsing return Try/Option, use flatMap instead of map on the log lines)
  // Largest response size? What was the request for it? Average response size? Sum of all responses?
  // which url have most hits? How many hits?

  //logLines.foreach(v=>println(v))
}