import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Select2Data } from 'ng-select2-component';
import { map } from 'rxjs/internal/operators/map';
import Swal from 'sweetalert2';
import { Estado } from '../estado/estado';
import { Marca } from '../marcas/marca';
import { Modelos } from '../marcas/modelos';
import { NotificationService } from '../notification.service';
import { Impresora } from './impresora';
import { ImpresorasService } from './impresoras.service';

@Component({
  selector: 'app-formimpresoras',
  templateUrl: './formimpresoras.component.html',
  styleUrls: ['./formimpresoras.component.css']
})




export class FormimpresorasComponent implements OnInit {

  public exampleData: Select2Data;

  numeroserie = new FormControl('', [Validators.required]);
  fecha_mov = new FormControl('', [Validators.required]);
  obs = new FormControl('', [Validators.required]);
  area = new FormControl('', [Validators.required]);
  estado = new FormControl('', [Validators.required]);
  marca = new FormControl('', [Validators.required]);
  modelo = new FormControl('', [Validators.required]);
  marcas: Marca[];
  marcaElegida: Marca = null;
  modelos: Modelos[] = [];
  estados: Estado[];
  public impresora: Impresora = new Impresora();
  public errores: string[];


  constructor(
    private impresoraService: ImpresorasService,
    private notifyService : NotificationService,
    public router: Router,
    public activatedroute: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.cargardatosImpresora();
    this.cargardatosMarcas();
    this.cargardatosEstados();
    // this.cargardatosEstado();
  }

  cargardatosMarcas(){
    Swal.fire({
      title: 'Cargando datos',

    });
    Swal.showLoading();
    this.impresoraService.getMarcas2().subscribe((marcas) => {
      (this.marcas = marcas),
      Swal.close();
    });
  }

  cargardatosEstados(){
    this.impresoraService.getEstados().subscribe((estados) => {
      (this.estados = estados)
    });

  }
  // cargardatosEstado(){
  //   this.impresoraService.getEstados().pipe(
  //     map((estado: Estado[]) => estado.map((estado: Estado) => ({label: estado.estadoimpresora, value: estado.id})))
  //   ).subscribe((estadodata: Select2Data) => {
  //     this.exampleData = estadodata;
  //     console.log(this.exampleData)
  //     console.log(estadodata)
  //   });

  // }


  cargardatosImpresora(){
    this.activatedroute.params.subscribe(params  => {

      let id = params ['id']
      if(id){
        this.impresoraService.getImpresora(id).subscribe((impresora) =>{
          this.impresora = impresora;
        })
      }
    })
  }

  public create(): void {
    this.impresoraService.create(this.impresora).subscribe(
      (json) => {
        this.notifyService.showSuccess("Registro creado con exito!","OK");
        this.router.navigate(['/impresoras']);
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  update(): void {
    this.impresoraService.update(this.impresora).subscribe(
      (json) => {
        this.router.navigate(['/impresoras']);
        this.notifyService.showSuccess( `La impresora se ha actualizado correctamente`,"OK");
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }

}
