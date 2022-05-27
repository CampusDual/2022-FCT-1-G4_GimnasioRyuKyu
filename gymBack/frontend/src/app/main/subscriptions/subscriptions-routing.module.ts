import { SubscriptionsHomeComponent } from './subscriptions-home/subscriptions-home.component';
import { SubscriptionsDetailComponent } from './subscriptions-detail/subscriptions-detail.component';
import { SubscriptionsNewComponent } from './subscriptions-new/subscriptions-new.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes =  [{
  path : '',
  component: SubscriptionsHomeComponent
},
{
  path : 'new',
  component: SubscriptionsNewComponent
},
{
  path: ":id",
  component: SubscriptionsDetailComponent
}];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubscriptionsRoutingModule { }
