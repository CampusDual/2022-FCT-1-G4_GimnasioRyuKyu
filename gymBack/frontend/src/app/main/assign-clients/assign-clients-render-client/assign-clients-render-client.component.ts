import { OComboCustomRenderer } from 'ontimize-web-ngx';
import { Component, OnInit, ViewChild, TemplateRef, Injector } from '@angular/core';

@Component({
  selector: 'app-assign-clients-render-client',
  templateUrl: './assign-clients-render-client.component.html',
  styleUrls: ['./assign-clients-render-client.component.css']
})
export class AssignClientsRenderClientComponent extends OComboCustomRenderer {

  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;
  constructor(protected injector: Injector) {
    super(injector);
    }

    getComboData(value: any): string{
      return value.name+" "+value.lastname+" - "+value.dni;
  }

}
