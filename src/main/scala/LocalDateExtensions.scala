import Holidays.schoolHolidays

import java.time.{DayOfWeek, LocalDate}

private val weekendDays = Set(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)

extension (day: LocalDate) {
  def isWeekend: Boolean = weekendDays.contains(
    day.getDayOfWeek,
  )

  def isSchoolHoliday: Boolean = {
    schoolHolidays.exists { case (start, end) =>
      start.lte(day) && day.lte(end)
    }
  }

  private def lte(other: LocalDate): Boolean =
    day.isBefore(other) || day.isEqual(other)
}
