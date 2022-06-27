import { CalendarService } from './../../../shared/service/calendar.service';
import { Component, ChangeDetectionStrategy, Inject } from '@angular/core';
import { AuthService, Observable } from 'ontimize-web-ngx';
import { Event } from "../../../shared/models/event";
import { Router } from "@angular/router";
import { setHours,isSameMonth,isSameDay} from "date-fns";
import { colors } from "../demo-utils/colors";
import { map } from "rxjs/operators";
import { CalendarEvent, CalendarEventTitleFormatter, CalendarView } from "angular-calendar";
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
export class CalendarClientsHomeComponent{

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
    public calendarService:CalendarService,
    @Inject(AuthService) private authService: AuthService
  ) { }

  ngOnInit() {
    this.getUserName();
    this.getClient();
    this.fetchEvents();
   }

   getUserName() {
    this.title = this.authService.getSessionInfo().user;
  }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  getSessionInfo() {
    return this.authService.getSessionInfo();
  }

  private getClient() {
    this.calendarService.getClients().subscribe(
      (res) => {
        this.clients = res.data;
        let clientUser = this.clients.filter(
          (client) => client.EMAIL.indexOf(this.title) > -1
        );
        this.currentClient = clientUser.length > 0 ? clientUser[0] : null;
        console.log(this.currentClient);
        this.getClientClasses();
      },
      (error) => console.log(error)
    );

  }



  getClientClasses() :any {
    this.calendarService.getClientsClasses().subscribe(
      (res) => {
        this.clientsClasses = res.data;
        let classesUser = this.clientsClasses.filter(
          (clientClass) => clientClass.ID_CLIENT==(this.currentClient.ID)
        );
        this.clientsClasses = classesUser;
        return this.clientsClasses;
      },
      (error) => console.log(error)
    );
  }

  fetchEvents(): void {
    this.events$ = this.calendarService.getEvents(this.currentClient.ID).pipe(
      map((results: any) => {
        return results.data.map((event: Event) => {
          return {
            title:
              event.CLASS_NAME +
              ": " +
              event.CLASS_NAME +
              " - " +
              event.MONITOR_NAME,
            start: setHours(parseFloat(event.DATE), parseFloat(event.H_START)),
            end: setHours(parseFloat(event.DATE), parseFloat(event.H_END)),
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
