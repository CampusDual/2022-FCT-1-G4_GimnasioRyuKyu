import { OntimizeBaseService, Util } from 'ontimize-web-ngx';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable, Injector } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class CalendarService extends OntimizeBaseService{

  urlClients = "http://207.188.183.218:33333/clients/activeClient?columns=ID,NAME,LASTNAME,EMAIL,DNI,PHONE,BIRTHDAY,PHOTO";
  urlClientsClasses = "http://207.188.183.218:33333/clientsClasses/showClientsClasses?columns=ID_CLIENT,ID_ASSING_ROOM,DATE,CLASS_NAME,H_START,H_END,MONITOR_NAME";
  url ="http://207.188.183.218:33333/clientsClasses/showClientsClasses/search";
  urlClientByEmail="http://207.188.183.218:33333/clients/activeClient/search"

  protected buildHeaders(): HttpHeaders {
    let headers = new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json;charset=UTF-8'
    });
    const sessionId = this.authService.getSessionInfo().id;
    if (Util.isDefined(sessionId)) {
      headers = headers.append('Authorization', 'Bearer ' + sessionId);
    }
    return headers;
  }


  constructor(private http:HttpClient, protected injector: Injector) {
    super(injector);

  }

 getClients() {
    return this.http.get<any>(this.urlClients);
 }

 getClientsClasses() {
  return this.http.get<any>(this.urlClientsClasses);
 }

 getEvents(id) {
  const options = {
    headers : this.buildHeaders()
  };
  return this.http.post(this.url, {filter: { "ID_CLIENT": id }, columns: ["ID_CLIENT", "ID_ASSING_ROOM", "DATE", "CLASS_NAME", "H_START", "H_END", "MONITOR_NAME"]}, options);
 }



}
