/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "historial")
public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "total_generado")
    private int totalGenerado;

    @Column(name = "ganancia")
    private int ganancia;

    @Column(name = "inversion")
    private int inversion;

    @Column(name = "anio")
    private int anio;
    
    @Column(name = "mes")
    private int mes;

}
