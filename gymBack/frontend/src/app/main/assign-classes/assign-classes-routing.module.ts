import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AssignClassesDetailComponent } from './assign-classes-detail/assign-classes-detail.component';
import { AssignClassesHomeComponent } from './assign-classes-home/assign-classes-home.component';


const routes: Routes =  [{
  path : '',
  component: AssignClassesHomeComponent
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
