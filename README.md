# Print wall calendar planner for a year

## Usage

- Add holidays for the year you want to print.
- Run `sbt run` or `sbt "run 2023"`.
- Open the html in Safari and print it to PDF, A4 landscape, no headers/footers, print backgrounds.

## Alternative Usage

- using Python + Typst:

      YEAR=2025
      python3 calendar_days.py $YEAR >"calendar$YEAR.typ"
      typst compile "calendar$YEAR.typ"
