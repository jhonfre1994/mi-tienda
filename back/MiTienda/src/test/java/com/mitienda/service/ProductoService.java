/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.service;

import com.mitienda.dto.ProductoDTO;
import java.util.List;

/**
 *
 * @author jhonfre
 */
public interface ProductoService {

    List<ProductoDTO> listarProductos();

    List<ProductoDTO> listarProductosConCantidad();

    ProductoDTO guardarProducto(ProductoDTO producto);

    ProductoDTO eliminarProducto(Integer idProducto);

}
