import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { NotificationService } from '../notification.service';
import { Marca } from './marca';

@Injectable({
  providedIn: 'root',
})
export class MarcaService {
  private urlEndPoint: string = 'http://localhost:8080/api/marcas';

  constructor(private http: HttpClient, private router: Router,
    private notifyService : NotificationService) {}

  getMarcas(): Observable<Marca[]> {
    return this.http
      .get(this.urlEndPoint)
      .pipe(map((marcas) => marcas as Marca[]));
  }

  getMarcas2(): Observable<Marca[]> {
    return this.http.get<Marca[]>(this.urlEndPoint);
  }

  // Registrar nuevo dato
  create(marca: Marca): Observable<any> {
    return this.http.post(this.urlEndPoint, marca).pipe(
      catchError((e) => {
        if (e.status == 400) {
          return throwError(e);
        }
        if (e.error.mensaje) {
          Swal.fire('Error',`Hola ${e.error.mensaje} `,'error');
          console.error(e.error.mensaje);
        }
        Swal.fire('Error',`Hola ${e.error.mensaje} `,'error');

        console.error(e.error.mensaje);
        return throwError(e);
      })
    );
  }

  //Captura dato especifico por ID
  getMarca(id: any): Observable<Marca> {
    return this.http.get<Marca>(`${this.urlEndPoint}/${id}`).pipe(
      catchError((e) => {
        if (e.status != 401 && e.error.mensaje) {
          this.notifyService.showError(`Error BD ${e.error.mensaje} `,"Error");
          this.router.navigate(['/marcas']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    );
  }

  //Update ficha urgencia
  update(marca: Marca): Observable<any> {
    return this.http.put<any>(`${this.urlEndPoint}/${marca.id}`, marca).pipe(
      catchError((e) => {
        if (e.status == 400) {
          return throwError(e);
        }
        if (e.error.mensaje) {
          Swal.fire('Error',`Error BD ${e.error.mensaje} `,'error');
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    );
  }

  //Elimina una ficha urgencias
  delete(id: any): Observable<Marca> {
    return this.http.delete<Marca>(`${this.urlEndPoint}/${id}`).pipe(
      catchError((e) => {
        if (e.error.mensaje) {
          Swal.fire('Error',`Error BD ${e.error.mensaje} `,'error');
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    );
  }
}
