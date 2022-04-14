import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Estado } from './estado';
import { EstadosService } from './estados.service';

@Component({
  selector: 'app-formestados',
  templateUrl: './formestados.component.html',
  styleUrls: ['./formestados.component.css']
})
export class FormestadosComponent implements OnInit {

  

  public estado:Estado = new Estado();

  public errores:string[];

  constructor(
    public estadoservice:EstadosService,
    public router:Router,
    public activatedroute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos():void{
    this.activatedroute.params.subscribe(params=>{
      let id=params['id']
      if(id){
        this.estadoservice.getEstado(id).subscribe((estado)=>this.estado = estado)
      }
    })
  }


  public create():void{
    this.estadoservice.create(this.estado).subscribe(json=>{
      this.router.navigate(['/estados']);
      Swal.fire({toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        title:'Registro exitoso!',
        text:`El estado: ${json.estado.estadoimpresora}, fue creado con exito`,
        icon:'success'
      })
    })
    return;
  }

  update():void{
    this.estadoservice.update(this.estado)
    .subscribe(json =>{
      this.router.navigate(['/estados'])
      Swal.fire({toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        title:'Actualizacion exitosa!',
        text:`El estado: ${json.estado.estadoimpresora}, fue actualizado con exito`,
        icon:'success'
      })
    })
  }

}
