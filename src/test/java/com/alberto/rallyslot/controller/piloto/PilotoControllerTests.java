package com.alberto.rallyslot.controller.piloto;

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
import com.alberto.rallyslot.dto.piloto.Piloto;
import com.alberto.rallyslot.util.ConstantesTests;
import com.alberto.rallyslot.util.UtilTests;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PilotoControllerTests extends BaseControllerTests {

	private static final Log LOG = LogFactory.getLog(PilotoControllerTests.class);

	/**
	 * Test de obtención de los pilotos vacio
	 */
	@Test
	void getPilotosEmptyTest() {
		String assertMessage = "Test de obtencion de los pilotos vacio. ";
		Exception ex = null;
		try {
			// Preparamos los mocks
			mockPilotoGrpcClientFechtPilotoList(ConstantesTests.ZERO);

			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_PILOTOS).build().encode().toUri();
			List<Piloto> pilotos = new ArrayList<>();
			pilotos = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, pilotos.getClass());
			assertNotNull(pilotos, assertMessage + " Se ha devuelto un nulo");
			assertTrue(pilotos.isEmpty(), "Se ha devuelto un listado de pilotos no vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de los pilotos
	 */
	@Test
	void getPilotosTest() {
		String assertMessage = "Test de obtencion de los pilotos vacio. ";
		Exception ex = null;
		Long pilotoId = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);
			pilotoId = createPiloto(assertMessage);

			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_PILOTOS).build().encode().toUri();
			List<Piloto> pilotos = new ArrayList<>();
			pilotos = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, pilotos.getClass());
			assertNotNull(pilotos, assertMessage + " Se ha devuelto un nulo");
			assertTrue(!pilotos.isEmpty(), "Se ha devuelto un listado de pilotos vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el piloto
			if (pilotoId != null) {
				borrarPiloto(assertMessage, pilotoId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un piloto que no existe
	 */
	@Test
	void getPilotoNotFoundTest() {
		String assertMessage = "Test de obtencion de un piloto que no existe. ";
		Exception ex = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);

			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_PILOTOS_WITH_ID, pathParams, null);
			ResponseEntity<Piloto> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Piloto.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un piloto
	 */
	@Test
	void getPilotoTest() {
		String assertMessage = "Test de obtencion de un piloto. ";
		Exception ex = null;
		Long pilotoId = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);
			pilotoId = createPiloto(assertMessage);
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pilotoId);
			URI uri = getUri(ConstantesTests.URL_PILOTOS_WITH_ID, pathParams, null);
			ResponseEntity<Piloto> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Piloto.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el piloto
			if (pilotoId != null) {
				borrarPiloto(assertMessage, pilotoId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un piloto sin nombre
	 */
	@Test
	void createPilotoEmptyTest() {
		String assertMessage = "Test de creación de un piloto sin nombre. ";
		Exception ex = null;
		try {
			Piloto piloto = UtilTests.createPilotoObject(null);
			createPiloto(assertMessage, piloto, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un piloto
	 */
	@Test
	void createPilotoTest() {
		String assertMessage = "Test de creacion de un piloto. ";
		Exception ex = null;
		Long pilotoId = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);
			pilotoId = createPiloto(assertMessage);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el piloto
			if (pilotoId != null) {
				borrarPiloto(assertMessage, pilotoId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un piloto que no existe
	 */
	@Test
	void updatePilotoNotFoundTest() {
		String assertMessage = "Test de modificacion de un piloto que no existe. ";
		Exception ex = null;
		try {
			Piloto pilotoToUpdate = new Piloto();
			pilotoToUpdate.setPilotoId(ConstantesTests.MINUS_ONE.longValue());
			pilotoToUpdate.setPilotoName("Piloto2");
			HttpEntity<Piloto> request = new HttpEntity<>(pilotoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_PILOTOS_WITH_ID, pathParams, null);
			ResponseEntity<Piloto> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Piloto.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un piloto sin nombre
	 */
	@Test
	void updatePilotoNoNameTest() {
		String assertMessage = "Test de modificacion de un piloto sin nombre. ";
		Exception ex = null;
		Long pilotoId = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);
			pilotoId = createPiloto(assertMessage);
			Piloto pilotoToUpdate = new Piloto();
			pilotoToUpdate.setPilotoId(pilotoId);
			HttpEntity<Piloto> request = new HttpEntity<>(pilotoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pilotoId);
			URI uri = getUri(ConstantesTests.URL_PILOTOS_WITH_ID, pathParams, null);
			ResponseEntity<Piloto> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Piloto.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el piloto
			if (pilotoId != null) {
				borrarPiloto(assertMessage, pilotoId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un piloto con ids diferentes
	 */
	@Test
	void updatePilotoDifferentIdTest() {
		String assertMessage = "Test de modificacion de un piloto con ids diferentes. ";
		Exception ex = null;
		Long pilotoId = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);
			pilotoId = createPiloto(assertMessage);
			Piloto pilotoToUpdate = new Piloto();
			pilotoToUpdate.setPilotoId(ConstantesTests.MINUS_ONE.longValue());
			pilotoToUpdate.setPilotoName("Piloto2");
			HttpEntity<Piloto> request = new HttpEntity<>(pilotoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pilotoId);
			URI uri = getUri(ConstantesTests.URL_PILOTOS_WITH_ID, pathParams, null);
			ResponseEntity<Piloto> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Piloto.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el piloto
			if (pilotoId != null) {
				borrarPiloto(assertMessage, pilotoId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un piloto
	 */
	@Test
	void updatePilotoTest() {
		String assertMessage = "Test de modificacion de un piloto. ";
		Exception ex = null;
		Long pilotoId = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);
			pilotoId = createPiloto(assertMessage);
			Piloto pilotoToUpdate = new Piloto();
			pilotoToUpdate.setPilotoId(pilotoId);
			pilotoToUpdate.setPilotoName("Piloto2");
			HttpEntity<Piloto> request = new HttpEntity<>(pilotoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pilotoId);
			URI uri = getUri(ConstantesTests.URL_PILOTOS_WITH_ID, pathParams, null);
			ResponseEntity<Piloto> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.PUT, request,
					Piloto.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el piloto
			if (pilotoId != null) {
				borrarPiloto(assertMessage, pilotoId, HttpStatus.OK);
			}

		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un piloto que no existe
	 */
	@Test
	void deletePilotoNotFoundTest() {
		String assertMessage = "Test de borrado de un piloto que no existe. ";
		Exception ex = null;
		try {
			borrarPiloto(assertMessage, ConstantesTests.MINUS_ONE.longValue(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un piloto
	 */
	@Test
	void deletePilotoTest() {
		String assertMessage = "Test de borrado de un piloto. ";
		Exception ex = null;
		Long pilotoId = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);
			pilotoId = createPiloto(assertMessage);
			borrarPiloto(assertMessage, pilotoId, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

}
