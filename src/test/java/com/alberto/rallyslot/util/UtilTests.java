package com.alberto.rallyslot.util;

import com.alberto.rallyslot.dto.categoria.Categoria;
import com.alberto.rallyslot.dto.club.Club;
import com.alberto.rallyslot.dto.inscrito.Inscrito;
import com.alberto.rallyslot.dto.piloto.Piloto;
import com.alberto.rallyslot.dto.prueba.Prueba;

public class UtilTests {

	/**
	 * Instantiates a new util tests.
	 */
	private UtilTests() {
	}

	/**
	 * Crea un objeto club para los tests.
	 *
	 * @param clubName el nombre del club
	 * @return el club
	 */
	public static Club createClubObject(String clubName) {
		Club club = new Club();
		club.setClubName(clubName);
		return club;
	}

	/*
	 * Crea un objeto piloto para los tests.
	 *
	 * @param pilotoName el nombre del piloto
	 * 
	 * @return el piloto
	 */
	public static Piloto createPilotoObject(String pilotoName) {
		Piloto piloto = new Piloto();
		piloto.setPilotoName(pilotoName);
		return piloto;
	}

	/**
	 * Crea un objeto prueba para los tests
	 *
	 * @param clubId     el identificador del club
	 * @param ano        el año
	 * @param pruebaName el nombre de la prueba
	 * @return la prueba
	 */
	public static Prueba createPruebaObject(Long clubId, Integer ano, String pruebaName, Integer secciones,
			Integer tramos) {
		Prueba prueba = new Prueba();
		prueba.setClubId(clubId);
		prueba.setAno(ano);
		prueba.setPruebaName(pruebaName);
		prueba.setSecciones(secciones);
		prueba.setTramos(tramos);
		return prueba;
	}

	/**
	 * Crea un objeto inscrito para los tests.
	 *
	 * @param pruebaId the prueba id
	 * @param pilotoId the piloto id
	 * @return the inscrito
	 */
	public static Inscrito createInscritoObject(Long pruebaId, Long pilotoId, Long categoriaId) {
		Inscrito inscrito = new Inscrito();
		inscrito.setPruebaId(pruebaId);
		inscrito.setPilotoId(pilotoId);
		inscrito.setCategoriaId(categoriaId);
		return inscrito;
	}

	/**
	 * Crea un objeto categoría para los tests.
	 *
	 * @param clubName el nombre de la categoría
	 * @return la categoría
	 */
	public static Categoria createCategoriaObject(String categoriaName, String categoriaCodigo) {
		Categoria categoria = new Categoria();
		categoria.setCategoriaCodigo(categoriaCodigo);
		categoria.setCategoriaName(categoriaName);
		return categoria;
	}

}
