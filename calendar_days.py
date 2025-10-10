# print calendar for a year, to be rendered in typst.app

import calendar
import sys
from datetime import date

header = '''#set page(paper: "a4", flipped: true, margin: 11pt, columns: 3)

#show table.cell.where(y: 0): strong

#set table(
  columns: (auto, 1fr, auto),
  align: (right, left, right),
  stroke: (x: none, y: 0.5pt + gray),
)

#let weekday(dayOfMonth, dayOfWeek) = return ([#dayOfMonth],[#dayOfWeek],[])
#let noteday(dayOfMonth, dayOfWeek, note) = return ([#dayOfMonth],[#dayOfWeek],text([#note], style: "italic", size: 9pt))
#let weekend(dayOfMonth, dayOfWeek) = return (
  table.cell([#dayOfMonth], fill: rgb("ffdddd")),
  table.cell([#dayOfWeek], fill: rgb("ffdddd")),
  table.cell([], fill: rgb("ffdddd")),
)
#let vacatio(dayOfMonth, dayOfWeek) = return (
  table.cell([#dayOfMonth], fill: rgb("ddffdd")),
  table.cell([#dayOfWeek], fill: rgb("ddffdd")),
  table.cell([], fill: rgb("ddffdd")),
)
#let adbeday(dayOfMonth, dayOfWeek, holiday) = return (
  table.cell([#dayOfMonth], fill: rgb("ddffff")),
  table.cell([#dayOfWeek], fill: rgb("ddffff")),
  table.cell(text([#holiday], style: "italic", size: 9pt), fill: rgb("ddffff")),
)
#let holiday(dayOfMonth, dayOfWeek, holiday) = return (
  table.cell([#dayOfMonth], fill: rgb("ffdddd")),
  table.cell([#dayOfWeek], fill: rgb("ffdddd")),
  table.cell(text([#holiday], style: "italic", size: 9pt), fill: rgb("ffdddd")),
)
#let endOfWeek = table.hline(stroke: 3pt + red)
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

holidays = {
    2026: {
        1: {
            1: "Anul nou",
            2: "Anul nou",
            6: "Boboteaza",
            7: "Sf. Ioan Botezătorul",
            24: "Unirea Principatelor",
        },
        4: {
            10: "Vinerea mare",
            12: "Paște",
            13: "Paște",
        },
        5: {
            1: "Ziua muncii",
            31: "Rusalii"
        },
        6: {
            1: "Rusalii",
        },
        8: {
            15: "Sf. Maria",
        },
        11: {
            30: "Sf. Andrei",
        },
        12: {
            1: "Ziua națională",
            25: "Crăciun",
        },
    }
}

vacation_days = {
    2026: {
        2: [16, 17, 18, 19, 20],
        4: [6, 7, 8, 9, 14],
    }
}

adobe_days = {
    2026: {
        1: {
            23: "Unirea Principatelor (in lieu)",
        },
        8: {
            14: "Sf. Maria (in lieu)",
        },
    }
}

notes = {
    2026: {
        6: {
            12: "ultima zi de școală",
            22: "EN: română",
            24: "EN: mate",
        },
        7: {
            1: "EN: rezultate",
            8: "MCR",
            22: "Repartizare"
        },
        9: {
            7: "prima zi de școală",
        },
    }
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

        day_name = None
        if day_name := adobe_days.get(year, {}).get(month, {}).get(day):
            day_type = "adbeday"
        elif day_name := holidays.get(year, {}).get(month, {}).get(day):
            day_type = "holiday"
        elif day_name := notes.get(year, {}).get(month, {}).get(day):
            day_type = "noteday"
        elif day in vacation_days.get(year, {}).get(month, []):
            day_type = "vacatio"
        elif day_of_week in ['Saturday', 'Sunday']:
            day_type = "weekend"
        else:
            day_type = "weekday"

        formatted_day_name = f',"{day_name}"' if day_name else ''
        print(f'  ..{day_type}({day:>2},"{romanian_initial}"{formatted_day_name}),')

        if day_of_week == 'Sunday':
            print("  endOfWeek,")

    print(")")

    if month < 12:
        print()
        print("#colbreak()")
        print()
