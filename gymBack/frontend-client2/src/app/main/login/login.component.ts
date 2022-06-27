import { Component, Inject, Injector, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, LocalStorageService, NavigationService } from 'ontimize-web-ngx';
import { Observable } from 'rxjs';
import { CalendarService } from './../../shared/service/calendar.service';
import { Clients } from '../../shared/models/Clients';

@Component({
  selector: 'login',
  styleUrls: ['./login.component.scss'],
  templateUrl: './login.component.html',
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});
  userCtrl: FormControl = new FormControl('', Validators.required);
  pwdCtrl: FormControl = new FormControl('', Validators.required);
  sessionExpired = false;

  router: Router;
  clients: Clients[]=[];

  constructor(
    private actRoute: ActivatedRoute,
    router: Router,
    @Inject(NavigationService) public navigation: NavigationService,
    @Inject(AuthService) private authService: AuthService,
    @Inject(LocalStorageService) private localStorageService,
    public injector: Injector,
    public calendarService:CalendarService
  ) {
    this.router = router;

    const qParamObs: Observable<any> = this.actRoute.queryParams;
    qParamObs.subscribe(params => {
      if (params) {
        const isDetail = params['session-expired'];
        if (isDetail === 'true') {
          this.sessionExpired = true;
        } else {
          this.sessionExpired = false;
        }
      }
    });

  }

  ngOnInit(): any {
    this.navigation.setVisible(false);

    this.loginForm.addControl('username', this.userCtrl);
    this.loginForm.addControl('password', this.pwdCtrl);

    if (this.authService.isLoggedIn()) {
      this.router.navigate(['../'], { relativeTo: this.actRoute });
    } else {
      this.authService.clearSessionData();
    }
  }

  login() {
    const userName = this.loginForm.value.username;
    const password = this.loginForm.value.password;
    if (userName && userName.length > 0 && password && password.length > 0) {
      const self = this;

      this.calendarService.getClients().subscribe(
        (res) => {
          this.clients = res.data;
          let clientUser = this.clients.filter(
            (client) => client.EMAIL.indexOf(userName) > -1
          );
          if(clientUser.length>0){
            this.authService.login(userName, password)
            .subscribe(() => {
              self.sessionExpired = false;
              self.router.navigate([''], { relativeTo: this.actRoute });
            }, this.handleError);
          }else{
            console.error("El cliente no está activo");
          }

        },
        (error) => console.log(error)
      );


    }
  }

  handleError(error) {
    switch (error.status) {
      case 401:
        console.error('Email or password is wrong.');
        break;
      default: break;
    }
  }

}
