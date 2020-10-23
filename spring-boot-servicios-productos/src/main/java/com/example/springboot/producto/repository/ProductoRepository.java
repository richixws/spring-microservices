package com.example.springboot.producto.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.producto.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long>{

	
}
