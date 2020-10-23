package com.example.springboot.producto.service;

import java.util.List;

import com.example.springboot.producto.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
	
	
}
