package com.alberto.rallyslot.service.tramo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alberto.rallyslot.dao.inscrito.InscritoDao;
import com.alberto.rallyslot.dao.tramo.TramoDao;
import com.alberto.rallyslot.dto.tramo.Tramo;
import com.alberto.rallyslot.entity.inscrito.InscritoEntity;
import com.alberto.rallyslot.entity.tramo.TramoEntity;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.common.impl.CommonServiceImpl;
import com.alberto.rallyslot.service.tramo.TramoService;

/**
 * Implementación del servicio de tramos.
 */
@Service
public class TramoServiceImpl extends CommonServiceImpl implements TramoService {
	
	@Autowired
	private TramoDao tramoDao;
	
	@Autowired
	private InscritoDao inscritoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tramo saveTramo(Tramo tramo) {
		// Se validan los datos del tramo
		TramoEntity tramoEntity = validateData(tramo);
		// Se crea el tramo
		TramoEntity createdTramo = tramoDao.saveTramo(tramoEntity);
		return map(createdTramo, Tramo.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Tramo> fetchTramoList() {
		List<TramoEntity> tramoEntityList = tramoDao.fetchTramoList();
		return mapList(tramoEntityList, Tramo.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tramo updateTramo(Tramo tramo, Long tramoId) {
		// Se validan los datos del tramo
		TramoEntity tramoEntity = validateData(tramo, tramoId);
		// Se actualiza el tramo
		tramoDao.updateTramo(tramoEntity, tramoId);
		// Se obtiene el tramo actualizada
		TramoEntity updatedTramo = tramoDao.findById(tramoId);
		return map(updatedTramo, Tramo.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteTramoById(Long tramoId, String usuario, Date date) {
		// Validamos si existe el tramo
		existsTramo(tramoId);
		TramoEntity tramo = new TramoEntity();
		tramo.setTramoId(tramoId);
		tramo.setDeleteUser(usuario);
		tramo.setDeleteDate(date);
		tramoDao.deleteTramoById(tramo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tramo getTramo(Long tramoId) {

		TramoEntity tramo = tramoDao.findById(tramoId);
		if (tramo == null) {
			throw new EntityNotFoundException("No existe el tramo con el identificador: " + tramoId);
		}

		return map(tramo, Tramo.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Tramo> getTramosByInscrito(Long inscritoId) {

		InscritoEntity inscrito = inscritoDao.findById(inscritoId);
		if (inscrito == null) {
			throw new EntityNotFoundException("No existe el inscrito con el identificador: " + inscritoId);
		}

		List<TramoEntity> tramosByInscrito = tramoDao.getTramosByInscrito(inscritoId);

		return mapList(tramosByInscrito, Tramo.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeTramos(BigDecimal tiempo, String user) {
		tramoDao.initializeTramos(tiempo, user);
	}

	/**
	 * Si existe el tramo.
	 *
	 * @param tramoId el identificador del tramo
	 * @return true, si existe
	 */
	private boolean existsTramo(Long tramoId) {
		if (tramoDao.findById(tramoId) == null) {
			throw new EntityNotFoundException("No existe el tramo con el identificador: " + tramoId);
		}
		return Boolean.TRUE;
	}

	/**
	 * Valida los datos.
	 *
	 * @param tramo   el tramo
	 * @param tramoId el identificador del tramo
	 * @return la entidad del tramo
	 */
	private TramoEntity validateData(Tramo tramo, Long tramoId) {
		// Validamos si existe el tramo
		existsTramo(tramoId);

		if (!tramoId.equals(tramo.getTramoId())) {
			throw new InvalidEntityException("El tramo de la ruta es distinto al del objeto");
		}

		return validateData(tramo);
	}

	/**
	 * Valida los datos.
	 *
	 * @param tramo el tramo
	 * @return la entidad del tramo
	 */
	private TramoEntity validateData(Tramo tramo) {
		List<String> invalidEntityErrors = new ArrayList<>();
		if (tramo.getInscritoId() == null) {
			invalidEntityErrors.add("No ha informado el inscrito");
		}

		if (tramo.getTramoPruebaId() == null) {
			invalidEntityErrors.add("No ha informado el tramo");
		}

		if (!CollectionUtils.isEmpty(invalidEntityErrors)) {
			throw new InvalidEntityException(invalidEntityErrors);
		}
		
		// En  primer lugar verificamos que exista el club
		if (inscritoDao.findById(tramo.getInscritoId()) == null) {
			throw new EntityNotFoundException("No existe el inscrito con el identificador: " + tramo.getInscritoId());
		}

		return map(tramo, TramoEntity.class);
	}

}
