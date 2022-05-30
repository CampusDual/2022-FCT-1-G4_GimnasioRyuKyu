import { OComboCustomRenderer } from 'ontimize-web-ngx';
import { Component, TemplateRef, Injector, ViewChild } from '@angular/core';

@Component({
  selector: 'app-clients-sub-render',
  templateUrl: './clients-sub-render.component.html',
  styleUrls: ['./clients-sub-render.component.css']
})
export class ClientsSubRenderComponent extends OComboCustomRenderer {

  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  constructor(protected injector: Injector) {
    super(injector);
    }

  getComboData(value: any) {
    return value.sub_months+" - "+value.price+" €";
  }

}
