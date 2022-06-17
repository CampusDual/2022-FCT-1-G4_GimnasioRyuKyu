import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';

import { OntimizeWebModule } from 'ontimize-web-ngx';

import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile.component';




@NgModule({

  imports: [

    CommonModule,

    OntimizeWebModule,

    ProfileRoutingModule

  ],

  declarations: [

    ProfileComponent

  ]

})

export class ProfileModule { }
