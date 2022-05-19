import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AssignClientsDetailComponent } from './assign-clients-detail/assign-clients-detail.component';
import { AssignClientsHomeComponent } from './assign-clients-home/assign-clients-home.component';
import { AssignClientsNewComponent } from './assign-clients-new/assign-clients-new.component';


const routes: Routes = [{
  path : '',
  component: AssignClientsHomeComponent
},
{
path : 'new',
  component: AssignClientsNewComponent
},
{
  path: ":id_client_class",
  component: AssignClientsDetailComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssignClientsRoutingModule { }
