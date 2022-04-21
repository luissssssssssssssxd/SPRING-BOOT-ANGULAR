import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';
import { NotificationService } from '../notification.service';
import { AuthService } from '../usuarios/auth.service';
import { Incidencia } from './incidencia';
import { IncidenciasService } from './incidencias.service';

@Component({
  selector: 'app-incidencias',
  templateUrl: './incidencias.component.html',
  styleUrls: ['./incidencias.component.css']
})
export class IncidenciasComponent implements OnInit {

  displayedColumns: string[] = ['ID','Action','numeroserie','Area','Estado','Marca', 'Modelo','Observaciones'];

  public dataSource: MatTableDataSource<Incidencia>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  private dataArray: any;

  incidencias:Incidencia[];
  // impresoraElegida:Impresora = null;
  filterpost = ''

  constructor(
    public incidenciaService:IncidenciasService,
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
    this.incidenciaService.getIncidencias().subscribe((incidencias) => {
      (this.incidencias = incidencias),
       console.log(this.incidencias);
      this.dataArray = incidencias;
      this.dataSource = new MatTableDataSource<Incidencia>(this.dataArray);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      Swal.close();
      console.log(incidencias);
    });
  }

  delete(incidencia: Incidencia): void {
    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar la incidencia:  ${incidencia.id}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,eliminar!',
      cancelButtonText: 'No, cancelar',
    }).then((result) => {
      if (result.value) {
        this.incidenciaService.delete(incidencia.id).subscribe((response) => {
          this.incidencias = this.incidencias.filter((cli) => cli !== incidencia);
          this.cargardatos();
          this.notifyService.showWarning(`Incidencia: ${incidencia.id}  eliminada`,"Delete");
        });

      }
       else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelado', 'Dato no eliminado :)', 'error');
      }
    });
  }

}
