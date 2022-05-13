import { OntimizeWebModule } from 'ontimize-web-ngx';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClassesRoutingModule } from './classes-routing.module';
import { ClassesHomeComponent } from './classes-home/classes-home.component';
import { ClassesDetailComponent } from './classes-detail/classes-detail.component';
import { ClassesNewComponent } from './classes-new/classes-new.component';


@NgModule({
  declarations: [ClassesHomeComponent, ClassesDetailComponent, ClassesNewComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ClassesRoutingModule
  ]
})
export class ClassesModule { }
