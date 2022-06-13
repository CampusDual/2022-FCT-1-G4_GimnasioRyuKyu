import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ClientsAssignClassesComponent } from './clients-assign-classes/clients-assign-classes.component';

@Component({
  selector: 'app-clients-detail',
  templateUrl: './clients-detail.component.html',
  styleUrls: ['./clients-detail.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class ClientsDetailComponent implements OnInit {

  constructor(
    protected dialog: MatDialog
  ) { }

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

  public openDetail(): void {
    this.dialog.open(ClientsAssignClassesComponent, {
      height: '330px',
      width: '520px'
    });
  }

}
