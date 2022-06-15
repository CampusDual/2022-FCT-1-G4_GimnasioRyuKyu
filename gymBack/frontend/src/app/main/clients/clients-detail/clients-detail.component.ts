import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-clients-detail',
  templateUrl: './clients-detail.component.html',
  styleUrls: ['./clients-detail.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class ClientsDetailComponent implements OnInit {

  constructor() { }

  ngOnInit() { }

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
