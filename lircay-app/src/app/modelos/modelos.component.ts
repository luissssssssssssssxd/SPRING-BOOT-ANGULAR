import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Modelos } from './modelos';
import { ModeloServiceService } from './modelo-service.service';
import { AuthService } from '../usuarios/auth.service';
import { HttpClient } from '@angular/common/http';
import { NotificationService } from '../notification.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modelos',
  templateUrl: './modelos.component.html',
  styleUrls: ['./modelos.component.css']
})
export class ModelosComponent implements OnInit {
  displayedColumns: string[] = ['action', 'ID', 'Modelo',];

  public dataSource: MatTableDataSource<Modelos>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  private dataArray: any;

  modelos:Modelos[];
  modeloElegido:Modelos = null;
  filterpost = ''

  constructor(
    public modeloservice:ModeloServiceService,
    public authservice: AuthService,
    private http:HttpClient,
    private notifyService : NotificationService
  ) { }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnInit(): void {
    this.cargardatos();
  }


  cargardatos(){
    Swal.fire({
      title: 'Cargando datos',

    });
    Swal.showLoading();
    this.modeloservice.getModelos().subscribe((modelos) => {
      (this.modelos = modelos),
       console.log(this.modelos);
      this.dataArray = modelos;
      this.dataSource = new MatTableDataSource<Modelos>(this.dataArray);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      Swal.close();
      console.log(modelos);
    });
  }

  delete(modelo: Modelos): void {
    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar el modelo:  ${modelo.modelo}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,eliminar!',
      cancelButtonText: 'No, cancelar',
    }).then((result) => {
      if (result.value) {
        this.modeloservice.delete(modelo.id).subscribe((response) => {
          this.modelos = this.modelos.filter((cli) => cli !== modelo);
          this.cargardatos();
          this.notifyService.showWarning(`Modelo: ${modelo.modelo}  eliminado`,"Delete");
        });

      }
       else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelado', 'Dato no eliminado :)', 'error');
      }
    });
  }

}
