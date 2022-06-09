import {
  DonutChartConfiguration,MultiBarChartConfiguration
} from "ontimize-web-ngx-charts";
import {
  Component
} from "@angular/core";
import { OntimizeService } from "ontimize-web-ngx";
import { ChangeDetectorRef, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: "home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],

})
export class HomeComponent {
  donutChartParameters: DonutChartConfiguration;
  incomeMultiBarChartParameters: MultiBarChartConfiguration;
  inscriptionMultiBarChartParameters: MultiBarChartConfiguration;

  constructor() {

    this.donutChartParameters = new DonutChartConfiguration();
    this.donutChartParameters.showLabels = false;
    this.donutChartParameters.cornerRadius = 15;
    this.donutChartParameters.legendPosition = "bottom";
    this.donutChartParameters.donutRatio = 0.5;
    this.donutChartParameters.color = ['#565656', '#e00000'];

    this.incomeMultiBarChartParameters = new MultiBarChartConfiguration();
    this.incomeMultiBarChartParameters.color = ['#e00000'];
    this.incomeMultiBarChartParameters.width= 300;
    this.incomeMultiBarChartParameters.height= 300;
    this.incomeMultiBarChartParameters.showLegend=false;
    this.incomeMultiBarChartParameters.showControls=false;
    this.incomeMultiBarChartParameters.showYAxis=true;

    this.inscriptionMultiBarChartParameters = new MultiBarChartConfiguration();
    this.inscriptionMultiBarChartParameters.color = ['#e00000'];
    this.inscriptionMultiBarChartParameters.showLegend=false;
    this.inscriptionMultiBarChartParameters.showControls=false;
    this.inscriptionMultiBarChartParameters.height= 300;
    this.inscriptionMultiBarChartParameters.width= 300;
  }

}
