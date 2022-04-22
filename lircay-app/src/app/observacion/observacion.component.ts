import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Incidencia } from '../incidencias/incidencia';
import { IncidenciasService } from '../incidencias/incidencias.service';
import { NotificationService } from '../notification.service';
import { Observacion } from './observacion';
import { ObservacionService } from './observacion.service';

@Component({
  selector: 'app-observacion',
  templateUrl: './observacion.component.html',
  styleUrls: ['./observacion.component.css'],
})
export class ObservacionComponent implements OnInit {
  observacion: Observacion[] | any;
  incidencias:Incidencia[] | any;

  constructor(
    private observacionservice: ObservacionService,
    private notifyService: NotificationService,
    public router: Router,
    public activatedroute: ActivatedRoute,
    private incidenciaservice:IncidenciasService
  ) {}

  ngOnInit(): void {
    this.cargardatosObservacion();
  }


  cargardatosObservacion(){
    this.activatedroute.params.subscribe((params) => {
      let id = params['id'];
      if(id){
        this.observacionservice.getObservacion(id).subscribe((observacion)=>{
          this.observacion = observacion
          console.log(this.observacion);
        });
      }
    });
    this.incidenciaservice.getIncidencias().subscribe(incidencias => this.incidencias = incidencias);

  }
}
