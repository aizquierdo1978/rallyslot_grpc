package com.alberto.rallyslot.service.prueba.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alberto.rallyslot.dao.club.ClubDao;
import com.alberto.rallyslot.dao.prueba.PruebaDao;
import com.alberto.rallyslot.dto.prueba.Prueba;
import com.alberto.rallyslot.entity.prueba.PruebaEntity;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.common.impl.CommonServiceImpl;
import com.alberto.rallyslot.service.prueba.PruebaService;

/**
 * Implementación del servicio de pruebas.
 */
@Service
public class PruebaServiceImpl extends CommonServiceImpl implements PruebaService {
	
	@Autowired
	private PruebaDao pruebaDao;
	
	@Autowired
	private ClubDao clubDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Prueba savePrueba(Prueba prueba) {
		// Se validan los datos de la prueba
		PruebaEntity pruebaEntity = validateData(prueba);
		// Se crea la prueba
		PruebaEntity createdPrueba = pruebaDao.savePrueba(pruebaEntity);
		return map(createdPrueba, Prueba.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Prueba> fetchPruebaList() {
		List<PruebaEntity> pruebaEntityList = pruebaDao.fetchPruebaList();
		return mapList(pruebaEntityList, Prueba.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Prueba updatePrueba(Prueba prueba, Long pruebaId) {
		// Se validan los datos de la prueba
		PruebaEntity pruebaEntity = validateData(prueba, pruebaId);
		// Se actualiza la prueba
		pruebaDao.updatePrueba(pruebaEntity, pruebaId);
		// Se obtiene la prueba actualizada
		PruebaEntity updatedPrueba = pruebaDao.findById(pruebaId);
		return map(updatedPrueba, Prueba.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePruebaById(Long pruebaId, String usuario, Date date) {
		// Validamos si existe la prueba
		existsPrueba(pruebaId);
		PruebaEntity prueba = new PruebaEntity();
		prueba.setPruebaId(pruebaId);
		prueba.setDeleteUser(usuario);
		prueba.setDeleteDate(date);
		pruebaDao.deletePruebaById(prueba);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Prueba getPrueba(Long pruebaId) {
		PruebaEntity prueba = pruebaDao.findById(pruebaId);
		if (prueba == null) {
			throw new EntityNotFoundException("No existe la prueba con el identificador: " + pruebaId);
		}
		return map(prueba, Prueba.class);
	}

	/**
	 * Si existe la prueba.
	 *
	 * @param pruebaId el identificador de la prueba
	 * @return true, si existe
	 */
	private boolean existsPrueba(Long pruebaId) {
		if (pruebaDao.findById(pruebaId) == null) {
			throw new EntityNotFoundException("No existe la prueba con el identificador: " + pruebaId);
		}
		return Boolean.TRUE;
	}

	/**
	 * Valida los datos.
	 *
	 * @param prueba   la prueba
	 * @param pruebaId el identificador de la prueba
	 * @return la entidad de la prueba
	 */
	private PruebaEntity validateData(Prueba prueba, Long pruebaId) {
		// Validamos si existe la prueba
		existsPrueba(pruebaId);

		if (!pruebaId.equals(prueba.getPruebaId())) {
			throw new InvalidEntityException("La prueba de la ruta es distinto al del objeto");
		}

		return validateData(prueba);
	}

	/**
	 * Valida los datos.
	 *
	 * @param prueba la prueba
	 * @return la entidad de la prueba
	 */
	private PruebaEntity validateData(Prueba prueba) {
		List<String> invalidEntityErrors = new ArrayList<>();
		if (prueba.getAno() == null) {
			invalidEntityErrors.add("No ha informado el año");
		}

		if (prueba.getClubId() == null) {
			invalidEntityErrors.add("No ha informado el club");
		}

		if (prueba.getPruebaName() == null) {
			invalidEntityErrors.add("No ha informado el nombre de la prueba");
		}
		
		if (prueba.getSecciones() == null) {
			invalidEntityErrors.add("No ha informado las secciones de la prueba");
		}

		if (prueba.getTramos() == null) {
			invalidEntityErrors.add("No ha informado los tramos de la prueba");
		}

		if (!CollectionUtils.isEmpty(invalidEntityErrors)) {
			throw new InvalidEntityException(invalidEntityErrors);
		}
		
		// En  primer lugar verificamos que exista el club
		if (clubDao.findById(prueba.getClubId()) == null) {
			throw new EntityNotFoundException("No existe la club con el identificador: " + prueba.getClubId());
		}

		return map(prueba, PruebaEntity.class);
	}

}
