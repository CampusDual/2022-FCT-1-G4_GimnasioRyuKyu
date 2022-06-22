import { AuthService, NavigationService, Observable } from 'ontimize-web-ngx';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, ViewEncapsulation, Inject, ChangeDetectionStrategy } from '@angular/core';
import { Clients } from '../../shared/models/Clients';
import { Event } from "../../shared/models/event";
import { ClientsClasses } from '../../shared/models/ClientsClasses';
import { CalendarService } from '../../shared/service/calendar.service';

import { setHours, setMinutes } from "date-fns";
import { colors } from "./demo-utils/colors";
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
import { CustomEventTitleFormatter } from "./demo-utils/custom-event-title-formatter.provider";




@Component({

  selector: 'calendar',
  templateUrl: './calendar.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./calendar.component.scss'],
  providers: [
    {
      provide: CalendarEventTitleFormatter,
      useClass: CustomEventTitleFormatter,
    },
  ],
  encapsulation: ViewEncapsulation.None
})

export class CalendarComponent implements OnInit {

  //Cosas calendario
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
    private actRoute: ActivatedRoute,
    router: Router,
    @Inject(NavigationService) public navigation: NavigationService,
    @Inject(AuthService) private authService: AuthService,
    ) {
      this.router = router;
    }

    ngOnInit():void {
      this.getUserName();
      this.getClient();
      this.getClientClasses();
    }

      logout() {
        this.authService.logout();
        this.router.navigate(['/main/login'], { relativeTo: this.actRoute });
      }

      isLoggedIn() {
        return this.authService.isLoggedIn();
      }

      getSessionInfo() {
        return this.authService.getSessionInfo();
      }

      getUserName() {
        return this.title = this.authService.getSessionInfo().user;
      }

      private getClient(){
        this.calendarService.getClients().subscribe(
          res => {
            this.clients = res.data;
            this.clients.map(client =>{
             if(client.EMAIL.includes(this.title)){
            this.currentClient.NAME=client.NAME;
            this.currentClient.LASTNAME=client.LASTNAME;
            this.currentClient.DNI=client.DNI;
            this.currentClient.ID=client.ID;
            console.log(client);
             }
            })

          },
          error => console.log(error)
        )
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
