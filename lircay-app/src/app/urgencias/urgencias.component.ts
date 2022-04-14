import { Component, OnInit } from '@angular/core';
import { Urgencia } from './urgencia';
import { UrgenciasService } from './urgencias.service';
import { AuthService } from '../usuarios/auth.service';
import swal from 'sweetalert2'
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import { Label } from 'ng2-charts';
import {HttpClient} from '@angular/common/http';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-urgencias',
  templateUrl: './urgencias.component.html'

})
export class UrgenciasComponent implements OnInit {

  title = 'Average annual software developer salary';

  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: Label[];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];

  barChartData: ChartDataSets[] = [];


  public  urgencias:Urgencia [];
  filterpost = ''

  private urlEndPoint:string ='http://192.168.2.175:8080/api/urgencias';

  constructor(public urgenciaservice:UrgenciasService,
  public authservice: AuthService,private http:HttpClient) { }

  ngOnInit(): void {
    this.urgenciaservice.getUrgencias().subscribe(
      urgencias => this.urgencias = urgencias,
    );

  }

  grafico(){
    this.http.get<Urgencia[]>(this.urlEndPoint).subscribe(data=> {
      this.barChartLabels = data.map(item=>item.nombrepaciente);
      this.barChartData =  [
        { data: data.map(item=>item.id), label: 'Countries' }
      ];

    });
  }




  export() {

    var today = new Date();
    var time = today.getDate() + ":" +  today.getMonth() +  ":" +  today.getFullYear();
  console.log(this.urgencias);
  this.urgenciaservice.exportExcel(this.urgencias, 'reporte: '+ time  );
}



  delete(urgencia: Urgencia): void {

    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar la ficha de:  ${urgencia.nombrepaciente} ${urgencia.observacion}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si,eliminar!',
      cancelButtonText: 'No, cancelar'
    }).then((result) => {
      if (result.value) {
        this.urgenciaservice.delete(urgencia.id).subscribe(
          response => {
            this.urgencias = this.urgencias.filter(cli => cli !== urgencia)
            Swal.fire(
              'Ficha Eliminado!',
              `Paciente ${urgencia.nombrepaciente} eliminado con éxito.`,
              'success'
            )
          }
        )
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
          'Cancelled',
          'Your imaginary file is safe :)',
          'error'
        )
      }
    })



 }



}
