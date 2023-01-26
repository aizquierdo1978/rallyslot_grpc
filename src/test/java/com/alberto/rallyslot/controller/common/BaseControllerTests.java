package com.alberto.rallyslot.controller.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.URI;
import java.util.*;

import com.alberto.rallyslot.entity.piloto.PilotoEntity;
import com.alberto.rallyslot.grpc.client.piloto.PilotoGrpcClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.alberto.rallyslot.dto.categoria.Categoria;
import com.alberto.rallyslot.dto.categoria.CategoriaEnum;
import com.alberto.rallyslot.dto.club.Club;
import com.alberto.rallyslot.dto.inscrito.Inscrito;
import com.alberto.rallyslot.dto.piloto.Piloto;
import com.alberto.rallyslot.dto.prueba.Prueba;
import com.alberto.rallyslot.dto.tramo.Tramo;
import com.alberto.rallyslot.util.ConstantesTests;
import com.alberto.rallyslot.util.UtilTests;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BaseControllerTests {

	private static final Log LOG = LogFactory.getLog(BaseControllerTests.class);

	@MockBean
	PilotoGrpcClient pilotoGrpcClient;

	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * Creación de un club
	 */
	public ResponseEntity<Club> createClub(String assertMessage, Club club, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<Club> response = null;
		try {
			URI uri = getUri(ConstantesTests.URL_CLUBES, null, null);
			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.postForEntity(uri, club, Club.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Eliminación de un club
	 */
	public ResponseEntity<String> borrarClub(String assertMessage, Long clubId, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<String> response = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, clubId);
			URI uri = getUri(ConstantesTests.URL_CLUBES_WITH_ID, pathParams, null);

			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.DELETE, request,
					String.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(),
					assertMessage + " Se esperaba un HTTP " + expectedStatus.name());
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Crea un club.
	 *
	 * @param assertMessage the assert message
	 * @return el identificador del piloto
	 */
	public Long createClub(String assertMessage) {
		String clubname = "Club";
		Club club = UtilTests.createClubObject(clubname);
		ResponseEntity<Club> response = createClub(assertMessage, club, HttpStatus.OK);
		assertNotNull(response.getBody(), assertMessage + "Se ha devuelto un club nulo");
		Club clubResponse = response.getBody();
		assertNotNull(clubResponse.getClubName(), assertMessage + "Se ha devuelto un nombre de club nulo");
		assertEquals(clubname, clubResponse.getClubName(),
				assertMessage + " Se ha devuelto un nombre de club diferente");
		return clubResponse.getClubId();
	}

	/**
	 * Creación de un piloto
	 */
	public ResponseEntity<Piloto> createPiloto(String assertMessage, Piloto piloto, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<Piloto> response = null;
		try {
			URI uri = getUri(ConstantesTests.URL_PILOTOS, null, null);
			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.postForEntity(uri, piloto, Piloto.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Eliminación de un piloto
	 */
	public ResponseEntity<String> borrarPiloto(String assertMessage, Long pilotoId, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<String> response = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pilotoId);
			URI uri = getUri(ConstantesTests.URL_PILOTOS_WITH_ID, pathParams, null);

			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.DELETE, request,
					String.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(),
					assertMessage + " Se esperaba un HTTP " + expectedStatus.name());
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Crea un piloto.
	 *
	 * @param assertMessage the assert message
	 * @return el identificador del piloto
	 */
	public Long createPiloto(String assertMessage) {
		String pilotoname = ConstantesTests.PILOTO_NAME;
		Piloto piloto = UtilTests.createPilotoObject(pilotoname);
		ResponseEntity<Piloto> response = createPiloto(assertMessage, piloto, HttpStatus.OK);
		assertNotNull(response.getBody(), assertMessage + "Se ha devuelto un piloto nulo");
		Piloto pilotoResponse = response.getBody();
		assertNotNull(pilotoResponse.getPilotoName(), assertMessage + "Se ha devuelto un nombre de piloto nulo");
		assertEquals(pilotoname, pilotoResponse.getPilotoName(),
				assertMessage + " Se ha devuelto un nombre de piloto diferente");
		return pilotoResponse.getPilotoId();
	}

	/**
	 * Creación de una prueba
	 */
	public ResponseEntity<Prueba> createPrueba(String assertMessage, Prueba prueba, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<Prueba> response = null;
		try {
			URI uri = getUri(ConstantesTests.URL_PRUEBAS, null, null);
			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.postForEntity(uri, prueba, Prueba.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Eliminación de una prueba
	 */
	public ResponseEntity<String> borrarPrueba(String assertMessage, Long pruebaId, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<String> response = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, pruebaId);
			URI uri = getUri(ConstantesTests.URL_PRUEBAS_WITH_ID, pathParams, null);

			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.DELETE, request,
					String.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(),
					assertMessage + " Se esperaba un HTTP " + expectedStatus.name());
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Crea una prueba.
	 *
	 * @param assertMessage the assert message
	 * @return el identificador del prueba
	 */
	public Long createPrueba(String assertMessage, Long clubId, Integer secciones, Integer tramos) {
		String pruebaname = "Prueba";
		Prueba prueba = UtilTests.createPruebaObject(clubId, ConstantesTests.YEAR, pruebaname, secciones, tramos);
		ResponseEntity<Prueba> response = createPrueba(assertMessage, prueba, HttpStatus.OK);
		assertNotNull(response.getBody(), assertMessage + "Se ha devuelto una prueba nulo");
		Prueba pruebaResponse = response.getBody();
		assertNotNull(pruebaResponse.getPruebaName(), assertMessage + "Se ha devuelto un nombre de prueba nulo");
		assertEquals(pruebaname, pruebaResponse.getPruebaName(),
				assertMessage + " Se ha devuelto un nombre de prueba diferente");
		return pruebaResponse.getPruebaId();
	}

	/**
	 * Creación de un inscrito
	 */
	public ResponseEntity<Inscrito> createInscrito(String assertMessage, Inscrito inscrito, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<Inscrito> response = null;
		try {
			URI uri = getUri(ConstantesTests.URL_INSCRITOS, null, null);
			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.postForEntity(uri, inscrito, Inscrito.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Eliminación de un inscrito
	 */
	public ResponseEntity<String> borrarInscrito(String assertMessage, Long inscritoId, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<String> response = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, inscritoId);
			URI uri = getUri(ConstantesTests.URL_INSCRITOS_WITH_ID, pathParams, null);

			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.DELETE, request,
					String.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(),
					assertMessage + " Se esperaba un HTTP " + expectedStatus.name());
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Crea un inscrito.
	 *
	 * @param assertMessage the assert message
	 * @return el identificador del inscrito
	 */
	public Long createInscrito(String assertMessage, Long pruebaId, Long pilotoId, Long categoriaId) {
		Inscrito inscrito = UtilTests.createInscritoObject(pruebaId, pilotoId, categoriaId);
		ResponseEntity<Inscrito> response = createInscrito(assertMessage, inscrito, HttpStatus.OK);
		assertNotNull(response.getBody(), assertMessage + "Se ha devuelto un inscrito nulo");
		Inscrito inscritoResponse = response.getBody();
		assertNotNull(inscritoResponse.getPilotoId(), assertMessage + "Se ha devuelto un piloto nulo");
		assertNotNull(inscritoResponse.getPruebaId(), assertMessage + "Se ha devuelto una prueba nula");
		assertEquals(pruebaId, inscritoResponse.getPruebaId(), assertMessage + " Se ha devuelto una prueba diferente");
		assertEquals(pilotoId, inscritoResponse.getPilotoId(), assertMessage + " Se ha devuelto un piloto diferente");
		return inscritoResponse.getInscritoId();
	}

	/**
	 * Creación de una categoría
	 */
	public ResponseEntity<Categoria> createCategoria(String assertMessage, Categoria categoria,
			HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<Categoria> response = null;
		try {
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS, null, null);
			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.postForEntity(uri, categoria, Categoria.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Eliminación de una categoría
	 */
	public ResponseEntity<String> borrarCategoria(String assertMessage, Long categoriaId, HttpStatus expectedStatus) {
		Exception ex = null;
		ResponseEntity<String> response = null;
		try {
			HttpEntity<Void> request = new HttpEntity<>(null);
			Map<String, Object> pathParams = new HashMap<>();
			pathParams.put(ConstantesTests.ID_PATH_PARAM, categoriaId);
			URI uri = getUri(ConstantesTests.URL_CATEGORIAS_WITH_ID, pathParams, null);

			response = this.restTemplate.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(uri, HttpMethod.DELETE, request,
					String.class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(expectedStatus, response.getStatusCode(),
					assertMessage + " Se esperaba un HTTP " + expectedStatus.name());
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
		return response;
	}

	/**
	 * Crea una categoría.
	 *
	 * @param assertMessage the assert message
	 * @return el identificador de la categoría
	 */
	public Long createCategoria(String assertMessage) {
		String categorianame = "Categoria";
		String categoriaCodigo = CategoriaEnum.WRC.getCodigo();
		Categoria categoria = UtilTests.createCategoriaObject(categorianame, categoriaCodigo);
		ResponseEntity<Categoria> response = createCategoria(assertMessage, categoria, HttpStatus.OK);
		assertNotNull(response.getBody(), assertMessage + "Se ha devuelto una categoría nulo");
		Categoria categoriaResponse = response.getBody();
		assertNotNull(categoriaResponse.getCategoriaName(),
				assertMessage + "Se ha devuelto un nombre de categoria nulo");
		assertEquals(categorianame, categoriaResponse.getCategoriaName(),
				assertMessage + " Se ha devuelto un nombre de categoria diferente");
		return categoriaResponse.getCategoriaId();
	}

	/**
	 * Obtiene un identificador de categoría existente.
	 *
	 * @param assertMessage el mensaje de assert
	 * @return un identificador de categoría existente
	 */
	public Long getCategoriaId(String assertMessage) {
		Exception ex = null;
		Long categoriaId = null;
		try {
			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_CATEGORIAS).build().encode().toUri();
			ResponseEntity<Categoria[]> response = this.restTemplate
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS).exchange(targetUrl,
					HttpMethod.GET,
					null,
					Categoria[].class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
			Categoria[] categorias = response.getBody();
			assertNotNull(categorias, assertMessage + " Se ha devuelto un nulo");
			assertNotEquals(ConstantesTests.ZERO, categorias.length, "Se ha devuelto un listado de categorias vacio");
			categoriaId = categorias[ConstantesTests.ZERO].getCategoriaId();
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);

		return categoriaId;
	}

	/**
	 * Obtiene un identificador de tramo existente.
	 *
	 * @param assertMessage el mensaje de assert
	 * @return un identificador de tramo existente
	 */
	public Long getTramoId(String assertMessage) {
		Tramo tramo = getTramo(assertMessage);
		return tramo.getTramoId();
	}

	/**
	 * Obtiene un tramo existente.
	 *
	 * @param assertMessage el mensaje de assert
	 * @return un tramo existente
	 */
	public Tramo getTramo(String assertMessage) {
		Exception ex = null;
		Tramo tramo = null;
		try {
			URI targetUrl = UriComponentsBuilder.fromUriString(ConstantesTests.URL_TRAMOS).build().encode().toUri();
			ResponseEntity<Tramo[]> response = this.restTemplate
					.withBasicAuth(ConstantesTests.USER_TESTS, ConstantesTests.PASSWORD_TESTS)
					.exchange(targetUrl,
					HttpMethod.GET, null,
					Tramo[].class);
			assertNotNull(response, assertMessage + ConstantesTests.ERROR_NULL);
			assertEquals(HttpStatus.OK, response.getStatusCode(), assertMessage + " Se esperaba un HTTP 200");
			Tramo[] tramos = response.getBody();
			assertNotNull(tramos, assertMessage + " Se ha devuelto un nulo");
			assertNotEquals(ConstantesTests.ZERO, tramos.length, "Se ha devuelto un listado de tramos vacio");
			tramo = tramos[ConstantesTests.ZERO];
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);

		return tramo;
	}

	/**
	 * Borra los datos.
	 *
	 * @param assertMessage el texto del assert
	 * @param clubId        el identificador del club
	 * @param inscritoId    el identificador de la inscrito
	 */
	public void deleteData(String assertMessage, Long clubId, Long inscritoId, Long pruebaId, Long pilotoId) {
		// Se borra el inscrito
		if (inscritoId != null) {
			borrarInscrito(assertMessage, inscritoId, HttpStatus.OK);
		}
		// Se borra la prueba
		if (pruebaId != null) {
			borrarPrueba(assertMessage, pruebaId, HttpStatus.OK);
		}
		// se borra el club
		if (clubId != null) {
			borrarClub(assertMessage, clubId, HttpStatus.OK);
		}
		// se borra el club
		if (pilotoId != null) {
			borrarPiloto(assertMessage, pilotoId, HttpStatus.OK);
		}
	}

	/**
	 * Obtiene el uri.
	 *
	 * @param url         la url
	 * @param pathParams  los parámetros del path
	 * @param queryParams los parámetros del query
	 * @return el uri
	 */
	public URI getUri(String url, Map<String, Object> pathParams, MultiValueMap<String, String> queryParams) {
		URI uri = null;
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(url);

		// Si hay parámetros en la URL
		if (pathParams != null && !pathParams.isEmpty()) {
			uriComponentsBuilder.uriVariables(pathParams);
		}

		uri = uriComponentsBuilder.build().toUri();

		if (queryParams != null && !queryParams.isEmpty()) {
			uri = UriComponentsBuilder.fromUri(uri).queryParams(queryParams).build().toUri();
		}

		return uri;
	}

	/**
	 * Obtiene el rest template.
	 *
	 * @return el rest template
	 */
	protected TestRestTemplate getRestTemplate() {
		return restTemplate;
	}

	protected void mockPilotoGrpcClientFechtPilotoList(int numberOfPilots) {
		List<PilotoEntity> pilotoList = createPilotoEntityList(numberOfPilots);
		Mockito.when(pilotoGrpcClient.fetchPilotoList()).thenAnswer(i->pilotoList);
	}

	protected void mockPilotoGrpcClientSavePiloto(int pilotoId) {
		Answer<PilotoEntity> answer = new Answer<PilotoEntity>() {
			public PilotoEntity answer(InvocationOnMock invocation) {
				PilotoEntity piloto =  invocation.getArgument(ConstantesTests.ZERO);
				if(piloto!=null) {
					piloto.setPilotoId(Long.valueOf(pilotoId));
				}
				return piloto;
			}
		};
		Mockito.when(pilotoGrpcClient.savePiloto(Mockito.any())).thenAnswer(answer);
	}

	protected void mockPilotoGrpcClientUpdatePiloto(int pilotoId) {
		Answer<PilotoEntity> answer = new Answer<PilotoEntity>() {
			public PilotoEntity answer(InvocationOnMock invocation) {
				return  invocation.getArgument(ConstantesTests.ZERO);
			}
		};
		Mockito.when(pilotoGrpcClient.updatePiloto(Mockito.any(), Mockito.anyLong())).thenAnswer(answer);
	}

	protected void mockPilotoGrpcClientDeletePilotoById() {
		Mockito.doNothing().when(pilotoGrpcClient).deletePilotoById(Mockito.any());
	}

	protected void mockPilotoGrpcClientFindById(int pilotoId) {

		Answer<PilotoEntity> answer = new Answer<PilotoEntity>() {
			public PilotoEntity answer(InvocationOnMock invocation) {
				Long pilotoId = invocation.getArgument(ConstantesTests.ZERO);
				PilotoEntity piloto = null;
				if(!Long.valueOf(ConstantesTests.MINUS_ONE).equals(pilotoId)) {
					piloto = createPilotoEntity(pilotoId.intValue());
				}
				return piloto;
			}
		};

		Mockito.when(pilotoGrpcClient.findById(Mockito.anyLong())).thenAnswer(answer);
	}

	protected void mockPilotoGrpcClient(Integer pilotoId) {
		mockPilotoGrpcClientSavePiloto(pilotoId);
		mockPilotoGrpcClientUpdatePiloto(pilotoId);
		mockPilotoGrpcClientFindById(pilotoId);
		mockPilotoGrpcClientFechtPilotoList(pilotoId);
		mockPilotoGrpcClientDeletePilotoById();
	}

	protected List<PilotoEntity> createPilotoEntityList(int numberOfPilots) {
		List<PilotoEntity> pilotoList = new ArrayList<>();
		for(int i=ConstantesTests.ONE; i<=numberOfPilots; i++ ) {
			PilotoEntity piloto = createPilotoEntity(i);
			pilotoList.add(piloto);
		}
		return pilotoList;
	}

	protected PilotoEntity createPilotoEntity(int i) {
			PilotoEntity piloto = new PilotoEntity();
			piloto.setPilotoId(Long.valueOf(i));
			piloto.setPilotoName(ConstantesTests.PILOTO_NAME);
			piloto.setCreationDate(new Date());
			piloto.setCreationUser(ConstantesTests.USER_TESTS);
		return piloto;
	}

}
