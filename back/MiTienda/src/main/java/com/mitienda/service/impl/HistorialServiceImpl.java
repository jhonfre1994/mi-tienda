/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitienda.service.impl;


import com.mitienda.dto.HistorialDTO;
import com.mitienda.dto.ProductosTemporalDTO;
import com.mitienda.dto.ReporteMes;
import com.mitienda.entity.Historial;
import com.mitienda.exceptions.responses.BadRequestException;
import com.mitienda.repository.HistorialRepositpory;
import com.mitienda.repository.ProductoTemporalRepository;
import com.mitienda.service.HistorialService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jhonfre
 */
@Service
public class HistorialServiceImpl implements HistorialService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private HistorialRepositpory repositpory;

    @Autowired
    private ProductoTemporalRepository repositoryTemporal;

    @Override
    public ReporteMes listarHistoriales(Date fecha) {
        LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int ano = localDate.getYear();
        int mes = localDate.getMonthValue();
        List<Historial> lista = repositpory.reporteMensual(ano, mes);
        ReporteMes reporte = new ReporteMes();
        if (lista != null && !lista.isEmpty()) {
            List<HistorialDTO> lis = new ArrayList<>();
            for (Historial historial : lista) {
                lis.add(mapper.map(historial, HistorialDTO.class));
            }
            reporte.setHistorial(lis);
            reporte.setGananciaTotalMes(repositpory.gananciaMensual(ano, mes));
            reporte.setInversionTotalMes(repositpory.inversionMensual(ano, mes));
            reporte.setTotalGenedadoMes(repositpory.totalGeneradoMensual(ano, mes));
            return reporte;
        }
        throw new BadRequestException("No hay datos de historial");
    }

    @Override
    public boolean guardarVentaDiaria(List<ProductosTemporalDTO> lista) {
        if (lista != null && !lista.isEmpty()) {
            Historial his = new Historial();
            int ganancia = 0;
            int inversion = 0;
            int gananciaTotal = 0;

            for (ProductosTemporalDTO produ : lista) {
                gananciaTotal += produ.getPrecio();
                inversion += produ.getIdProducto().getCostoUnitario() * produ.getCantidad();

            }
            ganancia = gananciaTotal - inversion;
            his.setIdHistorial(0);
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int ano = localDate.getYear();
            int mes = localDate.getMonthValue();

            his.setFecha(date);
            his.setGanancia(ganancia);
            his.setInversion(inversion);
            his.setTotalGenerado(gananciaTotal);
            his.setAnio(ano);
            his.setMes(mes);
            his = repositpory.save(his);
            if (his != null) {
                repositoryTemporal.deleteAllInBatch();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarHistorial(Integer idHistorial) {
        Optional<Historial> his = repositpory.findById(idHistorial);

        if (his.isPresent()) {
            repositpory.deleteById(idHistorial);
            return true;
        }
        throw new BadRequestException("Error al eliminar el historial");
    }

}
