import { Component, OnInit, ViewEncapsulation, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, NavigationService } from 'ontimize-web-ngx';


@Component({

  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss'],
  encapsulation: ViewEncapsulation.None

})

export class CalendarComponent implements OnInit {

  router: Router;
    title = '';

    constructor(
      private actRoute: ActivatedRoute,
      router: Router,
      @Inject(NavigationService) public navigation: NavigationService,
      @Inject(AuthService) private authService: AuthService,
      ) {

        this.router = router;
      }

    ngOnInit() {
      this.getUserName();
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

}
