import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { AssignClientsRoutingModule } from './assign-clients-routing.module';
import { AssignClientsHomeComponent } from './assign-clients-home/assign-clients-home.component';
import { AssignClientsDetailComponent } from './assign-clients-detail/assign-clients-detail.component';
import { AssignClientsNewComponent } from './assign-clients-new/assign-clients-new.component';
import { AssignClientsRenderComponent } from './assign-clients-detail/assign-clients-render/assign-clients-render.component';


@NgModule({
  declarations: [AssignClientsHomeComponent, AssignClientsDetailComponent, AssignClientsNewComponent, AssignClientsRenderComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    AssignClientsRoutingModule
  ]
})
export class AssignClientsModule { }
