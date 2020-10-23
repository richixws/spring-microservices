package com.example.springboot.item.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.item.model.Item;
import com.example.springboot.item.model.Producto;

@Service("serviceResTemplate")
public class ItemServiceImpl implements IItemService{

	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		
		List<Producto> productos=Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class));
		
		return productos.stream().map(p -> new Item(p, 2)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		Map<String , String> patchvariable=new HashMap<String, String>();
		patchvariable.put("id",id.toString());
		
		Producto producto=clienteRest.getForObject("http://servicio-productos/listar/{id}",Producto.class,patchvariable);
		
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		
		HttpEntity<Producto> body=new HttpEntity<Producto>(producto);
		
		ResponseEntity<Producto> response=clienteRest.exchange("http://servicio-productos/crear", HttpMethod.POST, body, Producto.class);
	    Producto productoResponse=response.getBody();
		
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		
		Map<String, String> pathVaribles=new HashMap<String,String>();
		pathVaribles.put("id", id.toString());
		
		
		HttpEntity<Producto> body=new HttpEntity<Producto>(producto);
		
		ResponseEntity<Producto> response=clienteRest.exchange("http://servicio-productos/editar/{id}", 
				HttpMethod.PUT, body, Producto.class,pathVaribles);
				
		return response.getBody();
	}

	@Override
	public void eliminar(Long id) {
		
		Map<String, String> patchVariables=new HashMap<String , String>();
		patchVariables.put("id", id.toString());
		
		clienteRest.delete("http://servicio-productos/eliminar/{id}", patchVariables);
			
	}

}
