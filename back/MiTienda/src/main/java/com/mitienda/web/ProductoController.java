/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.web;


import com.mitienda.dto.ProductoDTO;
import com.mitienda.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhonfre
 */
@RestController
@RequestMapping("/api/v.1/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/")
    public ResponseEntity<?> listarTodos() {
        List<ProductoDTO> res = productoService.listarProductos();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/listarTodosConCantidad")
    public ResponseEntity<?> listarTodosConCantidad() {
        List<ProductoDTO> res = productoService.listarProductosConCantidad();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarProducto(@RequestBody ProductoDTO producto) {
        ProductoDTO res = productoService.guardarProducto(producto);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("idProducto") Integer idProducto) {
        ProductoDTO res = productoService.eliminarProducto(idProducto);
        return ResponseEntity.ok(res);
    }
}
