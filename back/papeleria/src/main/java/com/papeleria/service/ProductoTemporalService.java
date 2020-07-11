/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papeleria.service;

import com.papeleria.dto.ProductosTemporalDTO;
import java.util.List;

/**
 *
 * @author jhonfre
 */
public interface ProductoTemporalService {

    List<ProductosTemporalDTO> listarTodos();

    ProductosTemporalDTO guardarProductoTem(ProductosTemporalDTO productoT);

    ProductosTemporalDTO eliminarProdcutoTem(Integer id);

}
