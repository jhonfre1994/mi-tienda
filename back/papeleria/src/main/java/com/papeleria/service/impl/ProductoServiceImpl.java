/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papeleria.service.impl;

import com.papeleria.dto.ProductoDTO;
import com.papeleria.entity.Producto;
import com.papeleria.exceptions.responses.BadRequestException;
import com.papeleria.repository.ProductoRepository;
import com.papeleria.service.ProductoService;
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
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> listarProductos() {
        List<Producto> productos = productoRepository.findAll();

        if (productos != null && !productos.isEmpty()) {
            List<ProductoDTO> res = new ArrayList<>();
            for (Producto producto : productos) {
                res.add(mapper.map(producto, ProductoDTO.class));
            }
            return res;
        }
        throw new BadRequestException("No hay productos guardador en la base de datos");
    }

    @Override
    public List<ProductoDTO> listarProductosConCantidad() {
        List<Producto> productos = productoRepository.listarProductosConCantidad();

        if (productos != null && !productos.isEmpty()) {
            List<ProductoDTO> res = new ArrayList<>();
            for (Producto producto : productos) {
                res.add(mapper.map(producto, ProductoDTO.class));
            }
            return res;
        }
        throw new BadRequestException("No hay productos disponibles");
    }

    @Override
    public ProductoDTO guardarProducto(ProductoDTO producto) {
        Producto res = new Producto();
        res = productoRepository.save(mapper.map(producto, Producto.class));
        return mapper.map(res, ProductoDTO.class);
    }

    @Override
    public ProductoDTO eliminarProducto(Integer idProducto) {
        Optional<Producto> produ = productoRepository.findById(idProducto);
        if (produ.isPresent()) {
            productoRepository.deleteById(idProducto);
            return mapper.map(produ.get(), ProductoDTO.class);
        }
        throw new BadRequestException("Ocurrio un error al eliminar el producto");
    }

}
