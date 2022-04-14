import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../usuarios/auth.service';
import { Marca } from './marca';
import { MarcaService } from './marca.service';
import { Modelos } from './modelos';

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css']
})
export class MarcaComponent implements OnInit {

  marcas: Marca[];
  marcaElegida: Marca = null;
  modelos: Modelos[] = [];

  filterpost = ''

  constructor(private marcaservice:MarcaService,
  public authservice: AuthService,private http:HttpClient) { }

  ngOnInit(): void {
    this.marcaservice.getMarcas().subscribe(marcas=>this.marcas = marcas);
  }

}
