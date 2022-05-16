import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AssignClassesHomeComponent } from './assign-classes-home/assign-classes-home.component';


const routes: Routes =  [{
  path : '',
  component: AssignClassesHomeComponent
},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssignClassesRoutingModule { }
