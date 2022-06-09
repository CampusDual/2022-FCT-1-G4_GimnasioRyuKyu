import { ActivatedRoute, Router } from '@angular/router';
import { DiscreteBarChartConfiguration } from 'ontimize-web-ngx-charts';
import { ChangeDetectorRef, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { OntimizeService } from 'ontimize-web-ngx'


@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {
  accountAmount: any;
  public chartParameters: DiscreteBarChartConfiguration;
  protected graphData: Array<Object>;
  public subschartParameters: DiscreteBarChartConfiguration;
  protected subsgraphData: Array<Object>;
  public subsubschartParameters: DiscreteBarChartConfiguration;
  protected subsubsgraphData: Array<Object>;


  constructor(private router: Router,private actRoute: ActivatedRoute,private ontimizeService: OntimizeService,
    private cd: ChangeDetectorRef) {
    this.generateActivesData(ontimizeService,cd);
    this.generateSubsData(ontimizeService,cd);
    this.generateInscriptions(ontimizeService,cd);
  }

  generateActivesData(ontimizeService: OntimizeService,cd: ChangeDetectorRef){
    ontimizeService.configureService(ontimizeService.getDefaultServiceConfiguration('clients'));
    ontimizeService.query(void 0, ['id','id_subscription'], 'client').subscribe(
      res => {
        if (res && res.data.length && res.code === 0) {
          this.accountAmount = res.data.length;
          this.adaptResult(res.data);
        }
      },
      err => console.log(err),
      () => cd.detectChanges()
    );

    this.chartParameters = new DiscreteBarChartConfiguration();
    this.chartParameters.height = 300;
    this.chartParameters.width=500;
    this.chartParameters.showLegend = false;
    this.chartParameters.y1Axis.showMaxMin = false;
    this.chartParameters.x1Axis.showMaxMin = false;
  }
  adaptResult(data: any) {
    if (data && data.length) {
      let values = this.processValues(data);
      // chart data
      this.graphData = [
        {
          'key': 'Discrete serie',
          'values': values
        }
      ]
    }
  }
  processValues(data: any) {
    let values = [];
    let minorValue = 0;
    let majorValue = 0;
    data.forEach((item: any, index: number) => {
      if (item['id_subscription']!=null){
        majorValue++;
      }else{
        minorValue++;
      }
    });

    let lowerCrit = {
      'x': 'inactive',
      'y': minorValue
    }

    let upperCrit = {
      'x': 'active',
      'y': majorValue
    }

    values.push(lowerCrit);
    values.push(upperCrit);
    return values;
  }

  generateSubsData(ontimizeService: OntimizeService,cd: ChangeDetectorRef){
    ontimizeService.configureService(ontimizeService.getDefaultServiceConfiguration('clients'));
    ontimizeService.query(void 0, ['shipping_month','total_incomes'], 'monthIncome').subscribe(
      res => {
        if (res && res.data.length && res.code === 0) {
          this.adaptSubsResult(res.data);
        }
      },
      err => console.log(err),
      () => cd.detectChanges()
    );



    this.subschartParameters = new DiscreteBarChartConfiguration();
    this.subschartParameters.height = 300;
    this.subschartParameters.width = 400;

    this.subschartParameters.showLegend = false;
    this.subschartParameters.y1Axis.showMaxMin = false;
    this.subschartParameters.x1Axis.showMaxMin = false;
  }

  adaptSubsResult(data: any) {
    if (data && data.length) {
      let values = this.processSubsValues(data);
      // chart data
      this.subsgraphData = [
        {
          'key': 'Discrete serie',
          'values': values
        }
      ]
    }
  }
  processSubsValues(data: any) {
    let values = [];
    data.forEach((item: any, index: number) => {
      let newLet = {
        'x': item['month'],
        'y': item['total_incomes']
      }
      values.push(newLet);
    });
    return values;
  }


  generateInscriptions(ontimizeService: OntimizeService,cd: ChangeDetectorRef){
    ontimizeService.configureService(ontimizeService.getDefaultServiceConfiguration('clients'));
    ontimizeService.query(void 0, ['month','total_inscriptions'], 'monthInscriptions').subscribe(
      res => {
        if (res && res.data.length && res.code === 0) {
          this.adaptInscriptionsResult(res.data);
        }
      },
      err => console.log(err),
      () => cd.detectChanges()
    );



    this.subsubschartParameters = new DiscreteBarChartConfiguration();
    this.subsubschartParameters.height = 300;
    this.subschartParameters.width = 400;

    this.subsubschartParameters.showLegend = false;
    this.subsubschartParameters.y1Axis.showMaxMin = false;
    this.subsubschartParameters.x1Axis.showMaxMin = false;
  }

  adaptInscriptionsResult(data: any) {
    if (data && data.length) {
      let values = this.processInscriptionsValues(data);
      // chart data
      this.subsubsgraphData = [
        {
          'key': 'Discrete serie',
          'values': values

        }
      ]
    }
  }
  processInscriptionsValues(data: any) {
    let values = [];
    data.forEach((item: any, index: number) => {
      let newLet = {
        'x': item['month'],
        'y': item['total_inscriptions']
      }
      values.push(newLet);
    });
    return values;
  }

  ngOnInit() {
    // nothing to do
  }



  navigate() {
    this.router.navigate(['../', 'login'], { relativeTo: this.actRoute });
  }

}
