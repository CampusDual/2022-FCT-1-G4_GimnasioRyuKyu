import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientsAssignClassesComponent } from './clients-detail/clients-assign-classes/clients-assign-classes.component';
import { ClientsDetailComponent } from './clients-detail/clients-detail.component';
import { ClientsHomeComponent } from './clients-home/clients-home.component';
import { ClientsNewComponent } from './clients-new/clients-new.component';

const routes: Routes =  [{
  path : '',
  component: ClientsHomeComponent
},
{
  path : "new",
  component: ClientsNewComponent
},
{
  path: ":id",
  component: ClientsDetailComponent
},
{
  path:":id/new",
  component: ClientsAssignClassesComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientsRoutingModule { }
