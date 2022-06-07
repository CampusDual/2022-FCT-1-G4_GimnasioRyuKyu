import { Router, ActivatedRoute } from '@angular/router';
import { CalendarService } from './../../../shared/calendar.service';
import { Component, ChangeDetectionStrategy } from '@angular/core';
import { setHours, setMinutes } from 'date-fns';
import { colors } from '../demo-utils/colors';
import {
  CalendarEvent,
  CalendarView,
} from 'angular-calendar';

@Component({
  selector: 'app-calendar-home',
  templateUrl: './calendar-home.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./calendar-home.component.css']
})
export class CalendarHomeComponent {

  view: CalendarView = CalendarView.Day;

  viewDate: Date = new Date();

  iEvents : Array<Event>;

  constructor(private calendarService: CalendarService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {

  }

  ngOnInit() {
   this.refreshData();
  }

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
  ];
}
