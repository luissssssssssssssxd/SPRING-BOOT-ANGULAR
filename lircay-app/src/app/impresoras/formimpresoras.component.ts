import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Marca } from '../marcas/marca';
import { Modelos } from '../marcas/modelos';
import { ImpresorasService } from './impresoras.service';

@Component({
  selector: 'app-formimpresoras',
  templateUrl: './formimpresoras.component.html',
  styleUrls: ['./formimpresoras.component.css']
})




export class FormimpresorasComponent implements OnInit {
  marcas: Marca[];
  marcaElegida: Marca = null;
  modelos: Modelos[] = [];

  constructor(
    private marcaservice: ImpresorasService,
  ) { }

  ngOnInit(): void {
    this.cargardatos();
  }

  cargardatos(){
    Swal.fire({
      title: 'Cargando datos',

    });
    Swal.showLoading();
    this.marcaservice.getMarcas2().subscribe((marcas) => {
      (this.marcas = marcas),
      Swal.close();
    });
  }

}
