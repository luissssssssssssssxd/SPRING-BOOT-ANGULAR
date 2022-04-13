import { Component, OnInit } from '@angular/core';
import { Urgencia } from './urgencia';
import { UrgenciasService } from './urgencias.service';
import {Router,ActivatedRoute} from '@angular/router';
import swal from 'sweetalert2';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'

})
export class FormComponent implements OnInit {

  public  urgencia:Urgencia  = new Urgencia()

  public titulo:string="Crear Registro"

  public errores:string[];

  constructor(public urgenciaservice:UrgenciasService,
    public router:Router,
    public activatedroute:ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarDatos() //Carga datos para update
  }

  cargarDatos(): void{
     this.activatedroute.params.subscribe(params => {
       let id = params['id']
       if(id){
         this.urgenciaservice.getUrgencia(id).subscribe( (urgencia) => this.urgencia = urgencia)
       }
     })
   }


  public create():void{
  this.urgenciaservice.create(this.urgencia)
  .subscribe(json =>{
    this.router.navigate(['/urgencias'])
   /*  swal('Nuevo Registro',`Registro ${json.urgencia.nombrepaciente} creado con exito!`,'success'); */

  },
  err=> {
    this.errores = err.error.errors as string [];
    console.error('Codigo error backend' + err.status);
    console.error(err.error.errors);

  }
);
  }

  update():void{
    this.urgenciaservice.update(this.urgencia)
    .subscribe(json =>{
      this.router.navigate(['/urgencias'])
      /* swal('Registro Actualizado',`Registro ${json.urgencia.nombrepaciente} actualizado con exito`,'success') */
    },
    err=> {
      this.errores = err.error.errors as string [];
      console.error('Codigo error backend' + err.status);
      console.error(err.error.errors);

    }
  )}

}
