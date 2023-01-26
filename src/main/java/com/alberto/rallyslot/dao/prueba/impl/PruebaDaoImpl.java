package com.alberto.rallyslot.dao.prueba.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberto.rallyslot.dao.prueba.PruebaDao;
import com.alberto.rallyslot.entity.prueba.PruebaEntity;
import com.alberto.rallyslot.mapper.prueba.PruebaMapper;

/**
 * Implementación del servicio de Prueba.
 */
@Component
public class PruebaDaoImpl implements PruebaDao {

	@Autowired
	private PruebaMapper pruebaMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PruebaEntity findById(Long pruebaId) {
		return pruebaMapper.getPrueba(pruebaId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PruebaEntity savePrueba(PruebaEntity prueba) {
		pruebaMapper.insertPrueba(prueba);
		return prueba;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PruebaEntity> fetchPruebaList() {
		return pruebaMapper.getPruebas();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PruebaEntity updatePrueba(PruebaEntity prueba, Long pruebaId) {
		pruebaMapper.updatePrueba(prueba);
		return prueba;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePruebaById(PruebaEntity prueba) {
		pruebaMapper.deletePrueba(prueba);
	}

}
