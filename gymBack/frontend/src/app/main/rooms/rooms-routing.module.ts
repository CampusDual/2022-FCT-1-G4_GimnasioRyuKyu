import { RoomsDetailComponent } from './rooms-detail/rooms-detail.component';
import { RoomsHomeComponent } from './rooms-home/rooms-home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [{
  path : '',
  component: RoomsHomeComponent
},
{
  path: ":ID",
  component: RoomsDetailComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoomsRoutingModule { }
