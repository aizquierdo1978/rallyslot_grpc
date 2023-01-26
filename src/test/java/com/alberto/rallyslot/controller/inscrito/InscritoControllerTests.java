package com.alberto.rallyslot.controller.inscrito;

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
import org.junit.jupiter.api.BeforeAll;
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
import com.alberto.rallyslot.dto.inscrito.Inscrito;
import com.alberto.rallyslot.util.ConstantesTests;
import com.alberto.rallyslot.util.UtilTests;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class InscritoControllerTests extends BaseControllerTests {

	private static final Log LOG = LogFactory.getLog(InscritoControllerTests.class);

	@BeforeEach
	void setup(){
		mockPilotoGrpcClient(ConstantesTests.ONE);
	}

	/**
	 * Test de obtención de los inscritos vacio
	 */
	@Test
	void getInscritosEmptyTest() {
		String assertMessage = "Test de obtencion de los inscritos vacio. ";
		Exception ex = null;
		try {
			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_INSCRITOS).build().encode().toUri();
			List<Inscrito> inscritos = new ArrayList<>();
			inscritos = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, inscritos.getClass());
			assertNotNull(inscritos, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(inscritos.isEmpty(), "Se ha devuelto un listado de inscritos no vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de los inscritos
	 */
	@Test
	void getInscritosTest() {
		String assertMessage = "Test de obtencion de los inscritos. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		try {
			mockPilotoGrpcClient(ConstantesTests.ONE);
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, getCategoriaId(assertMessage));

			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_INSCRITOS).build().encode().toUri();
			List<Inscrito> inscritos = new ArrayList<>();
			inscritos = this.getRestTemplate().withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, inscritos.getClass());
			assertNotNull(inscritos, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!inscritos.isEmpty(), "Se ha devuelto un listado de inscritos vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un inscrito que no existe
	 */
	@Test
	void getInscritoNotFoundTest() {
		String assertMessage = "Test de obtencion de un inscrito que no existe. ";
		Exception ex = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un inscrito
	 */
	@Test
	void getInscritoTest() {
		String assertMessage = "Test de obtencion de un inscrito. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, getCategoriaId(assertMessage));
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, inscritoId);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un inscrito sin nombre
	 */
	@Test
	void createInscritoEmptyTest() {
		String assertMessage = "Test de creación de un inscrito sin nombre. ";
		Exception ex = null;
		try {
			Inscrito inscrito = UtilTests.createInscritoObject(null, null, null);
			createInscrito(assertMessage, inscrito, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un inscrito
	 */
	@Test
	void createInscritoTest() {
		String assertMessage = "Test de creacion de un inscrito. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, getCategoriaId(assertMessage));

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un inscrito que no existe
	 */
	@Test
	void updateInscritoNotFoundTest() {
		String assertMessage = "Test de modificacion de un inscrito que no existe. ";
		Exception ex = null;
		try {
			Inscrito inscritoToUpdate = new Inscrito();
			inscritoToUpdate.setInscritoId(ConstantesTests.MINUS_ONE.longValue());
			HttpEntity<Inscrito> request = new HttpEntity<>(inscritoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un inscrito sin nombre
	 */
	@Test
	void updateInscritoNoDataTest() {
		String assertMessage = "Test de modificacion de un inscrito sin nombre. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, getCategoriaId(assertMessage));
			Inscrito inscritoToUpdate = new Inscrito();
			inscritoToUpdate.setInscritoId(inscritoId);
			HttpEntity<Inscrito> request = new HttpEntity<>(inscritoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, inscritoId);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un inscrito con una prueba que no existe
	 */
	@Test
	void updateInscritoPruebaNotExistTest() {
		String assertMessage = "Test de modificacion de un inscrito con una prueba que no existe. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		Long categoriaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			categoriaId = getCategoriaId(assertMessage);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, categoriaId);
			Inscrito inscritoToUpdate = new Inscrito();
			inscritoToUpdate.setInscritoId(inscritoId);
			inscritoToUpdate.setPruebaId(ConstantesTests.MINUS_ONE.longValue());
			inscritoToUpdate.setPilotoId(pilotoId);
			inscritoToUpdate.setCategoriaId(categoriaId);
			HttpEntity<Inscrito> request = new HttpEntity<>(inscritoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, inscritoId);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un inscrito con un piloto que no existe
	 */
	@Test
	void updateInscritoPilotoNotExistTest() {
		String assertMessage = "Test de modificacion de un inscrito con un piloto que no existe. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		Long categoriaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			categoriaId = getCategoriaId(assertMessage);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, categoriaId);
			Inscrito inscritoToUpdate = new Inscrito();
			inscritoToUpdate.setInscritoId(inscritoId);
			inscritoToUpdate.setPilotoId(ConstantesTests.MINUS_ONE.longValue());
			inscritoToUpdate.setPruebaId(pruebaId);
			inscritoToUpdate.setCategoriaId(categoriaId);
			HttpEntity<Inscrito> request = new HttpEntity<>(inscritoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, inscritoId);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un inscrito con una categoría que no existe
	 */
	@Test
	void updateInscritoCategoriaNotExistTest() {
		String assertMessage = "Test de modificacion de un inscrito con una categoria que no existe. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		Long categoriaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			categoriaId = getCategoriaId(assertMessage);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, categoriaId);
			Inscrito inscritoToUpdate = new Inscrito();
			inscritoToUpdate.setInscritoId(inscritoId);
			inscritoToUpdate.setPilotoId(pilotoId);
			inscritoToUpdate.setPruebaId(pruebaId);
			inscritoToUpdate.setCategoriaId(ConstantesTests.MINUS_ONE.longValue());
			HttpEntity<Inscrito> request = new HttpEntity<>(inscritoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, inscritoId);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un inscrito con ids diferentes
	 */
	@Test
	void updateInscritoDifferentIdTest() {
		String assertMessage = "Test de modificacion de un inscrito con ids diferentes. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, getCategoriaId(assertMessage));
			Inscrito inscritoToUpdate = new Inscrito();
			inscritoToUpdate.setInscritoId(ConstantesTests.MINUS_ONE.longValue());
			inscritoToUpdate.setPilotoId(pilotoId);
			inscritoToUpdate.setPruebaId(pruebaId);
			HttpEntity<Inscrito> request = new HttpEntity<>(inscritoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, inscritoId);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un inscrito
	 */
	@Test
	void updateInscritoTest() {
		String assertMessage = "Test de modificacion de un inscrito. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		Long categoriaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			categoriaId = getCategoriaId(assertMessage);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, categoriaId);
			Inscrito inscritoToUpdate = new Inscrito();
			inscritoToUpdate.setInscritoId(inscritoId);
			inscritoToUpdate.setPruebaId(pruebaId);
			inscritoToUpdate.setPilotoId(pilotoId);
			inscritoToUpdate.setCategoriaId(categoriaId);
			HttpEntity<Inscrito> request = new HttpEntity<>(inscritoToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, inscritoId);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);
			ResponseEntity<Inscrito> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.PUT, request,
					Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borran los datos creados
			deleteData(assertMessage, clubId, inscritoId, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un inscrito que no existe
	 */
	@Test
	void deleteInscritoNotFoundTest() {
		String assertMessage = "Test de borrado de un inscrito que no existe. ";
		Exception ex = null;
		try {
			borrarInscrito(assertMessage, ConstantesTests.MINUS_ONE.longValue(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un inscrito
	 */
	@Test
	void deleteInscritoTest() {
		String assertMessage = "Test de borrado de un inscrito. ";
		Exception ex = null;
		Long inscritoId = null;
		Long clubId = null;
		Long pilotoId = null;
		Long pruebaId = null;
		try {
			clubId = createClub(assertMessage);
			pilotoId = createPiloto(assertMessage);
			pruebaId = createPrueba(assertMessage, clubId, ConstantesTests.ONE, ConstantesTests.SIX);
			inscritoId = createInscrito(assertMessage, pruebaId, pilotoId, getCategoriaId(assertMessage));
			borrarInscrito(assertMessage, inscritoId, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			deleteData(assertMessage, clubId, null, pruebaId, pilotoId);
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

}
