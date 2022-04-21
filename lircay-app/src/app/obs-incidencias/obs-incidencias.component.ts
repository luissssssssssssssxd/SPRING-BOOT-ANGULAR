import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ImpresorasService } from '../impresoras/impresoras.service';
import { Incidencia } from '../incidencias/incidencia';
import { NotificationService } from '../notification.service';
import { ObsIncidenciasService } from './obs-incidencias.service';
import { obs_incidencia } from './obs_incidencia';

@Component({
  selector: 'app-obs-incidencias',
  templateUrl: './obs-incidencias.component.html',
  styleUrls: ['./obs-incidencias.component.css']
})


export class ObsIncidenciasComponent implements OnInit {



  public obs_incidencia: obs_incidencia = new obs_incidencia();
  incidencias: Incidencia[]; 

  constructor(
    private obs_incidenciasService: ObsIncidenciasService,
    private notifyService: NotificationService,
    public router: Router,
    public activatedroute: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.cargardatosIncidencias();
    this.cargardatosObs_incidencias();
  }

  cargardatosObs_incidencias() {
    this.activatedroute.params.subscribe((params) => {
      let id = params['id'];
      if (id) {
        this.obs_incidenciasService.getObs_incidencia(id).subscribe((obs_incidencias) => {
          this.obs_incidencia = obs_incidencias;
        });
      }
    });
    this.obs_incidenciasService.getIncidencias().subscribe(incidencias => this.incidencias = incidencias);
    console.log(this.incidencias);

  }

  

  

  cargardatosIncidencias() {
    this.obs_incidenciasService.getIncidencias().subscribe((incidencias) => {
      this.incidencias = incidencias;
    });
  }



}
