import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Classes } from 'src/app/models/Classes';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class HomeService {

  urlClasses = "http://localhost:33333/classes/showClasses?columns=NAME,DESCRIPTION";
  urlRooms = "http://localhost:33333/rooms/showRooms?columns=NAME,DESCRIPTION,PHOTO";
 constructor(private http:HttpClient){}

 getClasses() {
  return this.http.get<any>(this.urlClasses);
  }

  getRooms() {
    return this.http.get<any>(this.urlRooms);
    }

}
