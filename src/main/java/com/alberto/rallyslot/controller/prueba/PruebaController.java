package com.alberto.rallyslot.controller.prueba;

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
import com.alberto.rallyslot.dto.prueba.Prueba;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.prueba.PruebaService;
import com.alberto.rallyslot.util.RallyslotConstants;

/**
 * La clase del controlador de pruebas.
 */
@RestController
public class PruebaController extends BaseController {

	private static final Log LOG = LogFactory.getLog(PruebaController.class);

	@Autowired
	private PruebaService pruebaService;

	@PostMapping("/pruebas")
	public ResponseEntity<Prueba> savePrueba(@RequestBody Prueba prueba) {
		LOG.debug("Inicio de creacion de un prueba con datos:" + prueba.toString());
		ResponseEntity<Prueba> respuesta = null;
		try {
			prueba.setCreationDate(new Date());
			prueba.setCreationUser("user");
			Prueba pruebaCreado = pruebaService.savePrueba(prueba);
			respuesta = new ResponseEntity<>(pruebaCreado, HttpStatus.OK);
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al crear la prueba con datos " + prueba, e);
			throw e;
		}
		LOG.debug("Fin de creacion de un prueba con datos:" + prueba.toString());
		return respuesta;
	}

	// Read operation
	@GetMapping("/pruebas")
	public List<Prueba> fetchPruebaList() {
		LOG.debug("Inicio de consulta de listados de prueba.");
		List<Prueba> listadoPruebas = pruebaService.fetchPruebaList();
		LOG.debug("Fin de consulta de listados de prueba.");
		return listadoPruebas;
	}

	// Update operation
	@PutMapping("/pruebas/{id}")
	public ResponseEntity<Prueba> updatePrueba(@RequestBody Prueba prueba, @PathVariable("id") Long pruebaId) {
		LOG.debug("Inicio de modificacion de la prueba con identificador " + pruebaId);
		ResponseEntity<Prueba> respuesta = null;
		try {
			prueba.setModificationDate(new Date());
			prueba.setModificationUser("user");
			prueba = pruebaService.updatePrueba(prueba, pruebaId);
			respuesta = new ResponseEntity<>(prueba, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al modificar la prueba con identificador " + pruebaId, e);
			throw e;
		}
		LOG.debug("Fin de modificacion de la prueba con identificador " + pruebaId);
		return respuesta;
	}

	// Delete operation
	@DeleteMapping("/pruebas/{id}")
	public ResponseEntity<String> deletePruebaById(@PathVariable("id") Long pruebaId) {
		LOG.debug("Fin de borrado de la prueba con identificador " + pruebaId);
		ResponseEntity<String> respuesta = null;
		try {
			pruebaService.deletePruebaById(pruebaId, RallyslotConstants.USER, new Date());
			respuesta = new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al borrar la prueba con identificador " + pruebaId, e);
			throw e;
		}
		LOG.debug("Fin de borrado de la prueba con identificador " + pruebaId);
		return respuesta;
	}

	/**
	 * Obtiene la prueba.
	 *
	 * @param pruebaId el identificador de la prueba
	 * @return la prueba
	 */
	@GetMapping("/pruebas/{id}")
	public ResponseEntity<Prueba> getPrueba(@PathVariable("id") Long pruebaId) {
		LOG.debug("Inicio de obtencion de la prueba con identificador " + pruebaId);
		ResponseEntity<Prueba> respuesta = null;
		try {
			Prueba prueba = pruebaService.getPrueba(pruebaId);
			respuesta = new ResponseEntity<>(prueba, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al obtener la prueba con identificador " + pruebaId, e);
			throw e;
		}
		LOG.debug("Fin de obtencion de la prueba con identificador " + pruebaId);
		return respuesta;
	}

}
