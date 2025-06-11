import java.time.{LocalDate, Month}
import java.io.{BufferedWriter, FileWriter, File}
import scala.util.Using

@main def hello(args: String*): Unit =
  val year = if args.isEmpty then LocalDate.now.getYear + 1 else args(0).toInt
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
       |    .day { text-align: end; width: 1.4em; padding: 0.125em; }
       |    .day-of-week { width: 1em; padding-left: 0.5em; color: #cccccc; }
       |    .day-name { color: #cc9999; font-size: 80%; }
       |    .adobe-day-name { color: #cc99cc; font-size: 80%; }
       |    .holiday { background: #fff0f0; }
       |    .school-holiday { background: #ddffdd; }
       |    .adobe-holiday { background: #f0f0ff; }
       |    .months { display: flex; justify-content: space-around; gap: 2em; align-items: flex-start; }
       |    .month { flex: 1; }
       |  </style>
       |</head>
       |<body>""".stripMargin

  val footer =
    s"""</body>
       |</html>
       |""".stripMargin

  Using(BufferedWriter(FileWriter(File(f"$year.html")))) { bw =>
    bw.write(header); bw.newLine()
    bw.write(getCalendarPage(year, Month.JANUARY, Month.FEBRUARY, Month.MARCH)); bw.newLine()
    bw.write(getCalendarPage(year, Month.APRIL, Month.MAY, Month.JUNE)); bw.newLine()
    bw.write(getCalendarPage(year, Month.JULY, Month.AUGUST, Month.SEPTEMBER)); bw.newLine()
    bw.write(getCalendarPage(year, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER)); bw.newLine()
    bw.write(footer); bw.newLine()
  }

def getCalendarPage(year: Int, months: Month*): String =
  s"""  <div class="months">
       |${months.map(printMonth(year, _)).mkString("\n")}
       |  </div>""".stripMargin

def printMonth(year: Int, month: Month): String =
  val monthTable = StringBuilder(s"""    <table class="month">
       |      <thead>
       |        <tr>
       |          <th scope="col" colspan="3">${month.displayName}</th>
       |        </tr>
       |      </thead>
       |""".stripMargin)

  for (day <- month.daysIn(year))
    monthTable.append(
      s"""      <tr${dowClass(day)}>
         |        <td class="day">${day.getDayOfMonth}</td>
         |        <td class="day-of-week">${day.dowInitial}</td>
         |        <td${holidayClass(day)}>${day.holidayName}</td>
         |      </tr>
         |""".stripMargin)

  monthTable.append("    </table>")
  monthTable.toString

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
