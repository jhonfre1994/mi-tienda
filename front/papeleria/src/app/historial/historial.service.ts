import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HistorialDTO } from '../model/HistorialDTO';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ReporteMesDTO } from '../model/ReporteMesDTO';

@Injectable({
  providedIn: 'root'
})
export class HistorialService {

  constructor(public http: HttpClient) { }

  listarHistoriales(fecha: Date): Observable<ReporteMesDTO> {
    return this.http.get<ReporteMesDTO>(environment.url + "api/v.1/historial/"+ fecha)
  }


  eliminar(id: number): Observable<boolean> {
    return this.http.delete<boolean>(environment.url + "api/v.1/historial/"+ id)
  }
}
