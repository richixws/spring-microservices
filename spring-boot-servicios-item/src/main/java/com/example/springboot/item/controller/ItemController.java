package com.example.springboot.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Env;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.springboot.item.model.Item;
import com.example.springboot.item.model.Producto;
import com.example.springboot.item.service.IItemService;
import com.example.springboot.item.service.ItemServiceFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
//@RequestMapping("/api")
public class ItemController {

   
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment env;
    
	
	@Autowired
   // @Qualifier("serviceFeign")
   @Qualifier("serviceResTemplate")
	private IItemService itemservice;
	
    @Value("${configuracion.texto}")
	private String texto;
	
	@GetMapping("/listar")
	public List<Item> findAll(){
		
		return itemservice.findAll();
	}
	
	//@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/listar/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		
		return itemservice.findById(id, cantidad);
	}
	
	// metodo alternativo ante tolerancia a fallas con hixtrix
	public Item metodoAlternativo(Long id,  Integer cantidad) {
		
		
		Item item=new Item();
		Producto producto=new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("camara sony");
		producto.setPrecio(500.00);
	    item.setProducto(producto);
	    
	    return item;
		
	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
		
		log.info(texto);
		
		Map<String, String> json=new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		
		if (env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		
		return new ResponseEntity<Map<String,String>>(json,HttpStatus.OK); 
		
	}
	
	@PostMapping("/crear")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		
		return itemservice.save(producto);
		
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		
		return itemservice.update(producto, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void eliminar(@PathVariable Long id) {
		
	    itemservice.eliminar(id);
		
	}
	
	
	
}
