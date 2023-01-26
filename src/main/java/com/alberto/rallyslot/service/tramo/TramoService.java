package com.alberto.rallyslot.service.tramo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alberto.rallyslot.dto.tramo.Tramo;

/**
 * La interfaz de servicio de tramos.
 */
public interface TramoService {

	/**
	 * Guarda el tramo.
	 *
	 * @param tramo el tramo
	 * @return el tramo
	 */
	Tramo saveTramo(Tramo tramo);
	 
    /**
	 * Obtiene el listado de tramos.
	 *
	 * @return el listado de tramos
	 */
	List<Tramo> fetchTramoList();
 
    /**
	 * Actualiza un tramo.
	 *
	 * @param tramo   el tramo
	 * @param tramoId el identificador del tramo
	 * @return el tramo
	 */
	Tramo updateTramo(Tramo tramo, Long tramoId);
 
    /**
	 * Borra un tramo por su id.
	 *
	 * @param tramoId el identificador del tramo
	 */
	void deleteTramoById(Long tramoId, String usuario, Date date);

	/**
	 * Obtiene los datos de un tramo.
	 *
	 * @param tramoId el identificador del tramo
	 * @return el tramo
	 */
	Tramo getTramo(Long tramoId);

	/**
	 * Obtiene los datos de los tramos por un inscrito.
	 *
	 * @param inscritoId el identificador del inscrito
	 * @return los tramos del inscrito
	 */
	List<Tramo> getTramosByInscrito(Long inscritoId);

	/**
	 * Inicializa los tramos que están vacíos.
	 *
	 * @param tiempo el tiempo por defecto
	 * @param user   el tiempo
	 */
	void initializeTramos(BigDecimal tiempo, String user);
		
}
