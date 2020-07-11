import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductoService } from './producto.service';
import { ProductoDTO } from '../model/ProductoDTO';
import { r3JitTypeSourceSpan } from '@angular/compiler';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css'],
  providers: [ProductoService]
})
export class ProductosComponent implements OnInit {

  formGroup: FormGroup;
  public listaProductos: Array<ProductoDTO> = []
  public productoDTO: ProductoDTO = new ProductoDTO();
  constructor(private formBuilder: FormBuilder,
    private productoService: ProductoService) { }

  ngOnInit(): void {
    this.buildForm();
    this.listarTodos();

    this.formGroup.controls["costoUnitario"].valueChanges.subscribe(x => {

      let canti = Number(this.formGroup.controls["cantidad"].value);
      let costo = Number(this.formGroup.controls["costoUnitario"].value)
      let res = canti * costo
      this.formGroup.controls['costoTotal'].setValue(res);

    })

  }

  buildForm() {
    this.formGroup = this.formBuilder.group({
      nombre: new FormControl('', [Validators.required]),
      cantidad: new FormControl('', [Validators.required]),
      costoUnitario: new FormControl('', [Validators.required]),
      costoTotal: new FormControl('', [Validators.required]),
      precio: new FormControl('', [Validators.required]),
      precioVenta: new FormControl('', [Validators.required]),
    }

    );
  }


  listarTodos() {
    this.productoService.listarTodos().subscribe(res => {
      if (res != null) {
        this.listaProductos = res;
        console.log(res)
      }
    })
  }


  guardar() {
    if (this.productoDTO.idProducto != null) {
      this.productoDTO.idProducto = this.productoDTO.idProducto
    } else {
      this.productoDTO.idProducto = 0;
    }

    this.productoDTO.nombre = this.formGroup.get('nombre').value;
    this.productoDTO.cantidad = this.formGroup.get('cantidad').value;
    this.productoDTO.costoUnitario = this.formGroup.get('costoUnitario').value;
    this.productoDTO.costoTotal = this.formGroup.get('costoTotal').value;
    this.productoDTO.precio = this.formGroup.get('precio').value;
    this.productoDTO.precioVenta = this.formGroup.get('precioVenta').value;
    console.log(this.productoDTO)
    this.productoService.guardar(this.productoDTO).subscribe(res => {
      if (res != null) {
        console.log(res)
        this.nuevo()
        this.listarTodos();

      }
    })
  }

  editar(producto: ProductoDTO) {
    console.log(producto)
    this.productoDTO.idProducto = producto.idProducto
    this.formGroup.controls['nombre'].setValue(producto.nombre);;
    this.formGroup.controls['cantidad'].setValue(producto.cantidad);;
    this.formGroup.controls['costoUnitario'].setValue(producto.costoUnitario);;
    this.formGroup.controls['costoTotal'].setValue(producto.costoTotal);;
    this.formGroup.controls['precio'].setValue(producto.precio);;
    this.formGroup.controls['precioVenta'].setValue(producto.precioVenta);;
  }

  nuevo() {
    this.formGroup.reset();
    this.productoDTO = new ProductoDTO();
  }

  eliminar(pro :ProductoDTO){
    this.productoService.eliminar(pro.idProducto).subscribe(res => {
      if(res != null){
        this.listarTodos();
      }
    })
  }
}
