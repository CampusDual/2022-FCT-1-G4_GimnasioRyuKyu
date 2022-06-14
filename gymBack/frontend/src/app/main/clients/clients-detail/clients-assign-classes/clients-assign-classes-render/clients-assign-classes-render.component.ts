import { ViewChild, TemplateRef, Injector, Component } from '@angular/core';
import { OComboCustomRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-clients-assign-classes-render',
  templateUrl: './clients-assign-classes-render.component.html',
  styleUrls: ['./clients-assign-classes-render.component.css']
})
export class ClientsAssignClassesRenderComponent extends OComboCustomRenderer {

  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  constructor(protected injector: Injector) {
    super(injector);
  }

  getComboData(value: any) {
    const date = new Date(value.date).toLocaleDateString("es-EU");
    return value.class_name+" - "+date+" - "+value.h_start;
  }

  ngOnInit() {
  }

}
