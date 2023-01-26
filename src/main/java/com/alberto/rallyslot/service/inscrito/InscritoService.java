package com.alberto.rallyslot.service.inscrito;

import java.util.Date;
import java.util.List;

import com.alberto.rallyslot.dto.inscrito.Inscrito;

/**
 * La interfaz de servicio de inscritos.
 */
public interface InscritoService {

	/**
	 * Guarda el inscrito.
	 *
	 * @param inscrito el inscrito
	 * @return el inscrito
	 */
	Inscrito saveInscrito(Inscrito inscrito);
	 
    /**
	 * Obtiene el listado de inscritos.
	 *
	 * @return el listado de inscritos
	 */
	List<Inscrito> fetchInscritoList();
 
    /**
	 * Actualiza un inscrito.
	 *
	 * @param inscrito   el inscrito
	 * @param inscritoId el identificador del inscrito
	 * @return el inscrito
	 */
	Inscrito updateInscrito(Inscrito inscrito, Long inscritoId);
 
	/**
	 * Borra el inscrito por su identidicador.
	 *
	 * @param inscritoId el identificador del inscrito
	 * @param usuario    el usuario
	 * @param date       la fecha
	 */
	void deleteInscritoById(Long inscritoId, String usuario, Date date);

	/**
	 * Obtiene el inscrito.
	 *
	 * @param inscrito   el inscrito
	 * @param inscritoId el identificador del inscrito
	 * @return el inscrito
	 */
	Inscrito getInscrito(Long inscritoId);

	/**
	 * Comprobar inscritos sin tramos y se los asigna.
	 */
	void comprobarInscritosSinTramos();
		
}
