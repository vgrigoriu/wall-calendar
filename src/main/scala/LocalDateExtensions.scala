import java.time.LocalDate

extension (day: LocalDate) {
  private def lte(other: LocalDate): Boolean =
    day.isBefore(other) || day.isEqual(other)
}
