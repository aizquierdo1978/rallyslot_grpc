package com.alberto.rallyslot.controller.tramo;

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
import com.alberto.rallyslot.dto.tramo.Tramo;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.tramo.TramoService;
import com.alberto.rallyslot.util.RallyslotConstants;

/**
 * La clase del controlador de tramos.
 */
@RestController
public class TramoController extends BaseController {

	private static final Log LOG = LogFactory.getLog(TramoController.class);

	@Autowired
	private TramoService tramoService;

	@PostMapping("/tramos")
	public ResponseEntity<Tramo> saveTramo(@RequestBody Tramo tramo) {
		LOG.debug("Inicio de creacion de un tramo con datos:" + tramo.toString());
		ResponseEntity<Tramo> respuesta = null;
		try {
			tramo.setCreationDate(new Date());
			tramo.setCreationUser(RallyslotConstants.USER);
			Tramo tramoCreado = tramoService.saveTramo(tramo);
			respuesta = new ResponseEntity<>(tramoCreado, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al crear el tramo con datos " + tramo, e);
			throw e;
		}
		LOG.debug("Fin de creacion de un tramo con datos:" + tramo.toString());
		return respuesta;
	}

	// Read operation
	@GetMapping("/tramos")
	public ResponseEntity<List<Tramo>> fetchTramoList() {
		LOG.debug("Inicio de consulta de listados de tramos.");

		ResponseEntity<List<Tramo>> respuesta = null;
		try {
			List<Tramo> listadoTramos = tramoService.fetchTramoList();
			respuesta = new ResponseEntity<>(listadoTramos, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Se ha producido un error al consultar las categorias", e);
			throw e;
		}
		LOG.debug("Fin de consulta de listados de categoriaes.");
		return respuesta;
	}

	// Update operation
	@PutMapping("/tramos/{id}")
	public ResponseEntity<Tramo> updateTramo(@RequestBody Tramo tramo, @PathVariable("id") Long tramoId) {
		LOG.debug("Inicio de modificacion de el tramo con identificador " + tramoId);
		ResponseEntity<Tramo> respuesta = null;
		try {
			tramo.setModificationDate(new Date());
			tramo.setModificationUser(RallyslotConstants.USER);
			tramo = tramoService.updateTramo(tramo, tramoId);
			respuesta = new ResponseEntity<>(tramo, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al modificar el tramo con identificador " + tramoId, e);
			throw e;
		}
		LOG.debug("Fin de modificacion de el tramo con identificador " + tramoId);
		return respuesta;
	}

	// Delete operation
	@DeleteMapping("/tramos/{id}")
	public ResponseEntity<String> deleteTramoById(@PathVariable("id") Long tramoId) {
		LOG.debug("Fin de borrado de el tramo con identificador " + tramoId);
		ResponseEntity<String> respuesta = null;
		try {
			tramoService.deleteTramoById(tramoId, RallyslotConstants.USER, new Date());
			respuesta = new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al borrar el tramo con identificador " + tramoId, e);
			throw e;
		}
		LOG.debug("Fin de borrado de el tramo con identificador " + tramoId);
		return respuesta;
	}

	/**
	 * Obtiene el tramo.
	 *
	 * @param tramoId el identificador del tramo
	 * @return el tramo
	 */
	@GetMapping("/tramos/{id}")
	public ResponseEntity<Tramo> getTramo(@PathVariable("id") Long tramoId) {
		LOG.debug("Inicio de obtencion del tramo con identificador " + tramoId);
		ResponseEntity<Tramo> respuesta = null;
		try {
			Tramo tramo = tramoService.getTramo(tramoId);
			respuesta = new ResponseEntity<>(tramo, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al obtener el tramo con identificador " + tramoId, e);
			throw e;
		}
		LOG.debug("Fin de obtencion del tramo con identificador " + tramoId);
		return respuesta;
	}

	/**
	 * Obtiene los tramos de un inscrito.
	 *
	 * @param tramoId el identificador del tramo
	 * @return el tramo
	 */
	@GetMapping("/tramos/inscrito/{inscritoId}")
	public ResponseEntity<List<Tramo>> getTramosByInscrito(@PathVariable("inscritoId") Long inscritoId) {
		LOG.debug("Inicio de obtencion de los tramos del inscrito con identificador " + inscritoId);
		ResponseEntity<List<Tramo>> respuesta = null;
		try {
			List<Tramo> tramos = tramoService.getTramosByInscrito(inscritoId);
			respuesta = new ResponseEntity<>(tramos, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al obtener de los tramos del inscrito con identificador " + inscritoId,
					e);
			throw e;
		}
		LOG.debug("Fin de obtencion de los tramos del inscrito con identificador " + inscritoId);
		return respuesta;
	}

}
