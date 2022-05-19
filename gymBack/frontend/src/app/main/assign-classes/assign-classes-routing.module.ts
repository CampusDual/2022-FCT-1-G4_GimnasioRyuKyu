import { AssignClassesDetailComponent } from './assign-classes-detail/assign-classes-detail.component';
import { AssignClassesNewComponent } from './assign-classes-new/assign-classes-new.component';
import { AssignClassesHomeComponent } from './assign-classes-home/assign-classes-home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes =  [{
  path : '',
  component: AssignClassesHomeComponent
},
{
  path: "new",
  component: AssignClassesNewComponent
},
{
  path: ":id_room_class",
  component: AssignClassesDetailComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssignClassesRoutingModule { }
