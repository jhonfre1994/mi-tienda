/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.service;


import com.mitienda.dto.ProductosTemporalDTO;
import com.mitienda.dto.ReporteMes;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jhonfre
 */
public interface HistorialService {

    ReporteMes listarHistoriales(Date fecha);

    boolean guardarVentaDiaria(List<ProductosTemporalDTO> lista);
    
    boolean eliminarHistorial(Integer idHistorial);

}
