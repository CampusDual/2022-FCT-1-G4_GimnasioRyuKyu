import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientsHomeComponent } from './clients-home/clients-home.component';


const routes: Routes =  [{
  path : '',
  component: ClientsHomeComponent
}];




@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientsRoutingModule { }
