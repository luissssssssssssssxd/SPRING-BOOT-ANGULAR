import { Component, OnInit } from '@angular/core';
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

  constructor(private marcaservice:MarcaService) { }

  ngOnInit(): void {
    this.marcaservice.getMarcas().subscribe(marcas=>this.marcas = marcas);
  }

}
