import { Component, OnInit } from '@angular/core';
import { Modelos } from './modelos';
import { ModeloServiceService } from './modelo-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '../notification.service';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-formmodelo',
  templateUrl: './formmodelo.component.html',
  styleUrls: ['./formmodelo.component.css']
})
export class FormmodeloComponent implements OnInit {
  modelos = new FormControl('', [Validators.required]);


  public modelo:Modelos = new Modelos();
  
  public errores:string[];

  constructor(
    public modeloservice:ModeloServiceService,
    public router:Router,
    public activatedroute: ActivatedRoute,
    private notifyService: NotificationService) { }


  ngOnInit(): void {
    this.cargarDartos();
  }

  cargarDartos():void{
    this.activatedroute.params.subscribe(params  => {
      let id = params ['id']
      if(id){
        this.modeloservice.getModelo(id).subscribe((modelo) =>
          this.modelo= modelo)
      }
    })
  }


  public create():void{
    this.modeloservice.create(this.modelo).subscribe(json => {
      this.router.navigate(['/modelos']);
      this.notifyService.showSuccess(`Modelo creado exitosamente`,"OK")
    })
  }

  update(): void {
    this.modeloservice.update(this.modelo).subscribe(
      (json) => {
        this.router.navigate(['/modelos']);
        this.notifyService.showSuccess( `Registro ${json.modelo.modelo} actualizado con exito`,"OK");
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }

}
