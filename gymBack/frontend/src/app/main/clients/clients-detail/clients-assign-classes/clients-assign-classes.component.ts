import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-clients-assign-classes',
  templateUrl: './clients-assign-classes.component.html',
  styleUrls: ['./clients-assign-classes.component.css']
})
export class ClientsAssignClassesComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
  }

}
