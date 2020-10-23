package com.example.springboot.item.service;

import java.util.List;

import com.example.springboot.item.model.Item;
import com.example.springboot.item.model.Producto;

public interface IItemService {

	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
	
    public Producto save(Producto producto);
	
	public Producto update(Producto producto, Long id);
	
	public void eliminar(Long id);
	
	
	
	
	
}
