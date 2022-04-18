import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { NotificationService } from '../notification.service';
import { Marca } from './marca';
import { MarcaService } from './marca.service';

@Component({
  selector: 'app-formmarca',
  templateUrl: './formmarca.component.html',
  styleUrls: ['./formmarca.component.css'],
})
export class FormmarcaComponent implements OnInit {

  nombremarca = new FormControl('', [Validators.required]);
  public marca: Marca = new Marca();

  public errores: string[];

  constructor(
    public marcaservice: MarcaService,
    public router: Router,
    public activatedroute: ActivatedRoute,
    private notifyService : NotificationService


  ) { }


  ngOnInit(): void {
    this.cargarDartos();
  }

  cargarDartos(): void {
    this.activatedroute.params.subscribe((params) => {
      let id = params['id'];
      if (id) {
        this.marcaservice
          .getMarca(id)
          .subscribe((marca) => (this.marca = marca));
      }
    });
  }

  public create(): void {
    this.marcaservice.create(this.marca).subscribe(
      (json) => {
        this.notifyService.showSuccess("Registro creado con exito!","OK");
        this.router.navigate(['/marcas']);
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  update(): void {
    this.marcaservice.update(this.marca).subscribe(
      (json) => {
        this.router.navigate(['/marcas']);
        this.notifyService.showSuccess( `Registro ${json.marca.nombre} actualizado con exito`,"OK");
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }
}
