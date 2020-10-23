package com.example.springboot.producto.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.producto.models.entity.Producto;
import com.example.springboot.producto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private ProductoRepository productodao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		
		return (List<Producto>)productodao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
	
		return productodao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		
		return productodao.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {

         productodao.deleteById(id);
		
	}

}
