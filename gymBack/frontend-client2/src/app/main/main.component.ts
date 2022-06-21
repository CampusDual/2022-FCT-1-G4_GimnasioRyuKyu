import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, NavigationService } from 'ontimize-web-ngx';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

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

  calendar(){
  this.router.navigate(['/main/calendar'],{relativeTo:this.actRoute});
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
