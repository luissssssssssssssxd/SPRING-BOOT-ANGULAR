import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../usuarios/auth.service';
import { Area } from './area';
import { AreaService } from './area.service';

@Component({
  selector: 'app-area',
  templateUrl: './area.component.html'
})
export class AreaComponent implements OnInit {

  constructor(public areaservice:AreaService,
    public authservice: AuthService,private http:HttpClient) { }

    public areas:Area[];

  ngOnInit(): void {
    this.areaservice.getAreas().subscribe(
      areas =>this.areas = areas,
    )
  }

}
