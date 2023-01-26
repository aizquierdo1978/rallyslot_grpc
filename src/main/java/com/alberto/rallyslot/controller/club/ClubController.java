package com.alberto.rallyslot.controller.club;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.alberto.rallyslot.dto.club.Club;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.club.ClubService;
import com.alberto.rallyslot.util.RallyslotConstants;

@RestController
public class ClubController extends BaseController {

	private static final Log LOG = LogFactory.getLog(ClubController.class);

	@Value("${application.version}")
	private String pomVersion;

	@Autowired
	private ClubService clubService;

	@PostMapping("/clubs")
	public ResponseEntity<Club> saveClub(@RequestBody Club club) {
		LOG.debug("Inicio de creacion de un club con datos:" + club.toString());
		ResponseEntity<Club> respuesta = null;
		try {
			club.setCreationDate(new Date());
			club.setCreationUser("user");
			Club clubCreado = clubService.saveClub(club);
			respuesta = new ResponseEntity<>(clubCreado, HttpStatus.OK);
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al crear el club con datos " + club.toString(), e);
			throw e;
		}
		LOG.debug("Fin de creacion de un club con datos:" + club.toString());
		return respuesta;
	}

	// Read operation
	@GetMapping("/clubs")
	public List<Club> fetchClubList() {
		LOG.debug("Inicio de consulta de listados de clubes.");
		LOG.info("Versión aplicación:" + pomVersion);
		List<Club> listadoClubs = clubService.fetchClubList();
		LOG.debug("Fin de consulta de listados de clubes.");
		return listadoClubs;
	}

	// Update operation
	@PutMapping("/clubs/{id}")
	public ResponseEntity<Club> updateClub(@RequestBody Club club, @PathVariable("id") Long clubId) {
		LOG.debug("Inicio de modificacion del club con identificador " + clubId);
		ResponseEntity<Club> respuesta = null;
		try {
			club.setModificationDate(new Date());
			club.setModificationUser("user");
			Club clubUpdated = clubService.updateClub(club, clubId);
			respuesta = new ResponseEntity<>(clubUpdated, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al modificar el club con identificador " + clubId, e);
			throw e;
		}
		LOG.debug("Fin de modificacion del club con identificador " + clubId);
		return respuesta;
	}

	/**
	 * Obtiene el club.
	 *
	 * @param clubId el identificador del club
	 * @return el club
	 */
	@GetMapping("/clubs/{id}")
	public ResponseEntity<Club> getClub(@PathVariable("id") Long clubId) {
		LOG.debug("Inicio de obtencion del club con identificador " + clubId);
		ResponseEntity<Club> respuesta = null;
		try {
			Club club = clubService.getClub(clubId);
			respuesta = new ResponseEntity<>(club, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al obtener el club con identificador " + clubId, e);
			throw e;
		}
		LOG.debug("Fin de obtencion del club con identificador " + clubId);
		return respuesta;
	}

	// Delete operation
	@DeleteMapping("/clubs/{id}")
	public ResponseEntity<Void> deleteClubById(@PathVariable("id") Long clubId) {
		LOG.debug("Fin de borrado del club con identificador " + clubId);
		ResponseEntity<Void> respuesta = null;
		try {
			clubService.deleteClubById(clubId, RallyslotConstants.USER, new Date());
			respuesta = new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al borrar el club con identificador " + clubId, e);
			throw e;
		}
		LOG.debug("Fin de borrado del club con identificador " + clubId);
		return respuesta;
	}

}
