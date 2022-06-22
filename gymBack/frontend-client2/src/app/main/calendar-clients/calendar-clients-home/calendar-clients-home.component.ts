import { CalendarService } from './../../../shared/service/calendar.service';
import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Observable } from "ontimize-web-ngx";
import { Event } from "../../../shared/models/event";
import { Router, ActivatedRoute, Data } from "@angular/router";
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
import { ClientsClasses } from '../../../shared/models/ClientsClasses';
import { Clients } from '../../../shared/models/Clients';

@Component({
  selector: 'app-calendar-clients-home',
  templateUrl: './calendar-clients-home.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./calendar-clients-home.component.css'],
  providers: [
    {
      provide: CalendarEventTitleFormatter,
      useClass: CustomEventTitleFormatter,
    },
  ],
})
export class CalendarClientsHomeComponent implements OnInit {

  view: CalendarView = CalendarView.Week;
  viewDate: Date = new Date();
  events$: Observable<CalendarEvent<{ event: Event }>[]>;
  activeDayIsOpen: boolean = false;

    //Cosas iker
    currentClient:Clients=new Clients;
    active = 'top'
    clients: Clients[]=[];
    clientsClasses:ClientsClasses[]=[];
    router: Router;
    title = '';

  constructor(
    private Router: Router,
    private activatedRoute: ActivatedRoute,
    public calendarService:CalendarService,
  ) { }

  ngOnInit() {
    this.fetchEvents();
    console.log(this.events$);
  }

  getClientClasses(){
    this.calendarService.getClientsClasses().subscribe(
             res => {
               this.clientsClasses = res.data;
              this.clientsClasses.map(clientClass =>{
                if(clientClass.ID_CLIENT==(this.currentClient.ID)){
                 console.log(clientClass);
                }
               })

             },
             error => console.log(error)
           )
           return this.clientsClasses;
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

    this.events$ = this.calendarService.getClientsClasses().pipe(
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

}
