import { Component, OnInit } from '@angular/core';
import { Classes } from 'src/app/models/Classes';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  classesData: Classes [] = [];
  constructor(public homeService:HomeService) { }

  ngOnInit(): void {
    this.getClasses();
  }
  private getClasses(){
    this.homeService.getClasses().subscribe(
      res => {
        console.log(res)
        this.classesData = res;
      },
      error => console.log(error)
    )
  }
}
