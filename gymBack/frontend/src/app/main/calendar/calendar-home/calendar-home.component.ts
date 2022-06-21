import { Observable } from "ontimize-web-ngx";
import { Event } from "../../../shared/models/event";
import { Router, ActivatedRoute, Data } from "@angular/router";
import { CalendarService } from "./../../../shared/calendar.service";
import { Component, ChangeDetectionStrategy } from "@angular/core";
import { setHours, setMinutes } from "date-fns";
import { colors } from "../demo-utils/colors";
import { map } from "rxjs/operators";
import { CalendarEvent, CalendarEventTitleFormatter, CalendarView } from "angular-calendar";
import {
  isSameMonth,
  isSameDay,
  startOfMonth,
  endOfMonth,
  startOfWeek,
  endOfWeek,
  startOfDay,
  endOfDay,
  format,
} from "date-fns";
import { CustomEventTitleFormatter } from "../demo-utils/custom-event-title-formatter.provider";

@Component({
  selector: "app-calendar-home",
  templateUrl: "./calendar-home.component.html",
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ["./calendar-home.component.css"],
  providers: [
    {
      provide: CalendarEventTitleFormatter,
      useClass: CustomEventTitleFormatter,
    },
  ],
})
export class CalendarHomeComponent {
  view: CalendarView = CalendarView.Week;

  viewDate: Date = new Date();

  events$: Observable<CalendarEvent<{ event: Event }>[]>;

  activeDayIsOpen: boolean = false;

  constructor(
    private calendarService: CalendarService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.fetchEvents();
    this.events$.subscribe((x) => console.log(x));
  }

  fetchEvents(): void {
    const getStart: any = {
      month: startOfMonth,
      week: startOfWeek,
      day: startOfDay,
    }[this.view];

    const getEnd: any = {
      month: endOfMonth,
      week: endOfWeek,
      day: endOfDay,
    }[this.view];

    this.events$ = this.calendarService.getEvents().pipe(
      map((results: any) => {
        return results.data.map((event: Event) => {
          return {
            title:
              event.room_name +
              ": " +
              event.class_name +
              " - " +
              event.monitor_name,
            start: setHours(parseFloat(event.date), parseFloat(event.h_start)),
            end: setHours(parseFloat(event.date), parseFloat(event.h_end)),
            color: colors.customRed,
            cssClass: "my-custom-class",
          };
        });
      })
    );
  }

  dayClicked({
    date,
    events,
  }: {
    date: Date;
    events: CalendarEvent<{ event: Event }>[];
  }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
        this.viewDate = date;
      }
    }
  }
}
