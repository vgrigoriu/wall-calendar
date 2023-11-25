import java.time.{DayOfWeek, LocalDate, Month}
import java.time.Month._

@main def hello(): Unit =
  val year = 2024
  val header =
    s"""<!DOCTYPE html>
       |<html>
       |<head>
       |  <meta charset="utf-8" />
       |  <style type="text/css">
       |  /* This font size makes it so a month fits in a landscape A4 page when printed from the
       |     current version of desktop Safari. */
       |  body { font-family: sans-serif; font-size: 90%; }
       |  table { border-collapse: collapse; }
       |  table td { border-top: 1px solid red; border-bottom: 1px solid red; }
       |  .day { text-align: end; width: 1.4em; padding: 0.1em; }
       |  .day-of-week { width: 1em; padding-left: 0.5em; color: #cccccc; }
       |  .day-name { color: #cc9999; font-size: 80%; }
       |  .adobe-day-name { color: #cc99cc; font-size: 80%; }
       |  .weekend { background: #fff0f0; }
       |  .months { display: flex; justify-content: space-around; gap: 2em; align-items: flex-start; }
       |  .month { flex: 1; }
       |  </style>
       |</head>
       |<body>
       |""".stripMargin

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

private val holidays = Map(
  LocalDate.parse("2023-01-01") -> "Anul nou",
  LocalDate.parse("2023-01-02") -> "Anul nou",
  LocalDate.parse("2023-01-24") -> "Unirea Principatelor Române",
  LocalDate.parse("2023-04-14") -> "Vinerea Mare",
  LocalDate.parse("2023-04-16") -> "Paștele ortodox",
  LocalDate.parse("2023-04-17") -> "A doua zi de Paște",
  LocalDate.parse("2023-05-01") -> "Ziua Muncii",
  LocalDate.parse("2023-06-01") -> "Ziua Copilului",
  LocalDate.parse("2023-06-04") -> "Rusalii",
  LocalDate.parse("2023-06-05") -> "A doua zi de Rusalii",
  LocalDate.parse("2023-08-15") -> "Adormirea Maicii Domnului",
  LocalDate.parse("2023-11-30") -> "Sfântul Andrei",
  LocalDate.parse("2023-12-01") -> "Marea Unire",
  LocalDate.parse("2023-12-25") -> "Crăciun",
  LocalDate.parse("2023-12-26") -> "A doua zi de Crăciun",
  LocalDate.of(2024, JANUARY, 1) -> "Anul nou",
  LocalDate.of(2024, JANUARY, 2) -> "Anul nou",
  LocalDate.of(2024, JANUARY, 6) -> "Boboteaza",
  LocalDate.of(2024, JANUARY, 7) -> "Sf. Ioan",
  LocalDate.of(2024, JANUARY, 24) -> "Unirea mică",
  LocalDate.of(2024, MAY, 1) -> "Ziua muncii",
  LocalDate.of(2024, MAY, 3) -> "Vinerea Mare",
  LocalDate.of(2024, MAY, 5) -> "Paște",
  LocalDate.of(2024, MAY, 6) -> "Paște",
  LocalDate.of(2024, JUNE, 1) -> "Ziua copilului",
  LocalDate.of(2024, JUNE, 23) -> "Rusalii",
  LocalDate.of(2024, JUNE, 24) -> "Rusalii",
  LocalDate.of(2024, AUGUST, 15) -> "Sf. Maria",
  LocalDate.of(2024, NOVEMBER, 30) -> "Sf. Andrei",
  LocalDate.of(2024, DECEMBER, 1) -> "Unirea mare",
  LocalDate.of(2024, DECEMBER, 25) -> "Crăciun",
  LocalDate.of(2024, DECEMBER, 26) -> "Crăciun",
)

private val adobeHolidays = Map(
  LocalDate.of(2024, JANUARY, 5) -> "Boboteaza (in  lieu)",
  LocalDate.of(2024, JANUARY, 8) -> "Sf. Ioan (in lieu)",
  LocalDate.of(2024, MAY, 31) -> "Ziua copilului (in lieu)",
  LocalDate.of(2024, NOVEMBER, 29) -> "Sf. Andrei (in lieu)",
  LocalDate.of(2024, DECEMBER, 2) -> "Unirea mare (in lieu)",
  LocalDate.of(2024, DECEMBER, 27) -> "PTO",
  LocalDate.of(2024, DECEMBER, 30) -> "PTO",
  LocalDate.of(2024, DECEMBER, 31) -> "PTO",
)

def getCalendarPage(year: Int, months: Month*): String =
    s"""<div class="months">
       |  ${months.map(printMonth(year, _)).mkString("\n")}
       |</div>""".stripMargin

def printMonth(year: Int, month: Month): String =
  var day = LocalDate.of(year, month, 1)
  val sb = StringBuilder(
    s"""<table class="month">
       |  <thead>
       |    <th scope="col" colspan="3">${monthName(month)}</th>
       |  </thead>
       |""".stripMargin)
  while day.getMonth == month do
    sb.append(
      s"""<tr ${dowClass(day)}>
         |  <td class="day">${day.getDayOfMonth}</td>
         |  <td class="day-of-week">${dow(day.getDayOfWeek)}</td>
         |  <td class="${holidayStyle(day)}">${holidayName(day)}</td>
         |</tr>
         |""".stripMargin)
    day = day.plusDays(1)

  sb.append("</table>")
  sb.toString

private def holidayName(day: LocalDate): String =
  holidays.getOrElse(day, adobeHolidays.getOrElse(day, ""))

private def holidayStyle(day: LocalDate): String =
  if holidays.contains(day) then
    "day-name"
  else if adobeHolidays.contains(day) then
    "adobe-day-name"
  else
    ""

private def dowClass(date: LocalDate): String =
  if Set(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.getDayOfWeek) then
    """ class="weekend""""
  else
    ""