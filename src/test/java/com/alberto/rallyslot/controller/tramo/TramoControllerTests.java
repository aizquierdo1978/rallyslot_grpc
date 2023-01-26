package com.alberto.rallyslot.controller.tramo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.alberto.rallyslot.controller.common.BaseControllerTests;
import com.alberto.rallyslot.controller.common.DatosTest;
import com.alberto.rallyslot.dto.club.Club;
import com.alberto.rallyslot.dto.tramo.Tramo;
import com.alberto.rallyslot.util.ConstantesTests;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TramoControllerTests extends BaseControllerTests {

	private static final Log LOG = LogFactory.getLog(TramoControllerTests.class);

	@BeforeEach
	void setup(){
		mockPilotoGrpcClient(ConstantesTests.ONE);
	}

	/**
	 * Test de obtención de los tramos vacio
	 */
	@Test
	void getTramosEmptyTest() {
		String assertMessage = "Test de obtencion de los tramos vacio. ";
		Exception ex = null;
		try {
			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_TRAMOS).build().encode().toUri();
			List<Tramo> tramos = new ArrayList<>();
			tramos = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, tramos.getClass());
			assertNotNull(tramos, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(tramos.isEmpty(), "Se ha devuelto un listado de tramos no vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de los tramos
	 */
	@Test
	void getTramosTest() {
		String assertMessage = "Test de obtencion de los tramos. ";
		Exception ex = null;
		DatosTest datos = new DatosTest();
		try {
			datos = generateData(assertMessage);
			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_TRAMOS).build().encode().toUri();
			List<Tramo> tramos = new ArrayList<>();
			tramos = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, tramos.getClass());
			assertNotNull(tramos, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!tramos.isEmpty(), "Se ha devuelto un listado de tramos no vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, datos.getClubId(), datos.getInscritoId(), datos.getPruebaId(),
					datos.getPilotoId());
		}


		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un tramo que no existe
	 */
	@Test
	void getTramoNotFoundTest() {
		String assertMessage = "Test de obtencion de un tramo que no existe. ";
		Exception ex = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI targetUrl = getUri(ConstantesTests.URL_TRAMOS_WITH_ID, pathParams, null);
			ResponseEntity<Tramo> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(targetUrl,
							HttpMethod.GET, request,
					Tramo.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un tramo
	 */
	@Test
	void getTramoTest() {
		String assertMessage = "Test de obtencion de un tramo. ";
		Exception ex = null;
		DatosTest datos = new DatosTest();
		try {
			datos = generateData(assertMessage);
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			Long tramoId = getTramoId(assertMessage);
			pathParams.put(ConstantesTests.ID_PATH_PARAM, tramoId);
			URI targetUrl = getUri(ConstantesTests.URL_TRAMOS_WITH_ID, pathParams, null);
			ResponseEntity<Tramo> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(targetUrl,
							HttpMethod.GET, request,
					Tramo.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, datos.getClubId(), datos.getInscritoId(), datos.getPruebaId(),
					datos.getPilotoId());
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de los tramos para un inscrito que no existe
	 */
	@Test
	void getTramosByInscritoNotFoundTest() {
		String assertMessage = "Test de obtencion de los tramos para un inscrito que no existe. ";
		Exception ex = null;
		try {
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.INSCRITO_ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI targetUrl = getUri(ConstantesTests.URL_TRAMOS_WITH_INSCRITO_ID, pathParams, null);
			ResponseEntity<Void> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForEntity(targetUrl, Void.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de los tramos para un inscrito
	 */
	@Test
	void getTramosByInscrito() {
		String assertMessage = "Test de obtencion de los tramos para un inscrito. ";
		Exception ex = null;
		DatosTest datos = new DatosTest();
		try {
			datos = generateData(assertMessage);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.INSCRITO_ID_PATH_PARAM, datos.getInscritoId());
			URI targetUrl = getUri(ConstantesTests.URL_TRAMOS_WITH_INSCRITO_ID, pathParams, null);
			ResponseEntity<Tramo[]> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForEntity(targetUrl, Tramo[].class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
			assertNotNull(response.getBody(), assertMessage + ConstantesTests.ERROR_NULL);
			Tramo[] tramos = response.getBody();

			assertTrue(tramos.length > ConstantesTests.ZERO, "Se ha devuelto un listado de tramos vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, datos.getClubId(), datos.getInscritoId(), datos.getPruebaId(),
					datos.getPilotoId());
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificación de un tramo que no existe
	 */
	@Test
	void updateTramoNotFoundTest() {
		String assertMessage = "Test de modificacion de un tramo que no existe. ";
		Exception ex = null;
		try {
			Tramo tramoToUpdate = new Tramo();
			tramoToUpdate.setTramoId(ConstantesTests.MINUS_ONE.longValue());
			tramoToUpdate.setTiempo(BigDecimal.ZERO);
			HttpEntity<Tramo> request = new HttpEntity<>(tramoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_TRAMOS_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.PUT, request, Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificación de un tramo con un id que no se corresponde
	 */
	@Test
	void updateTramoDifferentId() {
		String assertMessage = "Test de modificacion de un tramo con un id que no se corresponde. ";
		Exception ex = null;
		DatosTest datos = new DatosTest();
		try {
			datos = generateData(assertMessage);
			Tramo tramoToUpdate = getTramo(assertMessage);
			Long tramoId = tramoToUpdate.getTramoId();
			tramoToUpdate.setTramoId(Long.valueOf(tramoToUpdate.getTramoId().intValue() + ConstantesTests.ONE));
			tramoToUpdate.setTiempo(BigDecimal.ZERO);
			HttpEntity<Tramo> request = new HttpEntity<>(tramoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, tramoId);
			URI uri = getUri(ConstantesTests.URL_TRAMOS_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.PUT, request, Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, datos.getClubId(), datos.getInscritoId(), datos.getPruebaId(),
					datos.getPilotoId());
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificación de un tramo
	 */
	@Test
	void updateTramo() {
		String assertMessage = "Test de modificacion de un tramo. ";
		Exception ex = null;
		DatosTest datos = new DatosTest();
		try {
			datos = generateData(assertMessage);
			Tramo tramoToUpdate = getTramo(assertMessage);
			tramoToUpdate.setTiempo(BigDecimal.ZERO);
			HttpEntity<Tramo> request = new HttpEntity<>(tramoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, tramoToUpdate.getTramoId());
			URI uri = getUri(ConstantesTests.URL_TRAMOS_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.PUT, request, Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, datos.getClubId(), datos.getInscritoId(), datos.getPruebaId(),
					datos.getPilotoId());
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un tramo que no existe
	 */
	@Test
	void deleteTramoNotFoundTest() {
		String assertMessage = "Test de borrado de un tramo que no existe. ";
		Exception ex = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_TRAMOS_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.DELETE, request, Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un tramo
	 */
	@Test
	void deleteTramo() {
		String assertMessage = "Test de borrado de un tramo. ";
		Exception ex = null;
		DatosTest datos = new DatosTest();
		try {
			new HttpEntity<>(null);
			HttpEntity<Void> request = new HttpEntity<>(null);
			datos = generateData(assertMessage);
			Long tramoId = getTramoId(assertMessage);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, tramoId);
			URI uri = getUri(ConstantesTests.URL_TRAMOS_WITH_ID, pathParams, null);
			ResponseEntity<Club> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.DELETE, request, Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, datos.getClubId(), datos.getInscritoId(), datos.getPruebaId(),
					datos.getPilotoId());
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Genera los datos para el test.
	 *
	 * @param assertMessage the assert message
	 * @return los datos para el test
	 */
	private DatosTest generateData(String assertMessage) {
		DatosTest datosTest = new DatosTest();
		datosTest.setClubId(createClub(assertMessage));
		datosTest.setPilotoId(createPiloto(assertMessage));
		datosTest.setPruebaId(
				createPrueba(assertMessage, datosTest.getClubId(), ConstantesTests.ONE, ConstantesTests.SIX));
		datosTest.setInscritoId(createInscrito(assertMessage, datosTest.getPruebaId(), datosTest.getPilotoId(),
				getCategoriaId(assertMessage)));

		return datosTest;

	}


}
