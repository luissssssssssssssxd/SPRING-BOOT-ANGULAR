import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import { UrgenciasComponent } from './urgencias/urgencias.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormComponent } from './urgencias/form.component';
import { FormsModule } from '@angular/forms';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './usuarios/login.component';
import { AuthGuard } from './usuarios/guards/auth.guard';
import { RoleGuard } from './usuarios/guards/role.guard';
import {TokenInterceptor} from './usuarios/interceptors/token.interceptor';
import { UrgenciasService } from './urgencias/urgencias.service';
import { AuthInterceptor } from './usuarios/interceptors/auth.interceptor';
import { FilterPipe } from './filter.pipe';
import { ChartsModule } from 'ng2-charts';
import { AreaComponent } from './areas/area/area.component';




const routes:Routes =[
  {path:'',redirectTo:'/urgencias',pathMatch:'full'},
  {path:'urgencias',component:UrgenciasComponent},
  {path:'urgencias/form',component:FormComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_USER'}},
  {path:'urgencias/form/:id',component:FormComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_ADMIN'}},
  {path:'login',component:LoginComponent},

];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    UrgenciasComponent,
    FormComponent,
    LoginComponent,
    FilterPipe,
    AreaComponent,



  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ChartsModule,
    RouterModule.forRoot(routes),
    [NgxMaterialTimepickerModule],
     BrowserAnimationsModule,

  ],
  providers: [UrgenciasService,
    {provide:LOCALE_ID,useValue:'es'},
    {provide: HTTP_INTERCEPTORS,useClass:TokenInterceptor,multi:true},
      {provide: HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true}],

  bootstrap: [AppComponent]
})
export class AppModule { }
