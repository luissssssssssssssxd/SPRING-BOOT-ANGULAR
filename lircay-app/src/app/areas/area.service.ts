import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Area } from './area';

@Injectable({
  providedIn: 'root'
})
export class AreaService {

  private urlEndPoint:string ='http://localhost:8080/api/areas';


  constructor(private http:HttpClient,
    private router:Router) { }


    getAreas():Observable<Area[]>{
      return this.http.get<Area[]>(this.urlEndPoint);
    }

    //Registrar nuevo dato
  create(area:Area): Observable<any>{
    return this.http.post(this.urlEndPoint,area).pipe(
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

//Captura dato especifico por ID
  getArea(id: any): Observable<Area>{
    return this.http.get<Area>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if(e.status !=401 && e.error.mensaje){
          this.router.navigate(['/areas']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }));

  }

//Update ficha urgencia
  update(area:Area):Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${area.id}`,area).pipe(
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

  //Elimina una ficha urgencias
  delete(id:any):Observable<Area>{
    return this.http.delete<Area>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {


        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }
}
