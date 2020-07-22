/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papeleria.service.impl;

import com.papeleria.dto.ProductoDTO;
import com.papeleria.dto.ProductosTemporalDTO;
import com.papeleria.entity.Producto;
import com.papeleria.entity.ProductosTemporal;
import com.papeleria.exceptions.responses.BadRequestException;
import com.papeleria.repository.ProductoTemporalRepository;
import com.papeleria.service.ProductoTemporalService;
import java.util.ArrayList;
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
public class ProductoTemporalServiceImpl implements ProductoTemporalService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductoTemporalRepository repository;

    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @Override
    public List<ProductosTemporalDTO> listarTodos() {
        List<ProductosTemporal> lista = repository.findAll();

        if (lista != null && !lista.isEmpty()) {
            List<ProductosTemporalDTO> res = new ArrayList<>();
            for (ProductosTemporal tem : lista) {
                res.add(mapper.map(tem, ProductosTemporalDTO.class));
            }
            return res;
        }
        throw new BadRequestException("No hay nada hasta el momento");
    }

    @Override
    public ProductosTemporalDTO eliminarProdcutoTem(Integer id) {
        Optional<ProductosTemporal> res = repository.findById(id);
        if (res.isPresent()) {
            ProductoDTO producto = new ProductoDTO();
            producto = mapper.map(res.get().getIdProducto(), ProductoDTO.class);
            producto.setCantidad(producto.getCantidad() + res.get().getCantidad());
            productoServiceImpl.guardarProducto(producto);
            
            repository.deleteById(id);
            return mapper.map(res.get(), ProductosTemporalDTO.class);
        }
        throw new BadRequestException("Ocurrio un error");
    }

    @Override
    public boolean guardarProductos(List<ProductosTemporalDTO> lista) {
        if (lista != null && !lista.isEmpty()) {
            List<ProductosTemporal> tempEntity = new ArrayList<>();
            for (ProductosTemporalDTO pro : lista) {
                ProductosTemporal item = new ProductosTemporal();
                item.setCantidad(pro.getCantidad());
                item.setIdProducto(mapper.map(pro.getIdProducto(), Producto.class));
                item.setIdProductoTemporal(pro.getIdProductoTemporal());
                item.setPrecio(pro.getPrecio());
                tempEntity.add(item);

                ProductoDTO producto = new ProductoDTO();
                producto = pro.getIdProducto();
                producto.setCantidad(producto.getCantidad() - pro.getCantidad());
                productoServiceImpl.guardarProducto(producto);

            }

            Iterable<ProductosTemporal> iteremp = tempEntity;
            tempEntity = repository.saveAll(iteremp);
            if (tempEntity != null && !tempEntity.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
