import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {catchError,map} from 'rxjs/operators';
import { Urgencia } from './urgencia';
import {Router} from '@angular/router';
import * as XLSX from 'xlsx';
import * as FileSaver from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class UrgenciasService {

  private urlEndPoint:string ='http://localhost:8080/api/urgencias';


  constructor(private http:HttpClient,
  private router:Router) { }


  fileType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
  fileExtension = '.xlsx';

  public exportExcel(jsonData: any[], fileName: string): void {

    const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet(jsonData);
    const wb: XLSX.WorkBook = { Sheets: { 'data': ws }, SheetNames: ['data'] };
    const excelBuffer: any = XLSX.write(wb, { bookType: 'xlsx', type: 'array' });
    this.saveExcelFile(excelBuffer, fileName);
  }

  private saveExcelFile(buffer: any, fileName: string): void {
    const data: Blob = new Blob([buffer], {type: this.fileType});
    FileSaver.saveAs(data, fileName + this.fileExtension);
  }









  //Retorna lista de ingresos urgencias.
  getUrgencias():Observable<Urgencia[]>{
    return this.http.get<Urgencia[]>(this.urlEndPoint);
  }


// Registrar nuevo dato
  create(urgencia:Urgencia): Observable<any>{
    return this.http.post(this.urlEndPoint,urgencia).pipe(
      catchError(e => {

        if(e.status ==400){
          return throwError(e);
        }
        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }

        console.error(e.error.mensaje);
        return throwError(e);
      })
    );
  }

//Captura dato especifico por ID
  getUrgencia(id: any): Observable<Urgencia>{
    return this.http.get<Urgencia>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if(e.status !=401 && e.error.mensaje){
          this.router.navigate(['/urgencias']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }));

  }

//Update ficha urgencia
  update(urgencia:Urgencia):Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${urgencia.id}`,urgencia).pipe(
      catchError(e => {


        if(e.status ==400){
          return throwError(e);
        }
        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }

  //Elimina una ficha urgencias
  delete(id:any):Observable<Urgencia>{
    return this.http.delete<Urgencia>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {


        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    )
  }





}
