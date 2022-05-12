import { OntimizeWebModule } from 'ontimize-web-ngx';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClassesRoutingModule } from './classes-routing.module';
import { ClassesHomeComponent } from './classes-home/classes-home.component';


@NgModule({
  declarations: [ClassesHomeComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ClassesRoutingModule
  ]
})
export class ClassesModule { }
