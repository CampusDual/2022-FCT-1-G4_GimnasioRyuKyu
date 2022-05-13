import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientsDetailComponent } from './clients-detail/clients-detail.component';
import { ClientsHomeComponent } from './clients-home/clients-home.component';


const routes: Routes =  [{
  path : '',
  component: ClientsHomeComponent
},
{
  path: ":ID",
  component: ClientsDetailComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientsRoutingModule { }
