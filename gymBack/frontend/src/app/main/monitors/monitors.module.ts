import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { MonitorsRoutingModule } from './monitors-routing.module';
import { MonitorsHomeComponent } from './monitors-home/monitors-home.component';
import { MonitorsDetailComponent } from './monitors-detail/monitors-detail.component';
import { MonitorsNewComponent } from './monitors-new/monitors-new.component';


@NgModule({
  declarations: [MonitorsHomeComponent, MonitorsDetailComponent, MonitorsNewComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    MonitorsRoutingModule
  ]
})
export class MonitorsModule { }
