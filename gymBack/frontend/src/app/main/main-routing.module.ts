import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'ontimize-web-ngx';

import { MainComponent } from './main.component';

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [AuthGuardService],
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
      { path: 'clients', loadChildren: () => import('./clients/clients.module').then(m => m.ClientsModule) },
      { path: 'monitors', loadChildren: () => import('./monitors/monitors.module').then(m => m.MonitorsModule) },
      { path: 'classes', loadChildren: () => import('./classes/classes.module').then(m => m.ClassesModule) },
      { path: 'rooms', loadChildren: () => import('./rooms/rooms.module').then(m => m.RoomsModule) },
      { path: 'subscriptions', loadChildren: () => import('./subscriptions/subscriptions.module').then(m => m.SubscriptionsModule) },
      { path: 'assign-classes', loadChildren: () => import('./assign-classes/assign-classes.module').then(m => m.AssignClassesModule) },
      { path: 'assign-clients', loadChildren: () => import('./assign-clients/assign-clients.module').then(m => m.AssignClientsModule) },
      { path: 'calendar', loadChildren: () => import('./calendar/calendar.module').then(m => m.CalendarModule2) }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
