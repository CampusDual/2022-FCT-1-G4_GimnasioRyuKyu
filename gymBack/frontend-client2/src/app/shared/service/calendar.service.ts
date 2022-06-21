import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  urlClients = "http://localhost:33333/clients/activeClient?columns=ID,NAME,LASTNAME,EMAIL,DNI";
  urlClientsClasses = "http://localhost:33333/clientsClasses/showClientsClasses?columns=ID_CLIENT,ID_ASSING_ROOM";
 constructor(private http:HttpClient){}

 getClients() {
    return this.http.get<any>(this.urlClients);
 }

 getClientsClasses() {
  return this.http.get<any>(this.urlClientsClasses);
 }



}
