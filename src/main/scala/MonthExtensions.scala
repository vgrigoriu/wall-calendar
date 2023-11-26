import java.time.{LocalDate, Month}
import java.time.Month.*

private val names = Map(
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

extension (month: Month) {
  def displayName: String = names(month)

  def daysIn(year: Int): Iterator[LocalDate] =
    Iterator.iterate(LocalDate.of(year, month, 1))(_.plusDays(1))
      .takeWhile(_.getMonth == month)
}
