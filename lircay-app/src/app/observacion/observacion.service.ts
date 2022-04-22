import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NotificationService } from '../notification.service';

@Injectable({
  providedIn: 'root',
})
export class ObservacionService {
  private urlEndPoint: string = 'http://localhost:8080/api/obs_incidencias';

  constructor(
    private http: HttpClient,
    private router: Router,
    private notifyService: NotificationService
  ) {}



  //Captura dato especifico por id
  getObservacion(id: any): Observable<any>{
    return this.http.get<any>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if(e.status !=401 && e.error.mensaje){
          this.router.navigate(['/incidencias']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }));
  }
}
