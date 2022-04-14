import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';
import { Estado } from './estado';
import { EstadosService } from './estados.service';

@Component({
  selector: 'app-estados',
  templateUrl: './estados.component.html',
})
export class EstadosComponent implements OnInit {

  constructor(
    public estadoservice:EstadosService,
    public authservice: AuthService,
    private http:HttpClient
  ) { }

  public estados:Estado[];


  filterpost='';

  ngOnInit(): void {
    this.estadoservice.getEstados().subscribe(
      estados => this.estados=estados,
    )
  }

  delete(estado:Estado):void{
    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar el estado:  ${estado.estadoimpresora}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar'
    }).then((result)=>{
      if(result.value){
        this.estadoservice.delete(estado.id).subscribe(
          response => {
            this.estados = this.estados.filter(cli => cli !== estado)
            Swal.fire({
              toast: true,
              position: 'top-end',
              showConfirmButton: false,
              timer: 3000,
              title:'Eliminacion exitosa!',
              text:`El estado: ${estado.estadoimpresora}, fue eliminado con exito`,
              icon:'success'
            })
          }
        )

      }else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000,
          title:'Cancelado!',
          text:`El estado: ${estado.estadoimpresora}, no se elimino.`,
          icon:'error'
        })
      }
    })

  }

}
