import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Clients } from "../models/Clients";

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  urlClients = "http://207.188.183.218:33333/clients/activeClient?columns=ID,NAME,LASTNAME,EMAIL,DNI,PHONE,BIRTHDAY,PHOTO";
  urlClientsClasses = "http://207.188.183.218:33333/clientsClasses/showClientsClasses?columns=ID_CLIENT,ID_ASSING_ROOM,DATE,CLASS_NAME,H_START,H_END,MONITOR_NAME";
  url ="http://207.188.183.218:33333/clientsClasses/showClientsClasses/";


 constructor(private http:HttpClient){}

 getClients() {
    return this.http.get<any>(this.urlClients);
 }

 getClientsClasses() {
  return this.http.get<any>(this.urlClientsClasses);
 }

 getEvents() {
  return this.http.post(this.url, {});
 }



}
