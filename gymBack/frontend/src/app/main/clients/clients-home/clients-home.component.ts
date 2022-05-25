import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clients-home',
  templateUrl: './clients-home.component.html',
  styleUrls: ['./clients-home.component.css']
})
export class ClientsHomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  public translateArgsFn(rowData: any): any[] {
    return [rowData.active];
  }

}
