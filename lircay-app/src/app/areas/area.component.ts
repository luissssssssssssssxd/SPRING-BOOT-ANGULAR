import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';
import { NotificationService } from '../notification.service';
import { AuthService } from '../usuarios/auth.service';
import { Area } from './area';
import { AreaService } from './area.service';

@Component({
  selector: 'app-area',
  templateUrl: './area.component.html'
})
export class AreaComponent implements OnInit {


  displayedColumns: string[] = ['action', 'ID', 'Nombre',];

  public dataSource: MatTableDataSource<Area>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  private dataArray: any;

  areas:Area[];
  areaElegida:Area = null;
  filterpost = ''


  constructor(
    public areaservice:AreaService,
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
    this.areaservice.getAreas().subscribe((areas) => {
      (this.areas = areas),
       console.log(this.areas);
      this.dataArray = areas;
      this.dataSource = new MatTableDataSource<Area>(this.dataArray);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      Swal.close();
      console.log(areas);
    });
  }

  delete(area: Area): void {
    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar el area:  ${area.nombrearea}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,eliminar!',
      cancelButtonText: 'No, cancelar',
    }).then((result) => {
      if (result.value) {
        this.areaservice.delete(area.id).subscribe((response) => {
          this.areas = this.areas.filter((cli) => cli !== area);
          this.cargardatos();
          this.notifyService.showWarning(`Area: ${area.nombrearea}  eliminada`,"Delete");
        });

      }
       else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelado', 'Dato no eliminado :)', 'error');
      }
    });
  }






}
