package com.alberto.rallyslot.controller.categoria;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.rallyslot.controller.BaseController;
import com.alberto.rallyslot.dto.categoria.Categoria;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.categoria.CategoriaService;
import com.alberto.rallyslot.util.RallyslotConstants;

@RestController
public class CategoriaController extends BaseController {

	private static final Log LOG = LogFactory.getLog(CategoriaController.class);

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping("/categorias")
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		LOG.debug("Inicio de creacion de una categoria con datos:" + categoria.toString());
		ResponseEntity<Categoria> respuesta = null;
		try {
			categoria.setCreationDate(new Date());
			categoria.setCreationUser("user");
			Categoria categoriaCreado = categoriaService.saveCategoria(categoria);
			respuesta = new ResponseEntity<>(categoriaCreado, HttpStatus.OK);
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al crear la categoria con datos " + categoria.toString(), e);
			throw e;
		}
		LOG.debug("Fin de creacion de una categoria con datos:" + categoria.toString());
		return respuesta;
	}

	// Read operation
	@GetMapping("/categorias")
	public ResponseEntity<List<Categoria>> fetchCategoriaList() {
		LOG.debug("Inicio de consulta de listados de categoriaes.");

		ResponseEntity<List<Categoria>> respuesta = null;
		try {
			List<Categoria> listadoCategorias = categoriaService.fetchCategoriaList();
			respuesta = new ResponseEntity<>(listadoCategorias, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Se ha producido un error al consultar las categorias", e);
			throw e;
		}
		LOG.debug("Fin de consulta de listados de categoriaes.");
		return respuesta;
	}

	// Update operation
	@PutMapping("/categorias/{id}")
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria,
			@PathVariable("id") Long categoriaId) {
		LOG.debug("Inicio de modificacion de la categoría con identificador " + categoriaId);
		ResponseEntity<Categoria> respuesta = null;
		try {
			categoria.setModificationDate(new Date());
			categoria.setModificationUser("user");
			Categoria categoriaUpdated = categoriaService.updateCategoria(categoria, categoriaId);
			respuesta = new ResponseEntity<>(categoriaUpdated, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al modificar la categoria con identificador " + categoriaId, e);
			throw e;
		}
		LOG.debug("Fin de modificacion de la categoria con identificador " + categoriaId);
		return respuesta;
	}

	/**
	 * Obtiene la categoría.
	 *
	 * @param categoriaId el identificador de la categoría
	 * @return la categoría
	 */
	@GetMapping("/categorias/{id}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable("id") Long categoriaId) {
		LOG.debug("Inicio de obtencion de la categoría con identificador " + categoriaId);
		ResponseEntity<Categoria> respuesta = null;
		try {
			Categoria categoria = categoriaService.getCategoria(categoriaId);
			respuesta = new ResponseEntity<>(categoria, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al obtener la categoria con identificador " + categoriaId, e);
			throw e;
		}
		LOG.debug("Fin de obtencion de la categoria con identificador " + categoriaId);
		return respuesta;
	}

	// Delete operation
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<Void> deleteCategoriaById(@PathVariable("id") Long categoriaId) {
		LOG.debug("Fin de borrado de la categoría con identificador " + categoriaId);
		ResponseEntity<Void> respuesta = null;
		try {
			categoriaService.deleteCategoriaById(categoriaId, RallyslotConstants.USER, new Date());
			respuesta = new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al borrar la categoria con identificador " + categoriaId, e);
			throw e;
		}
		LOG.debug("Fin de borrado de la categoria con identificador " + categoriaId);
		return respuesta;
	}

}
