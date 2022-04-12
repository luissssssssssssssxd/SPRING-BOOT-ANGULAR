import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authservice:AuthService,
  private router:Router){

  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.authservice.isAuthenticated()){
        if(this.isTokenExpirado()){
          this.authservice.logout();
          return false;
        }
        return true;
      }
this.router.navigate(['/login']);
    return false;
  }

  isTokenExpirado():boolean{
    let token = this.authservice.token;
    let payload  = this.authservice.obtenerDatosToken(token);
    let now = new Date().getTime() /1000;
    if(payload.exp < now){
      return true;
    }
    return false;
  }

}
