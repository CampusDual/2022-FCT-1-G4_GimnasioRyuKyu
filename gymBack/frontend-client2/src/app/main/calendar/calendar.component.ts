import { AuthService, NavigationService, Observable } from "ontimize-web-ngx";
import { ActivatedRoute, Router } from "@angular/router";
import {
  Component,
  OnInit,
  ViewEncapsulation,
  Inject,
  ChangeDetectionStrategy,
} from "@angular/core";
import { Clients } from "../../shared/models/Clients";
import { Event } from "../../shared/models/event";
import { ClientsClasses } from "../../shared/models/ClientsClasses";
import { CalendarService } from "../../shared/service/calendar.service";

import { setHours, setMinutes } from "date-fns";
import { colors } from "./demo-utils/colors";
import { map } from "rxjs/operators";
import {
  CalendarEvent,
  CalendarEventTitleFormatter,
  CalendarView,
} from "angular-calendar";
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
  selector: "calendar",
  templateUrl: "./calendar.component.html",
  //changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ["./calendar.component.scss"],
  providers: [
    {
      provide: CalendarEventTitleFormatter,
      useClass: CustomEventTitleFormatter,
    },
  ],
  encapsulation: ViewEncapsulation.None,
})
export class CalendarComponent implements OnInit {
  //Cosas calendario
  view: CalendarView = CalendarView.Week;
  viewDate: Date = new Date();
  events$: Observable<CalendarEvent<{ event: Event }>[]>;
  activeDayIsOpen: boolean = false;

  //Cosas iker
  currentClient: Clients = new Clients();
  active = "top";
  clients: Clients[] = [];
  clientsClasses: ClientsClasses[] = [];
  router: Router;
  title = "";

  constructor(
    public calendarService: CalendarService,
    private actRoute: ActivatedRoute,
    router: Router,
    @Inject(NavigationService) public navigation: NavigationService,
    @Inject(AuthService) private authService: AuthService
  ) {
    this.router = router;
  }

  ngOnInit(): void {
    this.getUserName();
    this.getClient();

  }

  logout() {
    this.authService.logout();
    this.router.navigate(["/main/login"], { relativeTo: this.actRoute });
  }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  getSessionInfo() {
    return this.authService.getSessionInfo();
  }

  getUserName() {
    this.title = this.authService.getSessionInfo().user;
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
      },
      (error) => console.log(error)
    );
    this.getClientClasses();
  }



  getClientClasses() {
    this.calendarService.getClientsClasses().subscribe(
      (res) => {
        this.clientsClasses = res.data;
        let classesUser = this.clientsClasses.filter(
          (clientClass) => clientClass.ID_CLIENT==(this.currentClient.ID)
        );
        this.clientsClasses = classesUser;
        console.log(this.clientsClasses);
      },
      (error) => console.log(error)
    );
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
}
