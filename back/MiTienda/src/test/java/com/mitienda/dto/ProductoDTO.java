/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhonfre
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {

    private Integer idProducto;
    private String nombre;
    private int cantidad;
    private int costoUnitario;
    private int costoTotal;
    private int precio;
    private int precioVenta;
}
