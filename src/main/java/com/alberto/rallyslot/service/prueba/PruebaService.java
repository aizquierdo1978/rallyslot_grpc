package com.alberto.rallyslot.service.prueba;

import java.util.Date;
import java.util.List;

import com.alberto.rallyslot.dto.prueba.Prueba;

/**
 * La interfaz de servicio de pruebas.
 */
public interface PruebaService {

	/**
	 * Guarda la prueba.
	 *
	 * @param prueba la prueba
	 * @return la prueba
	 */
	Prueba savePrueba(Prueba prueba);
	 
    /**
	 * Obtiene el listado de pruebas.
	 *
	 * @return el listado de pruebas
	 */
	List<Prueba> fetchPruebaList();
 
    /**
	 * Actualiza un prueba.
	 *
	 * @param prueba   la prueba
	 * @param pruebaId el identificador de la prueba
	 * @return la prueba
	 */
	Prueba updatePrueba(Prueba prueba, Long pruebaId);
 
    /**
	 * Borra un prueba por su id.
	 *
	 * @param pruebaId el identificador de la prueba
	 */
	void deletePruebaById(Long pruebaId, String usuario, Date date);

	/**
	 * Obtiene la prueba.
	 *
	 * @param prueba   la prueba
	 * @param pruebaId el identificador de la prueba
	 * @return la prueba
	 */
	Prueba getPrueba(Long pruebaId);
		
}
