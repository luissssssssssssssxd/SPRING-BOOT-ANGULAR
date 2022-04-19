import { Component, OnInit } from '@angular/core';
import { Select2OptionData } from 'ng-select2';
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

  public exampleData: Array<Select2OptionData>;


  constructor(
    private marcaservice: ImpresorasService,
  ) { }

  ngOnInit(): void {
    this.cargardatos();
    this.exampleData = [
      {
        id: 'basic1',
        text: 'Basic 1'
      },
      {
        id: 'basic2',
        disabled: true,
        text: 'Basic 2'
      },
      {
        id: 'basic3',
        text: 'Basic 3'
      },
      {
        id: 'basic4',
        text: 'Basic 4'
      }
    ];
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
