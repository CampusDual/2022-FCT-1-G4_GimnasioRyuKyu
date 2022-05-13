import { OntimizeWebModule } from 'ontimize-web-ngx';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoomsRoutingModule } from './rooms-routing.module';
import { RoomsHomeComponent } from './rooms-home/rooms-home.component';
import { RoomsDetailComponent } from './rooms-detail/rooms-detail.component';


@NgModule({
  declarations: [RoomsHomeComponent, RoomsDetailComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    RoomsRoutingModule
  ]
})
export class RoomsModule { }
