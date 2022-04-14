
import {Injectable} from '@angular/core';
import {
  HttpEvent,HttpInterceptor,HttpHandler,HttpRequest}
  from '@angular/common/http';
  import {Observable,throwError} from 'rxjs';
import { AuthService } from '../auth.service';
import swal from 'sweetalert2';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';
import Swal from 'sweetalert2';


@Injectable()
export class AuthInterceptor implements HttpInterceptor{

  constructor(private authservice:AuthService,
  private router:Router){
  }



  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
catchError(e => {
  if(e.status == 401){

    if(this.authservice.isAuthenticated()){
      this.authservice.logout();
    }

    this.router.navigate(['/login']);
  }

  if(e.status==403){
    Swal.fire('Acceso denegado',`Hola ${this.authservice.usuario.username} no tienes acceso a este recurso`,'warning');
    this.router.navigate(['/urgencias']);
  }
  return throwError(e);

})


    );
  }

}
