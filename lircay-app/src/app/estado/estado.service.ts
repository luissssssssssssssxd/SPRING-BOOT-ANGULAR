import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { NotificationService } from '../notification.service';
import { Estado } from './estado';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {

  private urlEndPoint:string ='http://localhost:8080/api/estados';


  constructor( private http:HttpClient,
    private router:Router,
    private notifyService : NotificationService) { }

  getEstados():Observable<Estado[]>{
    return this.http.get<Estado[]>(this.urlEndPoint);
  }


  //registrar nuevo dato

  create(estado:Estado):Observable<any>{
    return this.http.post(this.urlEndPoint, estado).pipe(
      catchError(e=>{
        if(e.status == 400){
          return throwError(e);
        }else if(e.error.mensaje){
          Swal.fire('Error',`${e.error.mensaje} `,'error');
          console.error(e.error.mensaje);
        }
        Swal.fire('Error',` ${e.error.mensaje} `,'error');

        console.error(e.error.mensaje);
        return throwError(e);
      })
    )
  }

  //captura dato especifico por ID

  getEstado(id:any):Observable<Estado>{
    return this.http.get<Estado>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e=>{
        if(e.status != 401 && e.error.mensaje){
          this.notifyService.showError(`Error BD ${e.error.mensaje} `,"Error");
          this.router.navigate(['/estados']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }


  //actualizar estado


  update(estado:Estado):Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${estado.id}`,estado).pipe(
      catchError(e =>{
        if(e.status == 400){
          return throwError(e);
        }else if(e.error.mensaje){
          Swal.fire('Error',`Error BD ${e.error.mensaje} `,'error');
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }

  //eliminar estado

  delete(id:any):Observable<any>{
    return this.http.delete<Estado>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e=>{
        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }
}
