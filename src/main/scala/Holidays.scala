import java.time.LocalDate
import java.time.Month._

object Holidays {
  val holidays: Map[LocalDate, String] = Map(
    LocalDate.parse("2023-01-01") -> "Anul nou",
    LocalDate.parse("2023-01-02") -> "Anul nou",
    LocalDate.parse("2023-01-24") -> "Unirea Principatelor Române",
    LocalDate.parse("2023-04-14") -> "Vinerea Mare",
    LocalDate.parse("2023-04-16") -> "Paștele ortodox",
    LocalDate.parse("2023-04-17") -> "A doua zi de Paște",
    LocalDate.parse("2023-05-01") -> "Ziua Muncii",
    LocalDate.parse("2023-06-01") -> "Ziua Copilului",
    LocalDate.parse("2023-06-04") -> "Rusalii",
    LocalDate.parse("2023-06-05") -> "A doua zi de Rusalii",
    LocalDate.parse("2023-08-15") -> "Adormirea Maicii Domnului",
    LocalDate.parse("2023-11-30") -> "Sfântul Andrei",
    LocalDate.parse("2023-12-01") -> "Marea Unire",
    LocalDate.parse("2023-12-25") -> "Crăciun",
    LocalDate.parse("2023-12-26") -> "A doua zi de Crăciun",
    LocalDate.of(2024, JANUARY, 1) -> "Anul nou",
    LocalDate.of(2024, JANUARY, 2) -> "Anul nou",
    LocalDate.of(2024, JANUARY, 6) -> "Boboteaza",
    LocalDate.of(2024, JANUARY, 7) -> "Sf. Ioan",
    LocalDate.of(2024, JANUARY, 24) -> "Unirea mică",
    LocalDate.of(2024, MAY, 1) -> "Ziua muncii",
    LocalDate.of(2024, MAY, 3) -> "Vinerea Mare",
    LocalDate.of(2024, MAY, 5) -> "Paște",
    LocalDate.of(2024, MAY, 6) -> "Paște",
    LocalDate.of(2024, JUNE, 1) -> "Ziua copilului",
    LocalDate.of(2024, JUNE, 23) -> "Rusalii",
    LocalDate.of(2024, JUNE, 24) -> "Rusalii",
    LocalDate.of(2024, AUGUST, 15) -> "Sf. Maria",
    LocalDate.of(2024, NOVEMBER, 30) -> "Sf. Andrei",
    LocalDate.of(2024, DECEMBER, 1) -> "Unirea mare",
    LocalDate.of(2024, DECEMBER, 25) -> "Crăciun",
    LocalDate.of(2024, DECEMBER, 26) -> "Crăciun",
  )

  val schoolHolidays: Seq[(LocalDate, LocalDate)] = List(
    LocalDate.of(2024, JANUARY, 1) -> LocalDate.of(2024, JANUARY, 5),
    LocalDate.of(2024, FEBRUARY, 19) -> LocalDate.of(2024, FEBRUARY, 23),
    LocalDate.of(2024, APRIL, 27) -> LocalDate.of(2024, MAY, 7),
    LocalDate.of(2024, JUNE, 22) -> LocalDate.of(2024, SEPTEMBER, 8),
    LocalDate.of(2024, OCTOBER, 28) -> LocalDate.of(2024, NOVEMBER, 1),
    LocalDate.of(2024, DECEMBER, 23) -> LocalDate.of(2024, DECEMBER, 31),
  )

  val adobeHolidays: Map[LocalDate, String] = Map(
    LocalDate.of(2024, JANUARY, 5) -> "Boboteaza (in  lieu)",
    LocalDate.of(2024, JANUARY, 8) -> "Sf. Ioan (in lieu)",
    LocalDate.of(2024, FEBRUARY, 9) -> "Global Wellbeing",
    LocalDate.of(2024, MARCH, 29) -> "Global Wellbeing",
    LocalDate.of(2024, MAY, 6) -> "Global Wellbeing",
    LocalDate.of(2024, MAY, 31) -> "Ziua copilului (in lieu)",
    LocalDate.of(2024, JULY, 1) -> "Global Wellbeing",
    LocalDate.of(2024, SEPTEMBER, 20) -> "Global Wellbeing",
    LocalDate.of(2024, OCTOBER, 25) -> "Global Wellbeing",
    LocalDate.of(2024, NOVEMBER, 29) -> "Sf. Andrei (in lieu)",
    LocalDate.of(2024, DECEMBER, 2) -> "Unirea mare (in lieu)",
    LocalDate.of(2024, DECEMBER, 27) -> "PTO",
    LocalDate.of(2024, DECEMBER, 30) -> "PTO",
    LocalDate.of(2024, DECEMBER, 31) -> "PTO",
  )
}
