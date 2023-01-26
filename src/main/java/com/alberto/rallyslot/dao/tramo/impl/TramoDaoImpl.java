package com.alberto.rallyslot.dao.tramo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberto.rallyslot.dao.tramo.TramoDao;
import com.alberto.rallyslot.entity.tramo.TramoEntity;
import com.alberto.rallyslot.mapper.tramo.TramoMapper;

/**
 * Implementación del servicio de Tramo.
 */
@Component
public class TramoDaoImpl implements TramoDao {

	@Autowired
	private TramoMapper tramoMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TramoEntity findById(Long tramoId) {
		return tramoMapper.getTramo(tramoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TramoEntity saveTramo(TramoEntity tramo) {
		tramoMapper.insertTramo(tramo);
		return tramo;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TramoEntity> fetchTramoList() {
		return tramoMapper.getTramos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TramoEntity updateTramo(TramoEntity tramo, Long tramoId) {
		tramoMapper.updateTramo(tramo);
		return tramo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteTramoById(TramoEntity tramo) {
		tramoMapper.deleteTramo(tramo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertTramos(List<TramoEntity> tramos) {
		tramoMapper.insertBatchTramo(tramos);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TramoEntity> getTramosByInscrito(Long inscritoId) {
		return tramoMapper.getTramosByInscrito(inscritoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAllTramosByInscritoId(TramoEntity tramoEntity) {
		tramoMapper.deleteAllTramosByInscritoId(tramoEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeTramos(BigDecimal tiempo, String user) {
		tramoMapper.initializeTramos(tiempo, user);
	}

}
