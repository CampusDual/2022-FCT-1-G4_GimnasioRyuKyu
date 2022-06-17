import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PricingComponent } from './components/pricing/pricing.component';

const routes: Routes = [
{ path: 'login', component: LoginComponent, pathMatch: "full" },
{ path: 'pricing', component: PricingComponent },
{ path: 'home', component: HomeComponent },
{path:'',component:HomeComponent}
];

@NgModule({
imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { };
export const routing = RouterModule.forRoot(routes);
