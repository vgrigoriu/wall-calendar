# print calendar for a year, to be rendered in typst.app

import calendar
import sys
from datetime import date

header = '''#set page(paper: "a4", flipped: true, margin: 11pt, columns: 3)

#show table.cell.where(y: 0): strong

#set table(
  columns: (auto, 1fr, auto),
  align: (right, left, right),
  stroke: (x: none),
)

#let weekday(dayOfMonth, dayOfWeek) = return ([#dayOfMonth],[#dayOfWeek],[])
#let noteday(dayOfMonth, dayOfWeek, note) = return ([#dayOfMonth],[#dayOfWeek],text([#note], style: "italic", size: 9pt))
#let weekend(dayOfMonth, dayOfWeek) = return (
  table.cell([#dayOfMonth], fill: rgb("ffdddd")),
  table.cell([#dayOfWeek], fill: rgb("ffdddd")),
  table.cell([], fill: rgb("ffdddd")),
)
#let vacatio(dayOfMonth, dayOfWeek) = return (
  table.cell([#dayOfMonth], fill: rgb("eeffee")),
  table.cell([#dayOfWeek], fill: rgb("eeffee")),
  table.cell([], fill: rgb("eeffee")),
)
#let holiday(dayOfMonth, dayOfWeek, holiday) = return (
  table.cell([#dayOfMonth], fill: rgb("ffdddd")),
  table.cell([#dayOfWeek], fill: rgb("ffdddd")),
  table.cell(text([#holiday], style: "italic", size: 9pt), fill: rgb("ffdddd")),
)
#let endOfWeek = table.hline(stroke: 4pt + red)

'''

print(header)

year = int(sys.argv[1])

romanian_days = {
    'Monday': 'L',
    'Tuesday': 'M',
    'Wednesday': 'M',
    'Thursday': 'J',
    'Friday': 'V',
    'Saturday': 'S',
    'Sunday': 'D'
}

romanian_months = {
    1: 'Ianuarie',
    2: 'Februarie',
    3: 'Martie',
    4: 'Aprilie',
    5: 'Mai',
    6: 'Iunie',
    7: 'Iulie',
    8: 'August',
    9: 'Septembrie',
    10: 'Octombrie',
    11: 'Noiembrie',
    12: 'Decembrie'
}

for month in range(1, 13):
    month_name = romanian_months[month]
    print(f"#table(")
    print(f"  table.header[][{month_name}][],")

    days_in_month = calendar.monthrange(year, month)[1]

    for day in range(1, days_in_month + 1):
        current_date = date(year, month, day)
        day_of_week = current_date.strftime("%A")
        romanian_initial = romanian_days[day_of_week]

        if day_of_week in ['Saturday', 'Sunday']:
            day_type = "weekend"
        else:
            day_type = "weekday"

        print(f'  ..{day_type}({day:>2}, "{romanian_initial}"),')

        if day_of_week == 'Sunday':
            print("  endOfWeek,")

    print(")")

    if month < 12:
        print()
        print("#colbreak()")
        print()
