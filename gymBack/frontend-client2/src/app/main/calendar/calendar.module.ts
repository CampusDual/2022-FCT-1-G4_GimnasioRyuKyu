import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';

import { OntimizeWebModule } from 'ontimize-web-ngx';

import { CalendarRoutingModule } from './calendar-routing.module';
import { CalendarComponent } from './calendar.component';




@NgModule({

  imports: [
    CommonModule,
    OntimizeWebModule,
    CalendarRoutingModule
  ],

  declarations: [
    CalendarComponent
  ]
})
export class CalendarModule { }
