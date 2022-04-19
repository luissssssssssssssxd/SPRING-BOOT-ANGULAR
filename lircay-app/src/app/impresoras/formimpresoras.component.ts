import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Select2OptionData } from 'ng-select2';
import Swal from 'sweetalert2';
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
  public impresora: Impresora = new Impresora();
  public errores: string[];

  public exampleData: Array<Select2OptionData>;


  constructor(
    private impresoraService: ImpresorasService,
    private notifyService : NotificationService,
    public router: Router,
    public activatedroute: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.cargardatosMarcas();
    this.cargardatosImpresoras();
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

  cargardatosImpresoras(){
    this.activatedroute.params.subscribe(params  => {
      let numeroserie = params ['numeroserie']
      if(numeroserie){
        this.impresoraService.getArea(numeroserie).subscribe((impresora) =>
          this.impresora= impresora)
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
        this.notifyService.showSuccess( `Impresora: ${json.impresora.numeroserie} actualizado con exito`,"OK");
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }

}
