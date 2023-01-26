package com.alberto.rallyslot.controller.categoria;

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
import com.alberto.rallyslot.dto.categoria.Categoria;
import com.alberto.rallyslot.dto.categoria.CategoriaEnum;
import com.alberto.rallyslot.util.ConstantesTests;
import com.alberto.rallyslot.util.UtilTests;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CategoriaControllerTests extends BaseControllerTests {

	private static final Log LOG = LogFactory.getLog(CategoriaControllerTests.class);

	/**
	 * Test de obtención de las categorias
	 */
	@Test
	void getCategoriasTest() {
		String assertMessage = "Test de obtencion de las categorias vacio. ";
		Exception ex = null;
		Long categoriaId = null;
		try {
			categoriaId = createCategoria(assertMessage);

			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_CATEGORIAS).build().encode().toUri();
			List<Categoria> categorias = new ArrayList<>();
			categorias = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.getForObject(targetUrl, categorias.getClass());
			assertNotNull(categorias, assertMessage + " Se ha devuelto un nulo");
			assertTrue(!categorias.isEmpty(), "Se ha devuelto un listado de categorias vacio");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el categoria
			if (categoriaId != null) {
				borrarCategoria(assertMessage, categoriaId, HttpStatus.OK);
			}

		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un categoria que no existe
	 */
	@Test
	void getCategoriaNotFoundTest() {
		String assertMessage = "Test de obtencion de un categoria que no existe. ";
		Exception ex = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS_WITH_ID, pathParams, null);
			ResponseEntity<Categoria> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Categoria.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de obtención de un categoria
	 */
	@Test
	void getCategoriaTest() {
		String assertMessage = "Test de obtencion de un categoria. ";
		Exception ex = null;
		Long categoriaId = null;
		try {
			categoriaId = createCategoria(assertMessage);
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, categoriaId);
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS_WITH_ID, pathParams, null);
			ResponseEntity<Categoria> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.GET, request,
					Categoria.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el categoria
			if (categoriaId != null) {
				borrarCategoria(assertMessage, categoriaId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un categoria sin nombre
	 */
	@Test
	void createCategoriaEmptyTest() {
		String assertMessage = "Test de creación de un categoria sin nombre. ";
		Exception ex = null;
		try {
			Categoria categoria = UtilTests.createCategoriaObject(null, null);
			createCategoria(assertMessage, categoria, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de creación de un categoria
	 */
	@Test
	void createCategoriaTest() {
		String assertMessage = "Test de creacion de un categoria. ";
		Exception ex = null;
		Long categoriaId = null;
		try {
			categoriaId = createCategoria(assertMessage);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el categoria
			if (categoriaId != null) {
				borrarCategoria(assertMessage, categoriaId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un categoria que no existe
	 */
	@Test
	void updateCategoriaNotFoundTest() {
		String assertMessage = "Test de modificacion de un categoria que no existe. ";
		Exception ex = null;
		try {
			Categoria categoriaToUpdate = new Categoria();
			categoriaToUpdate.setCategoriaId(ConstantesTests.MINUS_ONE.longValue());
			categoriaToUpdate.setCategoriaName("Categoria2");
			HttpEntity<Categoria> request = new HttpEntity<>(categoriaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, ConstantesTests.MINUS_ONE);
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS_WITH_ID, pathParams, null);
			ResponseEntity<Categoria> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Categoria.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 404");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un categoria sin nombre
	 */
	@Test
	void updateCategoriaNoNameTest() {
		String assertMessage = "Test de modificacion de un categoria sin nombre. ";
		Exception ex = null;
		Long categoriaId = null;
		try {
			categoriaId = createCategoria(assertMessage);
			Categoria categoriaToUpdate = new Categoria();
			categoriaToUpdate.setCategoriaId(categoriaId);
			HttpEntity<Categoria> request = new HttpEntity<>(categoriaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, categoriaId);
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS_WITH_ID, pathParams, null);
			ResponseEntity<Categoria> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Categoria.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el categoria
			if (categoriaId != null) {
				borrarCategoria(assertMessage, categoriaId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un categoria con código incorrecto
	 */
	@Test
	void updateCategoriaIncorrectCodeTest() {
		String assertMessage = "Test de modificacion de una categoria con codigo incorrecto. ";
		Exception ex = null;
		Long categoriaId = null;
		try {
			categoriaId = createCategoria(assertMessage);
			Categoria categoriaToUpdate = new Categoria();
			categoriaToUpdate.setCategoriaId(categoriaId);
			categoriaToUpdate.setCategoriaCodigo(ConstantesTests.INCORRECT_CATEGORY);
			categoriaToUpdate.setCategoriaName(CategoriaEnum.WRC.getNombre());
			HttpEntity<Categoria> request = new HttpEntity<>(categoriaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, categoriaId);
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS_WITH_ID, pathParams, null);
			ResponseEntity<Categoria> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Categoria.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el categoria
			if (categoriaId != null) {
				borrarCategoria(assertMessage, categoriaId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un categoria con ids diferentes
	 */
	@Test
	void updateCategoriaDifferentIdTest() {
		String assertMessage = "Test de modificacion de un categoria con ids diferentes. ";
		Exception ex = null;
		Long categoriaId = null;
		try {
			categoriaId = createCategoria(assertMessage);
			Categoria categoriaToUpdate = new Categoria();
			categoriaToUpdate.setCategoriaId(ConstantesTests.MINUS_ONE.longValue());
			categoriaToUpdate.setCategoriaName("Categoria2");
			HttpEntity<Categoria> request = new HttpEntity<>(categoriaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, categoriaId);
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS_WITH_ID, pathParams, null);
			ResponseEntity<Categoria> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(uri,
							HttpMethod.PUT, request,
					Categoria.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 400");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el categoria
			if (categoriaId != null) {
				borrarCategoria(assertMessage, categoriaId, HttpStatus.OK);
			}
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de modificacion de un categoria
	 */
	@Test
	void updateCategoriaTest() {
		String assertMessage = "Test de modificacion de un categoria. ";
		Exception ex = null;
		Long categoriaId = null;
		try {
			categoriaId = createCategoria(assertMessage);
			Categoria categoriaToUpdate = new Categoria();
			categoriaToUpdate.setCategoriaId(categoriaId);
			categoriaToUpdate.setCategoriaCodigo(CategoriaEnum.A.getCodigo());
			categoriaToUpdate.setCategoriaName("Categoria2");
			HttpEntity<Categoria> request = new HttpEntity<>(categoriaToUpdate);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, categoriaId);
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS_WITH_ID, pathParams, null);
			ResponseEntity<Categoria> response = this.getRestTemplate()
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.PUT, request,
					Categoria.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		} finally {
			// Se borra el categoria
			if (categoriaId != null) {
				borrarCategoria(assertMessage, categoriaId, HttpStatus.OK);
			}

		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un categoria que no existe
	 */
	@Test
	void deleteCategoriaNotFoundTest() {
		String assertMessage = "Test de borrado de un categoria que no existe. ";
		Exception ex = null;
		try {
			borrarCategoria(assertMessage, ConstantesTests.MINUS_ONE.longValue(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}
		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de borrado de un categoria
	 */
	@Test
	void deleteCategoriaTest() {
		String assertMessage = "Test de borrado de un categoria. ";
		Exception ex = null;
		Long categoriaId = null;
		try {
			categoriaId = createCategoria(assertMessage);
			borrarCategoria(assertMessage, categoriaId, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

}
