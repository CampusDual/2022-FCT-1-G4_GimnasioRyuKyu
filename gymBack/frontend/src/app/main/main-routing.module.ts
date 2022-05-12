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
      { path: 'classes', loadChildren: () => import('./classes/classes.module').then(m => m.ClassesModule) },
      { path: 'rooms', loadChildren: () => import('./rooms/rooms.module').then(m => m.RoomsModule) }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
