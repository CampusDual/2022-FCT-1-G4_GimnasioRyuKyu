import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { ClientsRoutingModule } from './clients-routing.module';
import { ClientsHomeComponent } from './clients-home/clients-home.component';


@NgModule({
  declarations: [ClientsHomeComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ClientsRoutingModule
  ]
})
export class ClientsModule { }
