package br.com.personal.money.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.personal.money.model.Categoria;
import br.com.personal.money.service.CategoriaService;
import br.com.personal.money.util.ResourceUtil;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public List<Categoria> buscarTodos() {
		return service.buscarTodos();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
		return ResourceUtil.getResponseOkOrNotFound(service.buscarPorCodigo(codigo));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		service.salvar(categoria, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
	}
}
