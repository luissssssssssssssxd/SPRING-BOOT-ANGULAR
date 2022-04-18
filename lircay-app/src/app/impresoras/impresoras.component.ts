import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';
import { NotificationService } from '../notification.service';
import { AuthService } from '../usuarios/auth.service';
import { Impresora } from './impresora';
import { ImpresorasService } from './impresoras.service';

@Component({
  selector: 'app-impresoras',
  templateUrl: './impresoras.component.html',
  styleUrls: ['./impresoras.component.css']
})
export class ImpresorasComponent implements OnInit {

  

  displayedColumns: string[] = ['action', 'Numero de serie', 'Area', 'Estado', 'Marca', 'Modelo', 'Actualizacion'];

  public dataSource: MatTableDataSource<Impresora>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  private dataArray: any;

  impresoras:Impresora[];
  impresoraElegida:Impresora = null;
  filterpost = ''


  constructor(
    public impresoraService:ImpresorasService,
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
    this.impresoraService.getImpresoras().subscribe((impresoras) => {
      (this.impresoras = impresoras),
       console.log(this.impresoras);
      this.dataArray = impresoras;
      this.dataSource = new MatTableDataSource<Impresora>(this.dataArray);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      Swal.close();
      console.log(impresoras);
    });
  }

  delete(impresora: Impresora): void {
    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar la impresora:  ${impresora.numeroserie}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,eliminar!',
      cancelButtonText: 'No, cancelar',
    }).then((result) => {
      if (result.value) {
        this.impresoraService.delete(impresora.numeroserie).subscribe((response) => {
          this.impresoras = this.impresoras.filter((cli) => cli !== impresora);
          this.cargardatos();
          this.notifyService.showWarning(`Impresora: ${impresora.numeroserie}  eliminada`,"Delete");
        });

      }
       else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelado', 'Dato no eliminado :)', 'error');
      }
    });
  }

}
