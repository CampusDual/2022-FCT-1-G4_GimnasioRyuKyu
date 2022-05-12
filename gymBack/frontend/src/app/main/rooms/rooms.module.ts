import { OntimizeWebModule } from 'ontimize-web-ngx';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoomsRoutingModule } from './rooms-routing.module';
import { RoomsHomeComponent } from './rooms-home/rooms-home.component';


@NgModule({
  declarations: [RoomsHomeComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    RoomsRoutingModule
  ]
})
export class RoomsModule { }
