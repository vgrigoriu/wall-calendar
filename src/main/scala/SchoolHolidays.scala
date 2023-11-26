import Holidays.schoolHolidays

import java.time.LocalDate

private object SchoolHolidays {
  def isSchoolHoliday(day: LocalDate): Boolean = {
    schoolHolidays.exists { case (start, end) =>
      start.lte(day) && day.lte(end)
    }
  }
}

extension (date: LocalDate) {
  def isSchoolHoliday: Boolean = SchoolHolidays.isSchoolHoliday(date)
}
