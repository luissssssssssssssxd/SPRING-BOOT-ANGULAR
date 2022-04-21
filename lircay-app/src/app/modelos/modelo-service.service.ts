import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { Modelos } from './modelos';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ModeloServiceService {

  private urlEndPoint:string ='http://localhost:8080/api/modelos';

  constructor(
    private http:HttpClient,
    private router:Router
  ) { }

  getModelos():Observable<Modelos[]>{
    return this.http.get<Modelos[]>(this.urlEndPoint);
  }

  //Registrar nuevo dato
create(modelo:Modelos): Observable<any>{
  return this.http.post(this.urlEndPoint,modelo).pipe(
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
getModelo(id: any): Observable<Modelos>{
  return this.http.get<Modelos>(`${this.urlEndPoint}/${id}`).pipe(
    catchError(e => {
      if(e.status !=401 && e.error.mensaje){
        this.router.navigate(['/modelos']);
        console.error(e.error.mensaje);
      }
      return throwError(e);
    }));

}

//Update modelo
update(modelo:Modelos):Observable<any>{
  return this.http.put<any>(`${this.urlEndPoint}/${modelo.id}`,modelo).pipe(
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
delete(id:any):Observable<Modelos>{
  return this.http.delete<Modelos>(`${this.urlEndPoint}/${id}`).pipe(
    catchError(e => {


      if(e.error.mensaje){
        console.error(e.error.mensaje);
      }
      return throwError(e);
    })
  )
}

}
