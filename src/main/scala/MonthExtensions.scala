import java.time.Month
import java.time.Month._

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
}
