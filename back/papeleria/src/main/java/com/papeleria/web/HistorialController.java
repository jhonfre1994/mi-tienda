/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papeleria.web;

import com.papeleria.dto.HistorialDTO;
import com.papeleria.dto.ProductosTemporalDTO;
import com.papeleria.dto.ReporteMes;
import com.papeleria.service.HistorialService;
import java.util.Date;
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
@RequestMapping("/api/v.1/historial")
@CrossOrigin(origins = "*")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/{fecha}")
    public ResponseEntity<?> listarHistoriales(@PathVariable("fecha") Date fecha) {
        ReporteMes res = historialService.listarHistoriales(fecha);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarDia(@RequestBody List<ProductosTemporalDTO> lista) {
        boolean res = historialService.guardarVentaDiaria(lista);
        return ResponseEntity.ok(res);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHistorial(@PathVariable("id") Integer id){
        boolean res = historialService.eliminarHistorial(id);
        return ResponseEntity.ok(res);
    }

}
