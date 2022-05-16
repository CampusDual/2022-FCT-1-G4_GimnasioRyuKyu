import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { AssignClassesRoutingModule } from './assign-classes-routing.module';
import { AssignClassesDetailComponent } from './assign-classes-detail/assign-classes-detail.component';
import { AssignClassesHomeComponent } from './assign-classes-home/assign-classes-home.component';


@NgModule({
  declarations: [AssignClassesHomeComponent, AssignClassesDetailComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    AssignClassesRoutingModule
  ]
})
export class AssignClassesModule { }
