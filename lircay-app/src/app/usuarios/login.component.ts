import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import swal from 'sweetalert2';
import Swal from 'sweetalert2';

import {AuthService} from './auth.service';
import {Router} from  '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'

})
export class LoginComponent implements OnInit {


  titulo:string = "Por favor Inicia Sesion!";

  usuario:Usuario;

  constructor(private autoService:AuthService,private router:Router) {
  this.usuario = new Usuario();
  }

  ngOnInit(): void {
    if(this.autoService.isAuthenticated()){
/*       swal('Login',`Hola ${this.autoService.usuario.username} ya estas autenticado`,'info');
 */      this.router.navigate(['/urgencias']);
    }
  }

  login():void{
    console.log(this.usuario);

    if(this.usuario.username == null || this.usuario.password == null){
      Swal.fire({ toast: true, position: 'top-end', showConfirmButton: false, timer: 3000, title: 'Error Login', text: 'Usuario o contraseña vacias¡', icon: 'error', });
/*       swal('Error Login','Usuario o Contraseña vacias!','error')
 */      return;
    }

    this.autoService.login(this.usuario).subscribe(respuesta => {
      console.log(respuesta);
      this.autoService.guardarUsuario(respuesta.access_token);
      this.autoService.guardarToken(respuesta.access_token);
      let usuario = this.autoService.usuario.username;


      this.router.navigate(['/urgencias']);
/*       swal('Login',`Hola ${usuario},has iniciado sesion con exito!`,'success')
 */    },err =>{
      if(err.status == 400){
/*         swal('Error Login','Usuario o Contraseña incorrecta!','error')
 */
      }
    }



  );

  }

}
