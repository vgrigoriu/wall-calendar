import java.time.DayOfWeek

private val initials = Map(
  DayOfWeek.MONDAY -> "L",
  DayOfWeek.TUESDAY -> "M",
  DayOfWeek.WEDNESDAY -> "M",
  DayOfWeek.THURSDAY -> "J",
  DayOfWeek.FRIDAY -> "V",
  DayOfWeek.SATURDAY -> "S",
  DayOfWeek.SUNDAY -> "D",
)

extension (dow: DayOfWeek) {
  def initial: String = initials(dow)
}
