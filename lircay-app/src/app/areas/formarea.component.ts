import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Area } from './area';
import { AreaService } from './area.service';

@Component({
  selector: 'app-formarea',
  templateUrl: './formarea.component.html',
  styleUrls: ['./formarea.component.css']
})
export class FormareaComponent implements OnInit {

  public area:Area = new Area();
  
  public errores:string[];

  constructor(public areaservice:AreaService,
    public router:Router,
    public activatedroute: ActivatedRoute) { }


  ngOnInit(): void {
    this.cargarDartos();
  }

  cargarDartos():void{
    this.activatedroute.params.subscribe(params  => {
      let id = params ['id']
      if(id){
        this.areaservice.getArea(id).subscribe((area) =>
          this.area= area)
      }
    })
  }


  public create():void{
    this.areaservice.create(this.area).subscribe(json => {
      this.router.navigate(['/areas'])
      
    })
  }

}
