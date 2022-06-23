import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AuthService, NavigationService, Observable } from "ontimize-web-ngx";
import { ActivatedRoute, Router } from "@angular/router";
import { Inject,
  ChangeDetectionStrategy,
} from "@angular/core";
import { Clients } from "../../shared/models/Clients";
import { Event } from "../../shared/models/event";
import { ClientsClasses } from "../../shared/models/ClientsClasses";
import { CalendarService } from "../../shared/service/calendar.service";
import { DatePipe, formatDate } from '@angular/common';

@Component({

  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
  encapsulation: ViewEncapsulation.None

})

export class ProfileComponent implements OnInit {
  currentClient: Clients = new Clients();
  active = "top";
  clients: Clients[] = [];
  clientsClasses: ClientsClasses[] = [];
  router: Router;
  title = "";
  datepipe:DatePipe;


  constructor(@Inject(AuthService) private authService: AuthService,public calendarService: CalendarService, router: Router) {this.router = router;}

  ngOnInit():void {
    this.getUserName();
    this.getClient();
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
        let birthday=formatDate(new Date(this.currentClient.BIRTHDAY),'dd/MM/yyyy','en_US');
        this.currentClient.BIRTHDAY= birthday;
        let subDate=formatDate(new Date(this.currentClient.SUB_EXPIRATION_DATE),'dd/MM/yyyy','en_US');
        this.currentClient.SUB_EXPIRATION_DATE= subDate;

        console.log(this.currentClient);
      },
      (error) => console.log(error)
    );
  }

}
