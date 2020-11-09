/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.repository;

import com.mitienda.entity.Historial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhonfre
 */
@Repository
public interface HistorialRepositpory extends JpaRepository<Historial, Integer> {

    @Query(value = "SELECT * FROM HISTORIAL \n"
            + "where anio = :anio and mes = :mes", nativeQuery = true)
    List<Historial> reporteMensual(@Param("anio") int fechaInicio, @Param("mes") int mes);
    
    
    @Query(value = "SELECT COALESCE(SUM(ganancia),0) FROM HISTORIAL\n"
            + "where anio = :anio and mes = :mes", nativeQuery = true)
    int gananciaMensual(@Param("anio") int fechaInicio, @Param("mes") int mes);
    
    @Query(value = "SELECT COALESCE(SUM(inversion),0) FROM HISTORIAL\n"
            + "where anio = :anio and mes = :mes", nativeQuery = true)
    int inversionMensual(@Param("anio") int fechaInicio, @Param("mes") int mes);
    
    @Query(value = "SELECT COALESCE(SUM(total_generado),0) FROM HISTORIAL\n"
            + "where anio = :anio and mes = :mes", nativeQuery = true)
    int totalGeneradoMensual(@Param("anio") int fechaInicio, @Param("mes") int mes);
}
