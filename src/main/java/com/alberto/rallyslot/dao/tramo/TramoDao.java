package com.alberto.rallyslot.dao.tramo;

import java.math.BigDecimal;
import java.util.List;

import com.alberto.rallyslot.entity.tramo.TramoEntity;

/**
 * La interfaz TramoDao.
 */
public interface TramoDao {

	/**
	 * Busca el tramo por id
	 *
	 * @param tramoId el identificador del tramo
	 * @return el tramo
	 */
	TramoEntity findById(Long tramoId);

	/**
	 * Guarda el tramo.
	 *
	 * @param tramo el tramo
	 * @return el tramo
	 */
	TramoEntity saveTramo(TramoEntity tramo);
	 
    /**
	 * Obtiene el listado de tramos.
	 *
	 * @return el listado de tramos
	 */
	List<TramoEntity> fetchTramoList();
 
    /**
	 * Actualiza el tramo.
	 *
	 * @param tramo   el tramo
	 * @param tramoId el identificador del tramo
	 * @return el tramo
	 */
    // Update operation
	TramoEntity updateTramo(TramoEntity tramo, Long tramoId);
 
    /**
	 * Borra el tramo por su identificador
	 *
	 * @param tramoId el identificador del tramo
	 */
    // Delete operation
	void deleteTramoById(TramoEntity tramo);

	/**
	 * Inserta un listado de tramos.
	 *
	 * @param tramos el listado de tramos a insertar
	 */
	void insertTramos(List<TramoEntity> tramos);

	/**
	 * Obtiene los datos de los tramos de un inscrito.
	 *
	 * @param inscritoId el identificador del inscrito
	 * @return los tramos de un inscrito
	 */
	List<TramoEntity> getTramosByInscrito(Long inscritoId);

	/**
	 * Borra todos los tramos para un identificador de inscrito.
	 *
	 * @param tramoEntity la entidad del tramo
	 */
	void deleteAllTramosByInscritoId(TramoEntity tramoEntity);

	/**
	 * Inicializa los tramos que están vacíos.
	 *
	 * @param tiempo el tiempo por defecto
	 * @param user   el tiempo
	 */
	void initializeTramos(BigDecimal tiempo, String user);
		
}
