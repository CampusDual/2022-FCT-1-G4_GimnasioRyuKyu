import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CalendarClientsRoutingModule } from './calendar-clients-routing.module';
import { CalendarClientsHomeComponent } from './calendar-clients-home/calendar-clients-home.component';

import { OntimizeWebModule } from 'ontimize-web-ngx';

import { CalendarModule, DateAdapter } from 'angular-calendar';
import { DemoUtilsModule } from './demo-utils/module';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

@NgModule({
  declarations: [CalendarClientsHomeComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    DemoUtilsModule,
    CalendarClientsRoutingModule
  ]
})
export class CalendarClientsModule { }
