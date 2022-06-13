import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-clients-assign-classes',
  templateUrl: './clients-assign-classes.component.html',
  styleUrls: ['./clients-assign-classes.component.css']
})
export class ClientsAssignClassesComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer
  ) { }

  ngOnInit() {
  }

}
