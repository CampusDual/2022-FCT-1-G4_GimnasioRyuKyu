import { Observable } from 'ontimize-web-ngx';
import {Event} from '../../../shared/models/event';
import { Router, ActivatedRoute } from '@angular/router';
import { CalendarService } from './../../../shared/calendar.service';
import { Component, ChangeDetectionStrategy } from '@angular/core';
import { setHours, setMinutes } from 'date-fns';
import { colors } from '../demo-utils/colors';
import { map } from 'rxjs/operators';
import {
  CalendarEvent,
  CalendarView,
} from 'angular-calendar';
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
} from 'date-fns';


function getTimezoneOffsetString(date: Date): string {
  const timezoneOffset = date.getTimezoneOffset();
  const hoursOffset = String(
    Math.floor(Math.abs(timezoneOffset / 60))
  ).padStart(2, '0');
  const minutesOffset = String(Math.abs(timezoneOffset % 60)).padEnd(2, '0');
  const direction = timezoneOffset > 0 ? '-' : '+';

  return `T00:00:00${direction}${hoursOffset}:${minutesOffset}`;
}

@Component({
  selector: 'app-calendar-home',
  templateUrl: './calendar-home.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./calendar-home.component.css']
})
export class CalendarHomeComponent {

  view: CalendarView = CalendarView.Week;

  viewDate: Date = new Date();

  events$: Observable<CalendarEvent<{ event: Event}>[]>;

  constructor(private calendarService: CalendarService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {

  }

  ngOnInit() {
    this.fetchEvents();
    this.events$.subscribe( x => console.log(x));
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
        map(({ results }: { results: Event[] }) => {
          return results.map((event: Event) => {
            return {
              title: event.id_room_class,
              start: new Date(event.h_start),
              color: colors.yellow,
              allDay: false,
              meta: {
                event,
              },
            };
          });
        })
      );
  }

/*

  refreshData() {
    this.calendarService.getEvents().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
  }

  handleSuccessfulResponse(response) {
    this.iEvents = response;
  }

  events: CalendarEvent[] = [
    {
      title: 'No event end date',
      start: setHours(setMinutes(new Date(), 0), 3),
      end: setHours(setMinutes(new Date(), 0), 4),
      color: colors.blue,
    },
    {
      title: 'No event end date',
      start: setHours(setMinutes(new Date(), 0), 5),
      end: setHours(setMinutes(new Date(), 0), 6),
      color: colors.yellow,
    },
  ];*/
}
