import { Component } from '@angular/core';
import { UsersService } from "../users/users.service";
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {

  model: any = {};
  sessionId: any = "";
  urlbase = "http://localhost:33333/users/login";
  http: HttpClient;

  constructor(public userService: UsersService, public router: Router) {}

  /*login() {
    const user = {email: this.model.email, password: this.model.password};
    this.userService.login(user).subscribe( data => {
      this.userService.setToken(data.token);
      this.router.navigateByUrl('/home');
    },
    error => {
      console.log(error);
    });
  }*/

  login() {
      this.userService.login(this.model.email, this.model.password).subscribe((res:any) => {
        if (res != null) {
          this.sessionId = res.headers.get('X-Auth-Token');
          sessionStorage.setItem(
            'token',
            this.sessionId);
          this.router.navigate(['/home']);
        } else {
            alert("Authentication failed.")
        }
      });
  }

  startsession(user: string, password: string): Observable<string | number> {
    const url = this.urlbase;
    const options: any = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa(user + ':' + password)
      }),
      observe: 'response'
    };
    const dataObservable: Observable<string | number> = new Observable(observer => {
      this.http.post(url, null, options).subscribe((resp: any) => {
        if (resp != null) {
          observer.next(resp.headers.get('X-Auth-Token'));
          this.router.navigate(['/home']);
        } else {
          // Invalid sessionId ...
          observer.error('Invalid user or password');
        }
      }, error => observer.error(error));
    });
    return dataObservable.pipe();
  }
}
