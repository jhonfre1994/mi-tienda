import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProductosTemporalDTO } from '../model/ProductosTemporalDTO';

@Injectable({
  providedIn: 'root'
})
export class VentaProductoTemporalService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }

  guardarVenta(producto: Array<ProductosTemporalDTO>): Observable<boolean> {
    return this.http.post<boolean>(environment.url + "api/v.1/productoTemporal/", producto, this.header)
  }

  lstarTemporal(): Observable<ProductosTemporalDTO[]> {
    return this.http.get<ProductosTemporalDTO[]>(environment.url + "api/v.1/productoTemporal/")
  }

  eliminarTemporal(id: number):Observable<ProductosTemporalDTO>{
    return this.http.delete<ProductosTemporalDTO>(environment.url + "api/v.1/productoTemporal/" + id)
  }

  guardarResumenDia(lista: Array<ProductosTemporalDTO>):Observable<boolean>{
    return this.http.post<boolean>(environment.url + "api/v.1/historial/", lista, this.header)
  }
}
