import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Impresora } from '../impresoras/impresora';
import { NotificationService } from '../notification.service';
import { Incidencia } from './incidencia';
import { IncidenciasService } from './incidencias.service';

@Component({
  selector: 'app-formincidencias',
  templateUrl: './formincidencias.component.html',
  styleUrls: ['./formincidencias.component.css']
})
export class FormincidenciasComponent implements OnInit {

  incidencia: Incidencia = new Incidencia();
  impresoras: Impresora[];
  public errores: string[];
  minDate: Date;
  maxDate: Date;

  constructor(
    private incidenciaService: IncidenciasService,
    private notifyService: NotificationService,
    public router: Router,
    public activatedroute: ActivatedRoute,

  ) {
       // Set the minimum to January 1st 20 years in the past and December 31st a year in the future.
       const currentYear = new Date().getFullYear();
       this.minDate = new Date(currentYear - 20, 0, 1);
       this.maxDate = new Date(currentYear + 1, 11, 31);
  }
  checkDate() {
    const dateSendingToServer = new DatePipe('es-CL').transform(this.incidencia.fecha_inicio, 'dd/MM/yyyy')
    console.log(dateSendingToServer);
  }

  ngOnInit(): void {
    this.cargardatosIncidencias();
    this.cargardatosImpresoras();
  }

  cargardatosIncidencias(){
    this.activatedroute.params.subscribe((params) => {
      let id = params['id'];
      if(id){
        this.incidenciaService.getIncidencia(id).subscribe((incidencia)=>{
          this.incidencia = incidencia
        });
      }
    });
    this.incidenciaService.getImpresoras().subscribe(impresoras => this.impresoras = impresoras);
  }

  cargardatosImpresoras() {
    this.incidenciaService.getImpresoras().subscribe((impresoras) => {
      this.impresoras = impresoras;
    });
  }


  compararRegion(o1: Impresora, o2: Impresora): boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }

    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false : o1.id === o2.id;
  }

  public create(): void {
    this.incidenciaService.create(this.incidencia).subscribe(
      (json) => {
        this.notifyService.showSuccess('Registro creado con exito!', 'OK');
        this.router.navigate(['/incidencias']);
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  update(): void {
    this.incidenciaService.update(this.incidencia).subscribe(
      (json) => {
        this.router.navigate(['/incidencias']);
        this.notifyService.showSuccess(
          `La incidencia se ha actualizado correctamente`,
          'OK'
        );
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }



}
