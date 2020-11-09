/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.web;


import com.mitienda.dto.ProductosTemporalDTO;
import com.mitienda.service.ProductoTemporalService;
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
@RequestMapping("/api/v.1/productoTemporal")
@CrossOrigin(origins = "*")
public class ProductoTemporalController {
 
    @Autowired
    private ProductoTemporalService productoTemporalService;

    public ProductoTemporalController(ProductoTemporalService productoTemporalService) {
        this.productoTemporalService = productoTemporalService;
    }
    
    @GetMapping("/")
    public ResponseEntity<?> listarTodos() {
        List<ProductosTemporalDTO> res = productoTemporalService.listarTodos();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarProducto(@RequestBody List<ProductosTemporalDTO> productos) {
        boolean res = productoTemporalService.guardarProductos(productos);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("idProducto") Integer idProducto) {
        ProductosTemporalDTO res = productoTemporalService.eliminarProdcutoTem(idProducto);
        return ResponseEntity.ok(res);
    }
}
