import Holidays.{adobeHolidays, holidays}

import java.time.{DayOfWeek, LocalDate, Month}
import java.time.Month.*

@main def hello(): Unit =
  val year = 2024
  val header =
    s"""<!DOCTYPE html>
       |<html lang="ro">
       |<head>
       |  <meta charset="utf-8">
       |  <title>Calendar $year</title>
       |  <style>
       |    /* This font size makes it so a month fits in a landscape A4 page when printed from the
       |       current version of desktop Safari. */
       |    body { font-family: sans-serif; font-size: 90%; }
       |    table { border-collapse: collapse; }
       |    table td { border-top: 1px solid red; border-bottom: 1px solid red; }
       |    .day { text-align: end; width: 1.4em; padding: 0.1em; }
       |    .day-of-week { width: 1em; padding-left: 0.5em; color: #cccccc; }
       |    .day-name { color: #cc9999; font-size: 80%; }
       |    .adobe-day-name { color: #cc99cc; font-size: 80%; }
       |    .holiday { background: #fff5f5; }
       |    .school-holiday { background: #f5fff5; }
       |    .adobe-holiday { background: #f5f5ff; }
       |    .months { display: flex; justify-content: space-around; gap: 2em; align-items: flex-start; }
       |    .month { flex: 1; }
       |  </style>
       |</head>
       |<body>""".stripMargin

  val footer =
    s"""</body>
       |</html>
       |""".stripMargin

  println(header)
  println(getCalendarPage(year, Month.JANUARY, Month.FEBRUARY, Month.MARCH))
  println(getCalendarPage(year, Month.APRIL, Month.MAY, Month.JUNE))
  println(getCalendarPage(year, Month.JULY, Month.AUGUST, Month.SEPTEMBER))
  println(getCalendarPage(year, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER))
  println(footer)

private val dow = Map(
  DayOfWeek.MONDAY -> "L",
  DayOfWeek.TUESDAY -> "M",
  DayOfWeek.WEDNESDAY -> "M",
  DayOfWeek.THURSDAY -> "J",
  DayOfWeek.FRIDAY -> "V",
  DayOfWeek.SATURDAY -> "S",
  DayOfWeek.SUNDAY -> "D",
)

private val monthName = Map(
  JANUARY -> "Ianuarie",
  FEBRUARY -> "Februarie",
  MARCH -> "Martie",
  APRIL -> "Aprilie",
  MAY -> "Mai",
  JUNE -> "Iunie",
  JULY -> "Iulie",
  AUGUST -> "August",
  SEPTEMBER -> "Septembrie",
  OCTOBER -> "Octombrie",
  NOVEMBER -> "Noiembrie",
  DECEMBER -> "Decembrie",
)

def getCalendarPage(year: Int, months: Month*): String =
  s"""  <div class="months">
       |${months.map(printMonth(year, _)).mkString("\n")}
       |  </div>""".stripMargin

def printMonth(year: Int, month: Month): String =
  var day = LocalDate.of(year, month, 1)
  val sb = StringBuilder(s"""    <table class="month">
       |      <thead>
       |        <tr>
       |          <th scope="col" colspan="3">${monthName(month)}</th>
       |        </tr>
       |      </thead>
       |""".stripMargin)
  while day.getMonth == month do
    sb.append(s"""      <tr${dowClass(day)}>
         |        <td class="day">${day.getDayOfMonth}</td>
         |        <td class="day-of-week">${dow(day.getDayOfWeek)}</td>
         |        <td${holidayClass(day)}>${holidayName(day)}</td>
         |      </tr>
         |""".stripMargin)
    day = day.plusDays(1)

  sb.append("    </table>")
  sb.toString

private def holidayName(day: LocalDate): String =
  holidays.getOrElse(day, adobeHolidays.getOrElse(day, ""))

private def holidayClass(day: LocalDate): String =
  if day.isHoliday then """ class="day-name""""
  else if day.isAdobeHoliday then """ class="adobe-day-name""""
  else ""

private def dowClass(date: LocalDate): String =
  if date.isWeekend || date.isHoliday
  then """ class="holiday""""
  else if date.isSchoolHoliday then """ class="school-holiday""""
  else if date.isAdobeHoliday then """ class="adobe-holiday""""
  else ""
