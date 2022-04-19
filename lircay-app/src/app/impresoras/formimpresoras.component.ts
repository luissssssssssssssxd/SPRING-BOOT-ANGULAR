import { Component, OnInit } from '@angular/core';
import { Select2Module } from 'ng-select2-component';

@Component({
  selector: 'app-formimpresoras',
  templateUrl: './formimpresoras.component.html',
  styleUrls: ['./formimpresoras.component.css']
})
export class FormimpresorasComponent implements OnInit {

  public exampleData: Array<Select2OptionData>;

  constructor() { }

  ngOnInit(): void {
  }

}
