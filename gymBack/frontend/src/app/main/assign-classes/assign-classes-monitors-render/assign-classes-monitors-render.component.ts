import { ViewChild, TemplateRef, Injector, Component } from '@angular/core';
  import { OComboCustomRenderer } from 'ontimize-web-ngx';
@Component({
  selector: 'app-assign-classes-monitors-render',
  templateUrl: './assign-classes-monitors-render.component.html',
  styleUrls: ['./assign-classes-monitors-render.component.css']
})
export class AssignClassesMonitorsRenderComponent extends OComboCustomRenderer {
  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;
  constructor(protected injector: Injector) {
    super(injector);
    }

    getComboData(value: any): string{
      return value.name+", "+value.lastname+", "+value.id;
  }

}
