import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../productos/producto.service';
import { ProductoDTO } from '../model/ProductoDTO';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { ProductosTemporalDTO } from '../model/ProductosTemporalDTO';
import { VentaProductoTemporalService } from './venta-producto-temporal.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-venta-prodcuto-temporal',
  templateUrl: './venta-prodcuto-temporal.component.html',
  styleUrls: ['./venta-prodcuto-temporal.component.css'],
  providers: [ProductoService, VentaProductoTemporalService, MessageService]
})
export class VentaProdcutoTemporalComponent implements OnInit {

  formGroup: FormGroup;
  public productos: Array<ProductoDTO> = [];
  public productoSelected: ProductoDTO;
  public listaVenta: Array<ProductosTemporalDTO> = [];
  public ventaDTO: ProductosTemporalDTO;
  public precioVenta: number = 0;
  public resumenDiaList: Array<ProductosTemporalDTO> = [];
  constructor(private productoService: ProductoService,
    private formBuilder: FormBuilder,
    private ventaProductoTemporalService: VentaProductoTemporalService,
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.buildForm();
    this.listarProductoConCantidad();
    this.listarProductosTemporales();
    this.formGroup.controls["cantidad"].valueChanges.subscribe(x => {

      if (this.formGroup.controls["producto"].value != null || this.formGroup.controls["producto"].value != undefined) {
        let canti = Number(this.formGroup.controls["producto"].value.precioVenta);
        let costo = Number(this.formGroup.controls["cantidad"].value)
        let res = canti * costo
        this.formGroup.controls['precio'].setValue(res);
      }

    })

  }


  buildForm() {
    this.formGroup = this.formBuilder.group({
      producto: new FormControl('', [Validators.required]),
      cantidad: new FormControl('', [Validators.required]),
      precio: new FormControl('', [Validators.required]),
    }

    );
  }

  listarProductoConCantidad() {
    this.productoService.listarTodosConCantidad().subscribe(res => {
      if (res != null) {
        this.productos = res;
      }
    })

  }

  limpiar() {
    this.formGroup.reset();
    this.ventaDTO = new ProductosTemporalDTO();
  }

  guardar() {
    this.ventaDTO = new ProductosTemporalDTO();
    this.ventaDTO.idProductoTemporal = 0;
    this.ventaDTO.idProducto = this.formGroup.get('producto').value;
    this.ventaDTO.cantidad = this.formGroup.get('cantidad').value;
    this.ventaDTO.precio = this.formGroup.get('precio').value;
    this.listaVenta.push(this.ventaDTO)
    this.precioVenta += Number(this.formGroup.get('precio').value)
    this.limpiar();
  }

  eliminar(venta: ProductosTemporalDTO) {
    let index = this.listaVenta.findIndex(d => d === venta);
    this.listaVenta.splice(index, 1);//remove element from array
    this.precioVenta -= venta.precio
  }

  generarVenta() {
    this.ventaProductoTemporalService.guardarVenta(this.listaVenta).subscribe(res => {
      if (res != null) {
        console.log(res)
        this.listarProductosTemporales();
        this.listaVenta = [];
        this.precioVenta = 0;
        this.showToast("success", "Bien", "Venta generada correctamente");
        this.listarProductoConCantidad();
      }
    })
  }

  listarProductosTemporales() {
    this.resumenDiaList = []
    this.ventaProductoTemporalService.lstarTemporal().subscribe(res => {
      console.log(res)
      if (res != null) {
        this.resumenDiaList = res;
      }
    })
  }

  eliminarTemporal(temp: ProductosTemporalDTO) {
    this.ventaProductoTemporalService.eliminarTemporal(temp.idProductoTemporal).subscribe(res => {
      if (res != null) {
        this.listarProductosTemporales();
        this.showToast("success", "Bien", "Producto eliminado correctamente");
        this.listarProductoConCantidad();
      }
    })
  }

  cerrarCaja(){
    this.ventaProductoTemporalService.guardarResumenDia(this.resumenDiaList).subscribe(res => {
      if(res != null){
        console.log(res);
        this.listarProductosTemporales();
        this.resumenDiaList = [];
        this.showToast("success", "Bien", "Caja cerrada correctamente");
      }
    })
  }

  public showToast(tipo: string, resumen: string, detalle: string): void {
    this.messageService.add({ severity: tipo, summary: resumen, detail: detalle })
  }
}
