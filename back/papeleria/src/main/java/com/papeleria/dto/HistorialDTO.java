/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papeleria.dto;

import java.util.Date;
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
public class HistorialDTO {

    private Integer idHistorial;
    private Date fecha;
    private int totalGenerado;
    private int ganancia;
    private int inversion;
    private int anio;
    private int mes;
    
    //datos adicionales
}
