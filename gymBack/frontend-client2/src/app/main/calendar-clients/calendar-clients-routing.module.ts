import { CalendarClientsHomeComponent } from './calendar-clients-home/calendar-clients-home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path : '',
    component: CalendarClientsHomeComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CalendarClientsRoutingModule { }
