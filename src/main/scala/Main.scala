import java.time.{DayOfWeek, LocalDate, Month}

@main def hello(): Unit =
  val year = 2023
  val header =
    s"""<!DOCTYPE html>
       |<html>
       |<head>
       |  <meta charset="utf-8" />
       |  <style type="text/css">
       |  /* The font size makes it so a month fits in a landscape A4 page. */
       |  body { font-family: sans-serif; font-size: 90%; }
       |  table { border-collapse: collapse; }
       |  table td { border-top: 1px solid red; border-bottom: 1px solid red; }
       |  .day { text-align: end; width: 2em; padding: 0.1em; }
       |  .day-of-week { width: 1em; padding-left: 0.5em; }
       |  .day-name { color: #cc9999; font-size: 80%; }
       |  .weekend { background: #ffeeee; }
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
  Month.JANUARY -> "Ianuarie",
  Month.FEBRUARY -> "Februarie",
  Month.MARCH -> "Martie",
  Month.APRIL -> "Aprilie",
  Month.MAY -> "Mai",
  Month.JUNE -> "Iunie",
  Month.JULY -> "Iulie",
  Month.AUGUST -> "August",
  Month.SEPTEMBER -> "Septembrie",
  Month.OCTOBER -> "Octombrie",
  Month.NOVEMBER -> "Noiembrie",
  Month.DECEMBER -> "Decembrie",
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
  LocalDate.parse("2023-12-01") -> "Marea Uniri",
  LocalDate.parse("2023-12-25") -> "Crăciun",
  LocalDate.parse("2023-12-26") -> "A doua zi de Crăciun",
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
         |  <td class="day-name">${holidayName(day)}</td>
         |</tr>
         |""".stripMargin)
    day = day.plusDays(1)

  sb.append("</table>")
  sb.toString

private def holidayName(date: LocalDate): String =
  holidays.getOrElse(date, "")

private def dowClass(date: LocalDate): String =
  if Set(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.getDayOfWeek) then
    """ class="weekend""""
  else
    ""