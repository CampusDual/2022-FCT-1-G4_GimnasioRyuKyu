import { Component, OnInit } from '@angular/core';
import { Classes } from 'src/app/models/Classes';
import { Rooms } from 'src/app/models/Rooms';
import { Subscriptions } from 'src/app/models/Subscriptions';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  classesData: Classes[]=[];
  roomsData: Rooms[]=[];
  subsData:Subscriptions[]=[];
  constructor(public homeService:HomeService) { }

  ngOnInit(): void {
    this.getClasses();
    this.getRooms();
    this.getSubs();
  }
  private getClasses(){
    this.homeService.getClasses().subscribe(
      res => {
        this.classesData = res.data;
      },
      error => console.log(error)
    )
  }

  private getRooms(){
    this.homeService.getRooms().subscribe(
      res => {
        this.roomsData = res.data;
      },
      error => console.log(error)
    )
  }

  private getSubs(){
    this.homeService.getSubs().subscribe(
      res => {
        this.subsData = res.data;
      },
      error => console.log(error)
    )
  }
}
