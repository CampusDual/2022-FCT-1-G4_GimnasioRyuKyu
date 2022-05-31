import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-monitors-new',
  templateUrl: './monitors-new.component.html',
  styleUrls: ['./monitors-new.component.css']
})
export class MonitorsNewComponent implements OnInit {

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
