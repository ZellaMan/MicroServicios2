package com.producto_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producto_service.model.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
