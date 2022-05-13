import { ClassesDetailComponent } from './classes-detail/classes-detail.component';
import { ClassesNewComponent } from './classes-new/classes-new.component';
import { ClassesHomeComponent } from './classes-home/classes-home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [{
  path : '',
  component: ClassesHomeComponent
},
{
  path: "new",
  component: ClassesNewComponent
},
{
  path: ":id",
  component: ClassesDetailComponent
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClassesRoutingModule { }
