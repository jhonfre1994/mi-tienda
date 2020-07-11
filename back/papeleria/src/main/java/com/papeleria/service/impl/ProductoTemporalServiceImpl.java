/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papeleria.service.impl;

import com.papeleria.dto.ProductosTemporalDTO;
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
    public ProductosTemporalDTO guardarProductoTem(ProductosTemporalDTO productoT) {
        ProductosTemporal res = new ProductosTemporal();
        res = repository.save(mapper.map(productoT, ProductosTemporal.class));
        return mapper.map(res, ProductosTemporalDTO.class);
    }

    @Override
    public ProductosTemporalDTO eliminarProdcutoTem(Integer id) {
        Optional<ProductosTemporal> res = repository.findById(id);
        if (res.isPresent()) {
            repository.deleteById(id);
            return mapper.map(res.get(), ProductosTemporalDTO.class);
        }
        throw new BadRequestException("Ocurrio un error");
    }

}
