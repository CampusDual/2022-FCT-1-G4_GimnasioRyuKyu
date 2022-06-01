import { OBaseTableCellRenderer } from 'ontimize-web-ngx';
import { ViewChild, TemplateRef, Injector,Component} from '@angular/core';

@Component({
  selector: 'app-assing-classes-render',
  templateUrl: './assing-classes-render.component.html',
  styleUrls: ['./assing-classes-render.component.css']
})
export class AssingClassesRenderComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  constructor(protected injector: Injector) {
    super(injector);
    }

  getCellData(rowvalue?: any): string{
    return  rowvalue["date_sart"]+" "+rowvalue["hour_start"];
  }


}
