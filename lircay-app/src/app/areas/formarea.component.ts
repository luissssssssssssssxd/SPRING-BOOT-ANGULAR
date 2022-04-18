import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '../notification.service';
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
    public activatedroute: ActivatedRoute,
    private notifyService: NotificationService) { }


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
      this.notifyService.showSuccess(`Area: ${json.area.nombrearea}, creada exitosamente`,"OK")
      
    })
  }

  update(): void {
    this.areaservice.update(this.area).subscribe(
      (json) => {
        this.router.navigate(['/areas']);
        this.notifyService.showSuccess( `Registro ${json.area.nombrearea} actualizado con exito`,"OK");
      },
      (err) => {
        this.errores = err.error.errors as string[];
        console.error('Codigo error backend' + err.status);
        console.error(err.error.errors);
      }
    );
  }

}
