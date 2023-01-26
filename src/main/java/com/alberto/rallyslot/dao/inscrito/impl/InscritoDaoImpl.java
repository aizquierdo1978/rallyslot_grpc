package com.alberto.rallyslot.dao.inscrito.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberto.rallyslot.dao.inscrito.InscritoDao;
import com.alberto.rallyslot.entity.inscrito.InscritoEntity;
import com.alberto.rallyslot.mapper.inscrito.InscritoMapper;

/**
 * Implementación del servicio de Inscrito.
 */
@Component
public class InscritoDaoImpl implements InscritoDao {

	@Autowired
	private InscritoMapper inscritoMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InscritoEntity findById(Long inscritoId) {
		return inscritoMapper.getInscrito(inscritoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InscritoEntity saveInscrito(InscritoEntity inscrito) {
		inscritoMapper.insertInscrito(inscrito);
		return inscrito;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<InscritoEntity> fetchInscritoList() {
		return inscritoMapper.getInscritos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InscritoEntity updateInscrito(InscritoEntity inscrito) {
		inscritoMapper.updateInscrito(inscrito);
		return inscrito;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteInscritoById(InscritoEntity inscrito) {
		inscritoMapper.delete(inscrito);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<InscritoEntity> findInscritoSinTramoList() {
		return inscritoMapper.getInscritosSinTramo();
	}

}
