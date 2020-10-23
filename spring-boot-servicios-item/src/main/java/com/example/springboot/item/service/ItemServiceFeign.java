package com.example.springboot.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.springboot.item.clientes.ProductoClienteRest;
import com.example.springboot.item.model.Item;
import com.example.springboot.item.model.Producto;

@Service("serviceFeign")
//@Primary
public class ItemServiceFeign implements IItemService {

	@Autowired
	private ProductoClienteRest clientefeign;
	
	@Override
	public List<Item> findAll() {
	
		return clientefeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		return new Item(clientefeign.detalle(id), cantidad);
	}

	

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}

}
