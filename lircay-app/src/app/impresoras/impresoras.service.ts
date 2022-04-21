import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'jquery';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Area } from '../areas/area';
import { Estado } from '../estado/estado';
import { Marca } from '../marcas/marca';
import { Modelos } from '../modelos/modelos';
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

  getMarcas2(): Observable<any[]> {
    return this.http.get<any[]>(`${this.urlEndPoint}/${'impresoras-marcas'}`);
  }
  getEstados(): Observable<any[]> {
    return this.http.get<any[]>(`${this.urlEndPoint}/${'impresoras-estados'}`);
  }
  getModelos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.urlEndPoint}/${'impresoras-modelos'}`);
  }
  getAreas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.urlEndPoint}/${'impresoras-areas'}`);
  }

  //Registrar nuevo dato
  create(impresora:Impresora): Observable<any>{
    return this.http.post(this.urlEndPoint,impresora).pipe(
      catchError(e => {

        if(e.status ==400){
          Swal.fire('Error',`Error BD ${e.status}  `,'error');
          return throwError(e);
        }
        if(e.error.mensaje){
          console.error(e.error.mensaje);
          Swal.fire('Error',`Error BD ${e.error.mensaje}  `,'error');

        }
        console.error(e.error.mensaje);
        Swal.fire('Error',`La impresora ya está ingresada`,'error');

        return throwError(e);
      })
    );
  }

  //Captura dato especifico por id
  getImpresora(id: any): Observable<any>{
    return this.http.get<any>(`${this.urlEndPoint}/${id}`).pipe(
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
    return this.http.put<any>(`${this.urlEndPoint}/${impresora.id}`,impresora).pipe(
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

  //Elimina una impresora
  delete(id:any):Observable<Impresora>{
    return this.http.delete<Impresora>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {


        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }








}


