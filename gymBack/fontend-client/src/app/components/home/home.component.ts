import { Component, OnInit } from '@angular/core';
import { Classes } from 'src/app/models/Classes';
import { Rooms } from 'src/app/models/Rooms';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  classesData: Classes[]=[];
  roomsData: Rooms[]=[];
  constructor(public homeService:HomeService) { }

  ngOnInit(): void {
    this.getClasses();
    this.getRooms();
  }
  private getClasses(){
    this.homeService.getClasses().subscribe(
      res => {
        this.classesData = res.data;
        console.log(this.classesData);
      },
      error => console.log(error)
    )
  }

  private getRooms(){
    this.homeService.getRooms().subscribe(
      res => {
        this.roomsData = res.data;
        console.log(this.roomsData);
      },
      error => console.log(error)
    )
  }
}
