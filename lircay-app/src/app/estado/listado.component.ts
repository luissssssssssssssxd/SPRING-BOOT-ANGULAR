import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { NotificationService } from '../notification.service';
import { AuthService } from '../usuarios/auth.service';
import { Estado } from './estado';
import { EstadoService } from './estado.service';

@Component({
  selector: 'app-listado',
  templateUrl: './listado.component.html',
  styleUrls: ['./listado.component.css']
})
export class ListadoComponent implements OnInit {

  public estados:Estado[];

  //Columnas tabla
  displayedColumns: string[] = ['action', 'ID', 'Nombre',];

  //Datos
  public dataSource: MatTableDataSource<Estado>;


  //Paginador y ordenador de datos
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;


  //Array tabla
  private dataArray: any;


  //Filtro tabla
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }



  constructor(    private estadoservice: EstadoService,
    public authservice: AuthService,
    private http: HttpClient,
    private router: Router,
    private notifyService : NotificationService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.cargardatos();
  }



  cargardatos(){
    Swal.fire({
      title: 'Cargando datos',

    });
    Swal.showLoading();
    this.estadoservice.getEstados().subscribe((estados) => {
      (this.estados = estados),
       console.log(this.estados);
      this.dataArray = estados;
      this.dataSource = new MatTableDataSource<Estado>(this.dataArray);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      Swal.close();
      console.log(estados);
    });
  }

  delete(estado: Estado): void {
    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar el estado de:  ${estado.estadoimpresora}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,eliminar!',
      cancelButtonText: 'No, cancelar',
    }).then((result) => {
      if (result.value) {
        this.estadoservice.delete(estado.id).subscribe((response) => {
          this.estados = this.estados.filter((cli) => cli !== estado);
          this.cargardatos();
          this.notifyService.showError(`Estado  ${estado.estadoimpresora}  eliminada`,"OK");
        });

      }
       else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelado', 'Dato no eliminado :)', 'error');
      }
    });
  }

}
