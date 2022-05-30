import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';
// import { AssignClientsDetailComponent } from '../main/assign-clients/assign-clients-detail/assign-clients-detail.component';

@NgModule({
  imports: [
    OntimizeWebModule,
    // AssignClientsDetailComponent
  ],
  declarations: [
  ],
  exports: [
    CommonModule,
    // AssignClientsDetailComponent
  ]
})
export class SharedModule { }
