import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Classes } from 'src/app/models/Classes';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  classesData: Classes [] = [];
 constructor(private http:HttpClient){}

 getClasses() {
  return this.http.get<Classes[]>("http://localhost:33333/classes/showClasses?columns=ID,NAME,DESCRIPTION");
  }
}
