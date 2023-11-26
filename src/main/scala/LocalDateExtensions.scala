import Holidays.schoolHolidays

import java.time.LocalDate

extension (day: LocalDate) {
  def isSchoolHoliday: Boolean = {
    schoolHolidays.exists { case (start, end) =>
      start.lte(day) && day.lte(end)
    }
  }

  private def lte(other: LocalDate): Boolean =
    day.isBefore(other) || day.isEqual(other)
}
