import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-monitors-detail',
  templateUrl: './monitors-detail.component.html',
  styleUrls: ['./monitors-detail.component.css']
})
export class MonitorsDetailComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  public genderArray = [{
    genderCode: "M",
    genderText: 'Male'
  }, {
    genderCode: "F",
    genderText: 'Female'
  }, {
    genderCode: "O",
    genderText: 'Other'
  }];
}
