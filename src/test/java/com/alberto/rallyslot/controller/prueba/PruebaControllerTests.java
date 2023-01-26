package com.alberto.rallyslot.controller.prueba;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.alberto.rallyslot.controller.common.BaseControllerTests;
import com.alberto.rallyslot.dto.prueba.Prueba;
import com.alberto.rallyslot.util.ConstantesTests;
import com.alberto.rallyslot.util.UtilTests;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PruebaControllerTests extends BaseControllerTests {

	private static final Log LOG = LogFactory.getLog(PruebaControllerTests.class);
	
	/**
	 * Test de obtención de las pruebas vacio
	 */
	@Test
	void getPruebasEmptyTest() {
		String assertMessage = "Test de obtencion de las pruebas vacio. ";
		Exception ex = null;
		try {
			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_PRUEBAS).build().encode().toUri();
			List<Prueba> pruebas = new ArrayList<>();
			pruebas = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, pruebas.getClass());
			assertNotNull(pruebas, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(pruebas.isEmpty(), "Se ha devuelto un listado de pruebas no vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de las pruebas
	 */
	@SuppressWarnings("unchecked")
	@Test
	void getPruebasTest() {
		String assertMessage = "Test de obtencion de las pruebas vacio. ";
		Exception ex = null;
		Long pruebaId = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);

			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_PRUEBAS).build().encode().toUri();
			List<Prueba> pruebas = new ArrayList<>();
			pruebas = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, pruebas.getClass());
			assertNotNull(pruebas, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!pruebas.isEmpty(), "Se ha devuelto un listado de pruebas vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, pruebaId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un prueba que no existe
	 */
	@Test
	void getPruebaNotFoundTest() {
		String assertMessage = "Test de obtencion de un prueba que no existe. ";
		Exception ex = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_PRUEBAS_WITH_ID, pathParams, null);
			ResponseEntity<Prueba> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Prueba.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un prueba
	 */
	@Test
	void getPruebaTest() {
		String assertMessage = "Test de obtencion de un prueba. ";
		Exception ex = null;
		Long pruebaId = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pruebaId);
			URI uri = getUri(ConstantesTests.URL_PRUEBAS_WITH_ID, pathParams, null);
			ResponseEntity<Prueba> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.withBasicAuth("test", "test").exchange(uri,
					HttpMethod.GET, request,
					Prueba.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, pruebaId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un prueba sin nombre
	 */
	@Test
	void createPruebaEmptyTest() {
		String assertMessage = "Test de creación de un prueba sin nombre. ";
		Exception ex = null;
		try {
			Prueba prueba = UtilTests.createPruebaObject(null, null, null, null, null);
			createPrueba(assertMessage, prueba, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un prueba
	 */
	@Test
	void createPruebaTest() {
		String assertMessage = "Test de creacion de un prueba. ";
		Exception ex = null;
		Long pruebaId = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, pruebaId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un prueba que no existe
	 */
	@Test
	void updatePruebaNotFoundTest() {
		String assertMessage = "Test de modificacion de un prueba que no existe. ";
		Exception ex = null;
		try {
			Prueba pruebaToUpdate = new Prueba();
			pruebaToUpdate.setPruebaId(ConstantesTests.MINUS_ONE.longValue());
			pruebaToUpdate.setAno(ConstantesTests.YEAR);
			pruebaToUpdate.setPruebaName("Prueba2");
			HttpEntity<Prueba> request = new HttpEntity<>(pruebaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_PRUEBAS_WITH_ID, pathParams, null);
			ResponseEntity<Prueba> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Prueba.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un prueba sin nombre
	 */
	@Test
	void updatePruebaNoDataTest() {
		String assertMessage = "Test de modificacion de un prueba sin nombre. ";
		Exception ex = null;
		Long pruebaId = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			Prueba pruebaToUpdate = new Prueba();
			pruebaToUpdate.setPruebaId(pruebaId);
			HttpEntity<Prueba> request = new HttpEntity<>(pruebaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pruebaId);
			URI uri = getUri(ConstantesTests.URL_PRUEBAS_WITH_ID, pathParams, null);
			ResponseEntity<Prueba> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Prueba.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, pruebaId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un prueba con ids diferentes
	 */
	@Test
	void updatePruebaDifferentIdTest() {
		String assertMessage = "Test de modificacion de un prueba con ids diferentes. ";
		Exception ex = null;
		Long pruebaId = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			Prueba pruebaToUpdate = new Prueba();
			pruebaToUpdate.setPruebaId(ConstantesTests.MINUS_ONE.longValue());
			pruebaToUpdate.setPruebaName("Prueba2");
			HttpEntity<Prueba> request = new HttpEntity<>(pruebaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pruebaId);
			URI uri = getUri(ConstantesTests.URL_PRUEBAS_WITH_ID, pathParams, null);
			ResponseEntity<Prueba> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Prueba.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, pruebaId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un prueba
	 */
	@Test
	void updatePruebaTest() {
		String assertMessage = "Test de modificacion de un prueba. ";
		Exception ex = null;
		Long pruebaId = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			Prueba pruebaToUpdate = new Prueba();
			pruebaToUpdate.setPruebaId(pruebaId);
			pruebaToUpdate.setAno(ConstantesTests.YEAR);
			pruebaToUpdate.setClubId(clubId);
			pruebaToUpdate.setPruebaName("Prueba2");
			pruebaToUpdate.setSecciones(ConstantesTests.THREE);
			pruebaToUpdate.setTramos(ConstantesTests.TEN);
			HttpEntity<Prueba> request = new HttpEntity<>(pruebaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pruebaId);
			URI uri = getUri(ConstantesTests.URL_PRUEBAS_WITH_ID, pathParams, null);
			ResponseEntity<Prueba> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.PUT, request,
					Prueba.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, pruebaId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un prueba que no existe
	 */
	@Test
	void deletePruebaNotFoundTest() {
		String assertMessage = "Test de borrado de un prueba que no existe. ";
		Exception ex = null;
		try {
			borrarPrueba(assertMessage, ConstantesTests.MINUS_ONE.longValue(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un prueba
	 */
	@Test
	void deletePruebaTest() {
		String assertMessage = "Test de borrado de un prueba. ";
		Exception ex = null;
		Long pruebaId = null;
		Long clubId = null;
		try {
			clubId = createClub(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			borrarPrueba(assertMessage, pruebaId, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			deleteData(assertMessage, clubId, null);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Borra los datos.
	 *
	 * @param assertMessage el texto del assert
	 * @param clubId        el identificador del club
	 * @param pruebaId      el identificador de la prueba
	 */
	private void deleteData(String assertMessage, Long clubId, Long pruebaId) {
		// Se borra la prueba
		if (pruebaId != null) {
			borrarPrueba(assertMessage, pruebaId, HttpStatus.OK);
		}
		// se borra el club
		if (clubId != null) {
			borrarClub(assertMessage, clubId, HttpStatus.OK);
		}
	}

}
