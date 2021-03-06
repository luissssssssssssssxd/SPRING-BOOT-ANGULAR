import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { NotificationService } from '../notification.service';
import { Estado } from './estado';
import { EstadoService } from './estado.service';

@Component({
  selector: 'app-formestado',
  templateUrl: './formestado.component.html',
  styleUrls: ['./formestado.component.css'],
})
export class FormestadoComponent implements OnInit {


  public estado: Estado = new Estado();

  public errores: string[];

  public  changePasswordForm : any;
  constructor(
    public estadoservice: EstadoService,
    public router: Router,
    public activatedroute: ActivatedRoute,
    private notifyService: NotificationService,
    formBuilder: FormBuilder
  ) {

    this.changePasswordForm = formBuilder.group({
      estado: new FormControl('',
            Validators.compose([Validators.required, Validators.minLength(3)]))
    });
  }

  ngOnInit(): void {
    this.cargarDatos();

  }

  cargarDatos(): void {
    this.activatedroute.params.subscribe((params) => {
      let id = params['id'];
      if (id) {
        this.estadoservice
          .getEstado(id)
          .subscribe((estado) => (this.estado = estado));
      }
    });
  }

  public create(): void {
    this.estadoservice.create(this.estado).subscribe((json) => {
      this.router.navigate(['/estados']);
      this.notifyService.showSuccess(
        `Estado: ${json.estado.estadoimpresora} creado correctamente`,
        'OK'
      );

      console.log();
    });
    return;
  }

  update(): void {
    this.estadoservice.update(this.estado).subscribe((json) => {
      this.router.navigate(['/estados']);
      this.notifyService.showSuccess(
        `Registro ${json.estado.estadoimpresora} actualizado con exito`,
        'OK'
      );
    });
  }
}
