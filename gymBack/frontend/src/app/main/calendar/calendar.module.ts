import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CalendarRoutingModule } from './calendar-routing.module';
import { CalendarHomeComponent } from './calendar-home/calendar-home.component';

import { CalendarModule, DateAdapter } from 'angular-calendar';
import { DemoUtilsModule } from './demo-utils/module';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';


@NgModule({
  declarations: [CalendarHomeComponent],
  imports: [
    CommonModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    DemoUtilsModule,
    CalendarRoutingModule
  ]
})
export class CalendarModule2 { }
