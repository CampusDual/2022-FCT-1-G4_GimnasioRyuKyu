import { Component, OnInit, ViewEncapsulation, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, NavigationService } from 'ontimize-web-ngx';
import { Clients } from '../../shared/models/Clients';
import { ClientsClasses } from '../../shared/models/ClientsClasses';
import { CalendarService } from '../../shared/service/calendar.service';

@Component({

  selector: 'calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss'],
  encapsulation: ViewEncapsulation.None

})

export class CalendarComponent implements OnInit {
  active = 'top'
  clients: Clients[]=[];
  clientsClasses:ClientsClasses[]=[];
  router: Router;
  name='';
  lastname='';
  idClient='';
  dni='';
idRoomClass:String[]=[];
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
            this.clients=this.clients.map(client =>{
             if(client.EMAIL.includes(this.title)){
            this.name=client.NAME;
            this.lastname=client.LASTNAME;
            this.dni=client.DNI;
                        this.idClient=client.ID;
             console.log(name);
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
                  this.clientsClasses=this.clientsClasses.map(clientClass =>{
                  // if(clientClass.ID_CLIENT.includes(this.idClient)){
                    console.log(clientClass);
               //    }
                  })

                },
                error => console.log(error)
              )
      }





}
