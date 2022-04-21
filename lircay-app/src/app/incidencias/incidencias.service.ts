import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Impresora } from '../impresoras/impresora';
import { NotificationService } from '../notification.service';
import { Incidencia } from './incidencia';

@Injectable({
  providedIn: 'root'
})
export class IncidenciasService {

  private urlEndPoint: string = "http://localhost:8080/api/incidencias";


  constructor(
    private http: HttpClient,
    private router: Router,
    private notifyService: NotificationService
  ) { }

  getIncidencias(): Observable<Incidencia[]> {
    return this.http.get<Incidencia[]>(this.urlEndPoint);
  }

  getImpresoras(): Observable<Impresora[]> {
    return this.http.get<Impresora[]>(`${this.urlEndPoint}/${'incidencias-impresoras'}`);
  }


  //Registrar nuevo dato
  create(incidencia:Incidencia): Observable<any>{
    return this.http.post(this.urlEndPoint,incidencia).pipe(
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
        Swal.fire('Error',`La incidencia ya existe`,'error');

        return throwError(e);
      })
    );
  }

  //Captura dato especifico por id
  getIncidencia(id: any): Observable<any>{
    return this.http.get<any>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if(e.status !=401 && e.error.mensaje){
          this.router.navigate(['/incidencias']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }));
  }

  //Update incidencia
  update(incidencia:Incidencia):Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${incidencia.id}`,incidencia).pipe(
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

  //Elimina una incidencia
  delete(id:any):Observable<Incidencia>{
    return this.http.delete<Incidencia>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {


        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }



  





}
