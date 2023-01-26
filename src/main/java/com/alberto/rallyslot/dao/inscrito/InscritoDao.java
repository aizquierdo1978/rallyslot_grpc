package com.alberto.rallyslot.dao.inscrito;

import java.util.List;

import com.alberto.rallyslot.entity.inscrito.InscritoEntity;

/**
 * La interfaz InscritoDao.
 */
public interface InscritoDao {

	/**
	 * Busca el inscrito por id
	 *
	 * @param inscritoId el identificador del inscrito
	 * @return el inscrito
	 */
	InscritoEntity findById(Long inscritoId);

	/**
	 * Guarda el inscrito.
	 *
	 * @param inscrito el inscrito
	 * @return el inscrito
	 */
	InscritoEntity saveInscrito(InscritoEntity inscrito);
	 
    /**
	 * Obtiene el listado de inscritoes.
	 *
	 * @return el listado de inscritoes
	 */
	List<InscritoEntity> fetchInscritoList();
 
    /**
	 * Actualiza el inscrito.
	 *
	 * @param inscrito   el inscrito
	 * @param inscritoId el identificador del inscrito
	 * @return el inscrito
	 */
    // Update operation
	InscritoEntity updateInscrito(InscritoEntity inscrito);
 
    /**
	 * Borra el inscrito por su identificador
	 *
	 * @param inscritoId el identificador del inscrito
	 */
    // Delete operation
	void deleteInscritoById(InscritoEntity inscrito);

	/**
	 * Obtiene el listado de inscritos.
	 *
	 * @return el listado de inscritos sin tramo
	 */
	List<InscritoEntity> findInscritoSinTramoList();
		
}
