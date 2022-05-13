import { RoomsNewComponent } from './rooms-new/rooms-new.component';
import { RoomsDetailComponent } from './rooms-detail/rooms-detail.component';
import { RoomsHomeComponent } from './rooms-home/rooms-home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [{
  path : '',
  component: RoomsHomeComponent
},
{
  path : "new",
  component: RoomsNewComponent
},
{
  path: ":id",
  component: RoomsDetailComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoomsRoutingModule { }
