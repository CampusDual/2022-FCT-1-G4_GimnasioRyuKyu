import { ViewChild, TemplateRef, Injector, Component } from '@angular/core';
import * as moment from 'moment';
import { OComboCustomRenderer  } from 'ontimize-web-ngx';

@Component({
  selector: 'app-assign-clients-render',
  templateUrl: './assign-clients-render.component.html',
  styleUrls: ['./assign-clients-render.component.css']
})
export class AssignClientsRenderComponent extends OComboCustomRenderer  {

  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  constructor(protected injector: Injector) {
    super(injector);
    }

  getComboData(value: any) {
    let theDate = moment.unix(value.h_start).format("DD/MM h:mm:ss a");
    return value.class_name+" "+theDate;
  }

}
