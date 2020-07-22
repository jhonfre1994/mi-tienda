/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papeleria.service;

import com.papeleria.dto.HistorialDTO;
import com.papeleria.dto.ProductosTemporalDTO;
import com.papeleria.dto.ReporteMes;
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
