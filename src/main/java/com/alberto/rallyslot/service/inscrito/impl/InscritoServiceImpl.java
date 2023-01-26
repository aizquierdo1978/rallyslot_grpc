package com.alberto.rallyslot.service.inscrito.impl;

import com.alberto.rallyslot.common.Constantes;
import com.alberto.rallyslot.dao.categoria.CategoriaDao;
import com.alberto.rallyslot.dao.inscrito.InscritoDao;
import com.alberto.rallyslot.dao.prueba.PruebaDao;
import com.alberto.rallyslot.dao.tramo.TramoDao;
import com.alberto.rallyslot.dto.inscrito.Inscrito;
import com.alberto.rallyslot.entity.inscrito.InscritoEntity;
import com.alberto.rallyslot.entity.prueba.PruebaEntity;
import com.alberto.rallyslot.entity.tramo.TramoEntity;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.common.impl.CommonServiceImpl;
import com.alberto.rallyslot.service.inscrito.InscritoService;
import com.alberto.rallyslot.service.piloto.PilotoService;
import com.alberto.rallyslot.util.RallyslotConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementación del servicio de inscritos.
 */
@Service
public class InscritoServiceImpl extends CommonServiceImpl implements InscritoService {
	
	@Autowired
	private InscritoDao inscritoDao;
	
	@Autowired
	private PilotoService pilotoService;

	@Autowired
	private PruebaDao pruebaDao;

	@Autowired
	private CategoriaDao categoriaDao;

