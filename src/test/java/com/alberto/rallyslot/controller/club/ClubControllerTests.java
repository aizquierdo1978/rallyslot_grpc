package com.alberto.rallyslot.controller.club;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.alberto.rallyslot.controller.common.BaseControllerTests;
import com.alberto.rallyslot.dto.club.Club;
import com.alberto.rallyslot.util.ConstantesTests;
import com.alberto.rallyslot.util.UtilTests;

class ClubControllerTests extends BaseControllerTests {

	private static final Log LOG = LogFactory.getLog(ClubControllerTests.class);
	
	/**
	 * Test de obtención de los clubes vacio
	 */
	@Test
	void getClubesEmptyTest() {
		String assertMessage = "Test de obtencion de los clubes vacio. ";
		Exception ex = null;
		try {
			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_CLUBES).build().encode().toUri();
			List<Club> clubes = new ArrayList<>();
			clubes = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, clubes.getClass());
			assertNotNull(clubes, assertMessage + " Se ha devuelto un nulo");
			assertTrue(clubes.isEmpty(), "Se ha devuelto un listado de clubes no vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de los clubes
	 */
	@Test
	void getClubesTest() {
		String assertMessage = "Test de obtencion de los clubes vacio. ";
		Exception ex = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);

			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_CLUBES).build().encode().toUri();
			List<Club> clubes = new ArrayList<>();
			clubes = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, clubes.getClass());
			assertNotNull(clubes, assertMessage + " Se ha devuelto un nulo");
			assertTrue(!clubes.isEmpty(), "Se ha devuelto un listado de clubes vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el club
			if (clubId != null) {
				borrarClub(assertMessage, clubId, HttpStatus.OK);
			}

		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un club que no existe
	 */
	@Test
	void getClubNotFoundTest() {
		String assertMessage = "Test de obtencion de un club que no existe. ";
		Exception ex = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_CLUBES_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un club
	 */
	@Test
	void getClubTest() {
		String assertMessage = "Test de obtencion de un club. ";
		Exception ex = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, clubId);
			URI uri = getUri(ConstantesTests.URL_CLUBES_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el club
			if (clubId != null) {
				borrarClub(assertMessage, clubId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un club sin nombre
	 */
	@Test
	void createClubEmptyTest() {
		String assertMessage = "Test de creación de un club sin nombre. ";
		Exception ex = null;
		try {
			Club club = UtilTests.createClubObject(null);
			createClub(assertMessage, club, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un club
	 */
	@Test
	void createClubTest() {
		String assertMessage = "Test de creacion de un club. ";
		Exception ex = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el club
			if (clubId != null) {
				borrarClub(assertMessage, clubId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un club que no existe
	 */
	@Test
	void updateClubNotFoundTest() {
		String assertMessage = "Test de modificacion de un club que no existe. ";
		Exception ex = null;
		try {
			Club clubToUpdate = new Club();
			clubToUpdate.setClubId(ConstantesTests.MINUS_ONE.longValue());
			clubToUpdate.setClubName("Club2");
			HttpEntity<Club> request = new HttpEntity<>(clubToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_CLUBES_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un club sin nombre
	 */
	@Test
	void updateClubNoNameTest() {
		String assertMessage = "Test de modificacion de un club sin nombre. ";
		Exception ex = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			Club clubToUpdate = new Club();
			clubToUpdate.setClubId(clubId);
			HttpEntity<Club> request = new HttpEntity<>(clubToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, clubId);
			URI uri = getUri(ConstantesTests.URL_CLUBES_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el club
			if (clubId != null) {
				borrarClub(assertMessage, clubId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un club con ids diferentes
	 */
	@Test
	void updateClubDifferentIdTest() {
		String assertMessage = "Test de modificacion de un club con ids diferentes. ";
		Exception ex = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			Club clubToUpdate = new Club();
			clubToUpdate.setClubId(ConstantesTests.MINUS_ONE.longValue());
			clubToUpdate.setClubName("Club2");
			HttpEntity<Club> request = new HttpEntity<>(clubToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, clubId);
			URI uri = getUri(ConstantesTests.URL_CLUBES_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el club
			if (clubId != null) {
				borrarClub(assertMessage, clubId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un club
	 */
	@Test
	void updateClubTest() {
		String assertMessage = "Test de modificacion de un club. ";
		Exception ex = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			Club clubToUpdate = new Club();
			clubToUpdate.setClubId(clubId);
			clubToUpdate.setClubName("Club2");
			HttpEntity<Club> request = new HttpEntity<>(clubToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, clubId);
			URI uri = getUri(ConstantesTests.URL_CLUBES_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.PUT, request,
					Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el club
			if (clubId != null) {
				borrarClub(assertMessage, clubId, HttpStatus.OK);
			}

		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un club que no existe
	 */
	@Test
	void deleteClubNotFoundTest() {
		String assertMessage = "Test de borrado de un club que no existe. ";
		Exception ex = null;
		try {
			borrarClub(assertMessage, ConstantesTests.MINUS_ONE.longValue(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un club
	 */
	@Test
	void deleteClubTest() {
		String assertMessage = "Test de borrado de un club. ";
		Exception ex = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			borrarClub(assertMessage, clubId, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

}
