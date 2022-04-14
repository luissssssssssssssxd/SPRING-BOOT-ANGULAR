import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import swal from 'sweetalert2';
import Swal from 'sweetalert2';


@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private authservice:AuthService,
  private router:Router){

  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(!this.authservice.isAuthenticated()){
        this.router.navigate(['/login']);
        return false;
      }
      let role = next.data['role'] as string;
      console.log(role);
      if(this.authservice.hasRole(role)){
        return true;
      }
      Swal.fire('Acceso denegado',`Hola ${this.authservice.usuario.username} no tienes acceso a este recurso`,'warning');
      this.router.navigate(['/urgencias']);
    return false;
  }

}
