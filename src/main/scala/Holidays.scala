import java.time.LocalDate
import java.time.Month._

object Holidays {
  val schoolHolidays: Seq[(LocalDate, LocalDate)] = List(
    LocalDate.of(2024, JANUARY, 1) -> LocalDate.of(2024, JANUARY, 5),
    LocalDate.of(2024, FEBRUARY, 19) -> LocalDate.of(2024, FEBRUARY, 23),
    LocalDate.of(2024, APRIL, 27) -> LocalDate.of(2024, MAY, 7),
    LocalDate.of(2024, JUNE, 22) -> LocalDate.of(2024, SEPTEMBER, 8),
    LocalDate.of(2024, OCTOBER, 28) -> LocalDate.of(2024, NOVEMBER, 1),
    LocalDate.of(2024, DECEMBER, 23) -> LocalDate.of(2024, DECEMBER, 31),
  )
}
