import Holidays.{adobeHolidays, holidays, schoolHolidays}

import java.time.{DayOfWeek, LocalDate}

private val weekendDays = Set(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)

extension (day: LocalDate) {
  def isWeekend: Boolean = weekendDays.contains(
    day.getDayOfWeek,
  )

  def isHoliday: Boolean = {
    holidays.contains(day)
  }

  def isSchoolHoliday: Boolean = {
    schoolHolidays.exists { case (start, end) =>
      start.lte(day) && day.lte(end)
    }
  }

  def isAdobeHoliday: Boolean = {
    adobeHolidays.contains(day)
  }

  def holidayName: String =
    holidays.getOrElse(day, adobeHolidays.getOrElse(day, ""))

  def dowInitial: String = day.getDayOfWeek.initial

  private def lte(other: LocalDate): Boolean =
    day.isBefore(other) || day.isEqual(other)
}
