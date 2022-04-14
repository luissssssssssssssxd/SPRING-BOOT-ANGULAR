import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Marca } from './marca';

@Injectable({
  providedIn: 'root'
})
export class MarcaService {

  private urlEndPoint:string ='http://localhost:8080/api/marcas';


  constructor(private http:HttpClient) { }


  getMarcas():Observable<Marca[]>{
    return this.http.get(this.urlEndPoint).pipe(
      map(marcas=> marcas as  Marca[])
    );
  }


}
