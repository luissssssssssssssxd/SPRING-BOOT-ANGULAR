import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'jquery';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NotificationService } from '../notification.service';
import { Impresora } from './impresora';

@Injectable({
  providedIn: 'root'
})
export class ImpresorasService {

  private urlEndPoint: string = "http://localhost:8080/api/impresoras";

  constructor(
    private http: HttpClient,
    private router: Router,
    private notifyService: NotificationService
  ) { }

  getImpresoras(): Observable<Impresora[]> {
    return this.http.get<Impresora[]>(this.urlEndPoint);
  }

  //Registrar nuevo dato
  create(impresora:Impresora): Observable<any>{
    return this.http.post(this.urlEndPoint,impresora).pipe(
      catchError(e => {

        if(e.status ==400){
          return throwError(e);
        }
        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }

        console.error(e.error.mensaje);
        return throwError(e);
      })
    );
  }

  //Captura dato especifico por numero de serie
  getArea(numeroserie: any): Observable<Impresora>{
    return this.http.get<Impresora>(`${this.urlEndPoint}/${numeroserie}`).pipe(
      catchError(e => {
        if(e.status !=401 && e.error.mensaje){
          this.router.navigate(['/impresoras']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }));
  }

  //Update impresora
  update(impresora:Impresora):Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${impresora.numeroserie}`,impresora).pipe(
      catchError(e => {


        if(e.status ==400){
          return throwError(e);
        }
        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }

  //Elimina una area
  delete(numeroserie:any):Observable<Impresora>{
    return this.http.delete<Impresora>(`${this.urlEndPoint}/${numeroserie}`).pipe(
      catchError(e => {


        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }







  
}

