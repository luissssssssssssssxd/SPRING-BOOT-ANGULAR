import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Incidencia } from '../incidencias/incidencia';
import { NotificationService } from '../notification.service';
import { obs_incidencia } from './obs_incidencia';

@Injectable({
  providedIn: 'root'
})
export class ObsIncidenciasService {

  private urlEndPoint: string = "http://localhost:8080/api/obs_incidencias"

  constructor(
    private http: HttpClient,
    private router: Router,
    private notifyService: NotificationService
  ) { }

  getObs_incidencias():Observable<obs_incidencia[]>{
    return this.http.get<obs_incidencia[]>(this.urlEndPoint);
  }

  getIncidencias(): Observable<Incidencia[]> {
    return this.http.get<Incidencia[]>(`${this.urlEndPoint}/${'obs_incidencias-incidencias'}`);
  }

  //Registrar nuevo dato
  create(obs_incidencia:obs_incidencia): Observable<any>{
    return this.http.post(this.urlEndPoint,obs_incidencia).pipe(
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
        Swal.fire('Error',`La obs ya existe`,'error');

        return throwError(e);
      })
    );
  }

  //Captura dato especifico por id
  getObs_incidencia(id: any): Observable<any>{
    return this.http.get<any>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if(e.status !=401 && e.error.mensaje){
          this.router.navigate(['/obs_incidencias']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }));
  }

  //Update obs_incidencia
  update(obs_incidencia:obs_incidencia):Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${obs_incidencia.id}`,obs_incidencia).pipe(
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
  delete(id:any):Observable<obs_incidencia>{
    return this.http.delete<obs_incidencia>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {


        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }


  
}