	@Autowired
	private TramoDao tramoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Inscrito saveInscrito(Inscrito inscrito) {
		// Se validan los datos de la inscrito
		InscritoEntity inscritoEntity = validateData(inscrito);
		// Se crea la inscrito
		InscritoEntity createdInscrito = inscritoDao.saveInscrito(inscritoEntity);
		
		// En este punto creamos los tramos
		crearTramos(createdInscrito);
		
		return map(createdInscrito, Inscrito.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Inscrito> fetchInscritoList() {
		List<InscritoEntity> inscritoEntityList = inscritoDao.fetchInscritoList();
		return mapList(inscritoEntityList, Inscrito.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Inscrito updateInscrito(Inscrito inscrito, Long inscritoId) {
		// Validamos si existe la inscrito
		InscritoEntity currentInscrito = existsInscrito(inscritoId);
		// Se validan los datos de la inscrito
		InscritoEntity inscritoEntity = validateData(inscrito, inscritoId);
		// Se actualiza la inscrito
		inscritoDao.updateInscrito(inscritoEntity);
		// Se obtiene la inscrito actualizada
		InscritoEntity updatedInscrito = inscritoDao.findById(inscritoId);

		return map(updatedInscrito, Inscrito.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteInscritoById(Long inscritoId, String usuario, Date date) {
		// Validamos si existe la inscrito
		existsInscrito(inscritoId);
		InscritoEntity inscrito = new InscritoEntity();
		inscrito.setInscritoId(inscritoId);
		inscrito.setDeleteUser(usuario);
		inscrito.setDeleteDate(date);
		inscritoDao.deleteInscritoById(inscrito);
		
		// Hay que borrar los tramos para un inscrito borrado
		TramoEntity tramo = new TramoEntity();
		tramo.setInscritoId(inscritoId);
		tramo.setDeleteDate(date);
		tramo.setDeleteUser(usuario);
		tramoDao.deleteAllTramosByInscritoId(tramo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Inscrito getInscrito(Long inscritoId) {
		InscritoEntity inscrito = inscritoDao.findById(inscritoId);
		if (inscrito == null) {
			throw new EntityNotFoundException("No existe la inscrito con el identificador: " + inscritoId);
		}
		return map(inscrito, Inscrito.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void comprobarInscritosSinTramos() {
		// Buscamos aquellos inscritos que no tienen tramos
		List<InscritoEntity> inscritosSinTramos = inscritoDao.findInscritoSinTramoList();

		if (CollectionUtils.isEmpty(inscritosSinTramos)) {
			return;
		}

		// Vamos creando inscritos uno a uno
		for (InscritoEntity inscrito : inscritosSinTramos) {
			inscrito.setCreationUser(Constantes.BATCH_USER);
			crearTramos(inscrito);
		}
	}

	/**
	 * Si existe la inscrito.
	 *
	 * @param inscritoId el identificador dla inscrito
	 * @return true, si existe
	 */
	private InscritoEntity existsInscrito(Long inscritoId) {
		InscritoEntity inscrito = inscritoDao.findById(inscritoId);
		if (inscritoDao.findById(inscritoId) == null) {
			throw new EntityNotFoundException("No existe la inscrito con el identificador: " + inscritoId);
		}
		return inscrito;
	}

	/**
	 * Valida los datos.
	 *
	 * @param inscrito   el inscrito
	 * @param inscritoId el identificador del inscrito
	 * @return la entidad del inscrito
	 */
	private InscritoEntity validateData(Inscrito inscrito, Long inscritoId) {

		if (!inscritoId.equals(inscrito.getInscritoId())) {
			throw new InvalidEntityException("El inscrito de la ruta es distinto al del objeto");
		}

		return validateData(inscrito);
	}

	/**
	 * Valida los datos.
	 *
	 * @param inscrito la inscrito
	 * @return la entidad de la inscrito
	 */
	private InscritoEntity validateData(Inscrito inscrito) {
		List<String> camposNoInformados = new ArrayList<>();
		if (inscrito.getPilotoId() == null) {
			camposNoInformados.add("No ha informado el campo inscrito");
		}

		if (inscrito.getPruebaId() == null) {
			camposNoInformados.add("No ha informado el campo prueba");
		}

		if (inscrito.getCategoriaId() == null) {
			camposNoInformados.add("No ha informado el campo categoria");
		}

		if (!CollectionUtils.isEmpty(camposNoInformados)) {
			throw new InvalidEntityException(camposNoInformados);
		}
		
		List<String> noEncontrados = new ArrayList<>();
		// En primer lugar verificamos que exista el inscrito
		if (pilotoService.getPiloto(inscrito.getPilotoId()) == null) {
			noEncontrados.add("No existe el piloto con el identificador: " + inscrito.getPilotoId());
		}

		// Verificamos que exista la prueba
		if (pruebaDao.findById(inscrito.getPruebaId()) == null) {
			noEncontrados.add("No existe la prueba con el identificador: " + inscrito.getPruebaId());
		}

		// Verificamos que exista la categoría
		if (categoriaDao.findById(inscrito.getCategoriaId()) == null) {
			noEncontrados.add("No existe la categoria con el identificador: " + inscrito.getCategoriaId());
		}

		if (!CollectionUtils.isEmpty(noEncontrados)) {
			throw new EntityNotFoundException(noEncontrados);
		}

		return map(inscrito, InscritoEntity.class);
	}

	/**
	 * Crea los tramos asociados al inscrito.
	 *
	 * @param inscrito el incrito
	 * @return el listado de tramos creados
	 */
	private List<TramoEntity> crearTramos(InscritoEntity inscrito) {
		// En su momento ya detectamos que existe la prueba
		PruebaEntity prueba = pruebaDao.findById(inscrito.getPruebaId());
		List<TramoEntity> tramos = null;
		
		if(prueba.getSecciones() == null || Integer.valueOf(RallyslotConstants.ZERO).equals(prueba.getSecciones()) || prueba.getTramos() == null || Integer.valueOf(RallyslotConstants.ZERO).equals(prueba.getTramos())) {
			return tramos;
		}
		
		tramos = new ArrayList<>();
		int tramoPruebaId = RallyslotConstants.ONE;
		for (int seccion = RallyslotConstants.ZERO; seccion < prueba.getSecciones(); seccion++) {
			for (int numTramo = RallyslotConstants.ZERO; numTramo < prueba.getTramos(); numTramo++) {
				TramoEntity tramo = new TramoEntity();
				tramo.setInscritoId(inscrito.getInscritoId());
				tramo.setTramoPruebaId(Long.valueOf(tramoPruebaId));
				tramo.setCreationUser(inscrito.getModificationUser() != null ? inscrito.getModificationUser()
						: inscrito.getCreationUser());
				tramo.setCreationDate(inscrito.getModificationDate() != null ? inscrito.getModificationDate()
						: inscrito.getCreationDate());
				tramos.add(tramo);
				tramoPruebaId++;
			}
		}

		tramoDao.insertTramos(tramos);

		return tramos;
	}

}
