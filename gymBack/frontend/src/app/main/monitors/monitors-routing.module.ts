import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MonitorsDetailComponent } from './monitors-detail/monitors-detail.component';
import { MonitorsHomeComponent } from './monitors-home/monitors-home.component';
import { MonitorsNewComponent } from './monitors-new/monitors-new.component';

const routes: Routes = [{
  path : '',
  component: MonitorsHomeComponent
},
{
  path : "new",
  component: MonitorsNewComponent
},
{
  path: ":id",
  component: MonitorsDetailComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MonitorsRoutingModule { }
