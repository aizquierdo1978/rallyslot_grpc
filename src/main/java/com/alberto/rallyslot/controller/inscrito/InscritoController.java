package com.alberto.rallyslot.controller.inscrito;

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
import com.alberto.rallyslot.dto.inscrito.Inscrito;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.inscrito.InscritoService;
import com.alberto.rallyslot.util.RallyslotConstants;

/**
 * La clase del controlador de inscritos.
 */
@RestController
public class InscritoController extends BaseController {

	private static final Log LOG = LogFactory.getLog(InscritoController.class);

	@Autowired
	private InscritoService inscritoService;

	@PostMapping("/inscritos")
	public ResponseEntity<Inscrito> saveInscrito(@RequestBody Inscrito inscrito) {
		LOG.debug("Inicio de creacion de un inscrito con datos:" + inscrito.toString());
		ResponseEntity<Inscrito> respuesta = null;
		try {
			inscrito.setCreationDate(new Date());
			inscrito.setCreationUser(RallyslotConstants.USER);
			Inscrito inscritoCreado = inscritoService.saveInscrito(inscrito);
			respuesta = new ResponseEntity<>(inscritoCreado, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al crear el inscrito con datos " + inscrito, e);
			throw e;
		}
		LOG.debug("Fin de creacion de un inscrito con datos:" + inscrito.toString());
		return respuesta;
	}

	// Read operation
	@GetMapping("/inscritos")
	public List<Inscrito> fetchInscritoList() {
		LOG.debug("Inicio de consulta de listados de inscrito.");
		List<Inscrito> listadoInscritos = inscritoService.fetchInscritoList();
		LOG.debug("Fin de consulta de listados de inscrito.");
		return listadoInscritos;
	}

	// Update operation
	@PutMapping("/inscritos/{id}")
	public ResponseEntity<Inscrito> updateInscrito(@RequestBody Inscrito inscrito,
			@PathVariable("id") Long inscritoId) {
		LOG.debug("Inicio de modificacion de el inscrito con identificador " + inscritoId);
		ResponseEntity<Inscrito> respuesta = null;
		try {
			inscrito.setModificationDate(new Date());
			inscrito.setModificationUser(RallyslotConstants.USER);
			inscrito = inscritoService.updateInscrito(inscrito, inscritoId);
			respuesta = new ResponseEntity<>(inscrito, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al modificar el inscrito con identificador " + inscritoId, e);
			throw e;
		}
		LOG.debug("Fin de modificacion de el inscrito con identificador " + inscritoId);
		return respuesta;
	}

	// Delete operation
	@DeleteMapping("/inscritos/{id}")
	public ResponseEntity<String> deleteInscritoById(@PathVariable("id") Long inscritoId) {
		LOG.debug("Fin de borrado de el inscrito con identificador " + inscritoId);
		ResponseEntity<String> respuesta = null;
		try {
			inscritoService.deleteInscritoById(inscritoId, RallyslotConstants.USER, new Date());
			respuesta = new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al borrar el inscrito con identificador " + inscritoId, e);
			throw e;
		}
		LOG.debug("Fin de borrado de el inscrito con identificador " + inscritoId);
		return respuesta;
	}

	/**
	 * Obtiene el inscrito.
	 *
	 * @param inscritoId el identificador del inscrito
	 * @return el inscrito
	 */
	@GetMapping("/inscritos/{id}")
	public ResponseEntity<Inscrito> getInscrito(@PathVariable("id") Long inscritoId) {
		LOG.debug("Inicio de obtencion del inscrito con identificador " + inscritoId);
		ResponseEntity<Inscrito> respuesta = null;
		try {
			Inscrito inscrito = inscritoService.getInscrito(inscritoId);
			respuesta = new ResponseEntity<>(inscrito, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al obtener el inscrito con identificador " + inscritoId, e);
			throw e;
		}
		LOG.debug("Fin de obtencion del inscrito con identificador " + inscritoId);
		return respuesta;
	}

}
