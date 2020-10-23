package com.example.springboot.producto.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.producto.models.entity.Producto;
import com.example.springboot.producto.service.IProductoService;

@RestController
//@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	private Environment env;   //Ribon con Feign 
	
	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProductoService service;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		
		return service.findAll().stream().map(producto -> {
			
		//	producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));   //Ribon con feign 
			producto.setPort(port);                                                     //Ribon con RestTemplate
			return producto;               
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/listar/{id}")
	public Producto ver (@PathVariable Long id )  {
		
		Producto producto=service.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));      //Ribon con feign 
	    producto.setPort(port);                                                          //Ribon con RestTemplate

		/*
		 * try { Thread.sleep(2000L); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	    
	    return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		
		return service.save(producto);
		
	}
	@PutMapping("/editar/id")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		
		Producto productodb=service.findById(id);
		
		productodb.setNombre(producto.getNombre());
		productodb.setPrecio(producto.getPrecio());
		
		return service.save(productodb);
		
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void eliminar(@PathVariable Long id) {
		
	       service.delete(id);
	}
	
	
}
