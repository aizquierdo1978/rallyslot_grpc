package com.alberto.rallyslot.dao.prueba;

import java.util.List;

import com.alberto.rallyslot.entity.prueba.PruebaEntity;

/**
 * La interfaz PruebaDao.
 */
public interface PruebaDao {

	/**
	 * Busca el prueba por id
	 *
	 * @param pruebaId el identificador de la prueba
	 * @return el prueba
	 */
	PruebaEntity findById(Long pruebaId);

	/**
	 * Guarda el prueba.
	 *
	 * @param prueba el prueba
	 * @return el prueba
	 */
	PruebaEntity savePrueba(PruebaEntity prueba);
	 
    /**
	 * Obtiene el listado de pruebaes.
	 *
	 * @return el listado de pruebaes
	 */
	List<PruebaEntity> fetchPruebaList();
 
    /**
	 * Actualiza el prueba.
	 *
	 * @param prueba   el prueba
	 * @param pruebaId el identificador de la prueba
	 * @return el prueba
	 */
    // Update operation
	PruebaEntity updatePrueba(PruebaEntity prueba, Long pruebaId);
 
    /**
	 * Borra el prueba por su identificador
	 *
	 * @param pruebaId el identificador de la prueba
	 */
    // Delete operation
	void deletePruebaById(PruebaEntity prueba);
		
}
