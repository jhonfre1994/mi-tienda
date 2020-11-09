import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProductoDTO } from '../model/ProductoDTO';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }

  listarTodos(): Observable<ProductoDTO[]> {
    return this.http.get<ProductoDTO[]>(environment.url + "api/v.1/producto/")
  }

  guardar(producto: ProductoDTO): Observable<ProductoDTO> {
    return this.http.post<ProductoDTO>(environment.url + "api/v.1/producto/", producto, this.header)
  }

  eliminar(id: number): Observable<ProductoDTO> {
    return this.http.delete<ProductoDTO>(environment.url + "api/v.1/producto/" + id, this.header)
  }

  listarTodosConCantidad(): Observable<ProductoDTO[]> {
    return this.http.get<ProductoDTO[]>(environment.url + "api/v.1/producto/listarTodosConCantidad")
  }
}
