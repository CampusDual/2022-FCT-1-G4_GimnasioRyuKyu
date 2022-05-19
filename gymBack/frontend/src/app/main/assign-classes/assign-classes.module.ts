
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { AssignClassesRoutingModule } from './assign-classes-routing.module';
import { AssignClassesHomeComponent } from './assign-classes-home/assign-classes-home.component';
import { AssignClassesNewComponent } from './assign-classes-new/assign-classes-new.component';
import { AssignClassesDetailComponent } from './assign-classes-detail/assign-classes-detail.component';


@NgModule({
  declarations: [AssignClassesHomeComponent, AssignClassesDetailComponent, AssignClassesNewComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    AssignClassesRoutingModule
  ]
})
export class AssignClassesModule { }


