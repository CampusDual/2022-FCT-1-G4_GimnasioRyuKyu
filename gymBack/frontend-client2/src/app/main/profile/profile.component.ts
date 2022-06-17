import { Component, OnInit, ViewEncapsulation } from '@angular/core';



@Component({

  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
  encapsulation: ViewEncapsulation.None

})

export class ProfileComponent implements OnInit {

  constructor() { }

  ngOnInit() {}

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
