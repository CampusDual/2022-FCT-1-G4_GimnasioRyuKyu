import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { ClientsRoutingModule } from './clients-routing.module';
import { ClientsHomeComponent } from './clients-home/clients-home.component';
import { ClientsDetailComponent } from './clients-detail/clients-detail.component';
import { ClientsNewComponent } from './clients-new/clients-new.component';
import { ClientsSubRenderComponent } from './clients-detail/clients-sub-render/clients-sub-render.component';
import { ClientsListRenderComponent } from './clients-detail/clients-list-render/clients-list-render.component';
import { ClientsAssignClassesComponent } from './clients-detail/clients-assign-classes/clients-assign-classes.component';


@NgModule({
  declarations: [ClientsHomeComponent, ClientsDetailComponent, ClientsNewComponent, ClientsSubRenderComponent, ClientsListRenderComponent, ClientsAssignClassesComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ClientsRoutingModule
  ]
})
export class ClientsModule {

}
