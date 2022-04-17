import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import { UrgenciasComponent } from './urgencias/urgencias.component';
import { HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormComponent } from './urgencias/form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './usuarios/login.component';
import { AuthGuard } from './usuarios/guards/auth.guard';
import { RoleGuard } from './usuarios/guards/role.guard';
import {TokenInterceptor} from './usuarios/interceptors/token.interceptor';
import { UrgenciasService } from './urgencias/urgencias.service';
import { AuthInterceptor } from './usuarios/interceptors/auth.interceptor';
import { FilterPipe } from './filter.pipe';
import {HttpClientModule} from '@angular/common/http';
import { AreaComponent } from './areas/area.component';
import { FormareaComponent } from './areas/formarea.component';
import { MarcaComponent } from './marcas/marca.component';
import { FormmarcaComponent } from './marcas/formmarca.component';
import { NgProgressModule } from "ngx-progressbar";
import { NgProgressHttpModule } from "ngx-progressbar/http";
import { MaterialModule } from './material.module';
import { FormestadosComponent } from './estados/formestados.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table'
import { NotifierModule, NotifierOptions } from 'angular-notifier';
import { ToastrModule } from 'ngx-toastr';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

const routes:Routes =[
  {path:'',redirectTo:'/urgencias',pathMatch:'full'},
  {path:'urgencias',component:UrgenciasComponent},
  {path:'areas',component:AreaComponent},
  {path:'marcas',component:MarcaComponent},
  {path:'marcas/form',component:FormmarcaComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_USER'}},
  {path:'marcas/form/:id',component:FormmarcaComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_ADMIN'}},
  {path:'urgencias/form',component:FormComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_USER'}},
  {path:'urgencias/form/:id',component:FormComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_ADMIN'}},
  {path:'login',component:LoginComponent},
  {path:'areas/form',component:FormareaComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_USER'}},
  {path:'areas/form/:id',component:FormareaComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_ADMIN'}},
  {path:'estados/form',component:FormestadosComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_USER'}},
  {path:'estados/form/:id',component:FormestadosComponent,canActivate:[AuthGuard,RoleGuard],data:{role:'ROLE_ADMIN'}},


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
    FormareaComponent,
    MarcaComponent,
    FormmarcaComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatTableModule,
    MatInputModule,
    NotifierModule,
    RouterModule.forRoot(routes),
    [NgxMaterialTimepickerModule],
     BrowserAnimationsModule,
     NgProgressModule.withConfig({
      spinnerPosition: "left",
      color: "white"
    }),
    NgProgressHttpModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
      progressBar:true,
      progressAnimation:'decreasing'
    }),

  ],
  providers: [UrgenciasService,
    {provide:LOCALE_ID,useValue:'es'},
    {provide: HTTP_INTERCEPTORS,useClass:TokenInterceptor,multi:true},
      {provide: HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true}],

  bootstrap: [AppComponent]
})
export class AppModule { }
