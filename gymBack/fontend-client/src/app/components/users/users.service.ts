import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { CookieService } from "ngx-cookie-service";

@Injectable({
  providedIn: "root"
})
export class UsersService {
  constructor(private http: HttpClient, private cookies: CookieService) {}

  protected buildHeaders(): HttpHeaders {

    let headers = new HttpHeaders({

      'Access-Control-Allow-Origin': '*',

      'Content-Type': 'application/json;charset=UTF-8'

    });

    const sessionId = this.getToken;
    headers = headers.append('Authorization', 'Bearer ' + sessionId);
    return headers;

  }

  /*login(user:any): Observable<any> {
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(user)});
    return this.http.post("https://localhost:33333/users/login", null ,{headers,responseType: 'text' as 'json'});
  }*/

  public login(username:string, password:string): Observable<any>{
    const headers = new HttpHeaders({AUthorization: 'Basic ' + btoa(username + ':' + password)});
    return this.http.get("http://localhost:33333/users/login",{headers,responseType: 'text' as 'json'}
    );

  }

  setToken(token: any) {
    this.cookies.set("token", token);
  }
  getToken() {
    return this.cookies.get("token");
  }
  getUser() {
    return this.http.get("//localhost:33333/users/login");
  }
  getUserLogged() {
    const token = this.getToken();
    // Aquí iría el endpoint para devolver el usuario para un token
  }
}

// protected buildHeaders(): HttpHeaders {

//   let headers = new HttpHeaders({

//     'Access-Control-Allow-Origin': '*',

//     'Content-Type': 'application/json;charset=UTF-8'

//   });

//   const sessionId = this.authService.getSessionInfo().id;

//   if (Util.isDefined(sessionId)) {

//     headers = headers.append('Authorization', 'Bearer ' + sessionId);

//   }

//   return headers;

// }




// public getEvents(){

//   const options = {

//     headers : this.buildHeaders()

//   };

//   return this.http.get(this.url + '?columns=id_room_class,date,h_start,h_end,id_monitor,class_name,monitor_name,room_name' , options);

// }
