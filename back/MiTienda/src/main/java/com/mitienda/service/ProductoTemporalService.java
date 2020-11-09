/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.service;

import com.mitienda.dto.ProductosTemporalDTO;
import java.util.List;

/**
 *
 * @author jhonfre
 */
public interface ProductoTemporalService {

    List<ProductosTemporalDTO> listarTodos();

    ProductosTemporalDTO eliminarProdcutoTem(Integer id);
    
    boolean guardarProductos(List<ProductosTemporalDTO> lista);

}
