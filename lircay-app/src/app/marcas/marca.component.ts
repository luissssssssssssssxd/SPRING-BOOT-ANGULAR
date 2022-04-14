import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';
import { Marca } from './marca';
import { MarcaService } from './marca.service';
import { Modelos } from './modelos';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';



@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css'],
})
export class MarcaComponent implements OnInit {



  displayedColumns: string[] = ['action','ID', 'Nombre',];

  public dataSource: MatTableDataSource<Marca>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  private dataArray: any;

  marcas: Marca[];
  marcaElegida: Marca = null;
  modelos: Modelos[] = [];
  NProgress: any;

  filterpost = '';

  constructor(
    private marcaservice: MarcaService,
    public authservice: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
       Swal.fire({
      title: 'Cargando datos',
    });
    Swal.showLoading();
    this.marcaservice.getMarcas().subscribe((marcas) => {
      (this.marcas = marcas), console.log(this.marcas);
      this.dataArray = marcas;
        this.dataSource = new MatTableDataSource<Marca>(this.dataArray);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      Swal.close();
      console.log(marcas);
    });


  }

  delete(marca: Marca): void {
    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar la ficha de:  ${marca.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,eliminar!',
      cancelButtonText: 'No, cancelar',
    }).then((result) => {
      if (result.value) {
        this.marcaservice.delete(marca.id).subscribe((response) => {
          this.marcas = this.marcas.filter((cli) => cli !== marca);
          Swal.fire(
            'Ficha Eliminado!',
            `Paciente ${marca.nombre} eliminado con éxito.`,
            'success'
          );
        });
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled', 'Your imaginary file is safe :)', 'error');
      }
    });
  }
}
