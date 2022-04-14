import { Component } from "@angular/core";
import { AuthService } from "../usuarios/auth.service";
import {Router} from '@angular/router';
import swal from 'sweetalert2';
import Swal from "sweetalert2";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent{
  title:string ='';


  constructor(public authservice:AuthService,
    public router:Router){

  }
  logout():void{
    Swal.fire('Login',`Hola ${this.authservice.usuario.username} ha cerrado sesion exitosamente`,'success');
    this.authservice.logout();
    this.router.navigate(['/login']);

  }


}
