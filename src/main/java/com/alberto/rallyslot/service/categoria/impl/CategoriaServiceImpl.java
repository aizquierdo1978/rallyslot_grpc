package com.alberto.rallyslot.service.categoria.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alberto.rallyslot.dao.categoria.CategoriaDao;
import com.alberto.rallyslot.dto.categoria.Categoria;
import com.alberto.rallyslot.entity.categoria.CategoriaEntity;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.categoria.CategoriaService;
import com.alberto.rallyslot.service.common.impl.CommonServiceImpl;

/**
 * Implementación del servicio de categorías.
 */
@Service
public class CategoriaServiceImpl extends CommonServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Categoria saveCategoria(Categoria categoria) {
		CategoriaEntity categoriaEntity = validateData(categoria);
		CategoriaEntity createdCategoria = categoriaDao.saveCategoria(categoriaEntity);
		return map(createdCategoria, Categoria.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Categoria> fetchCategoriaList() {
		List<CategoriaEntity> categoriaEntityList = categoriaDao.fetchCategoriaList();
		return mapList(categoriaEntityList, Categoria.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Categoria updateCategoria(Categoria categoria, Long categoriaId) {
		// Se valida y parsea el categoria
		CategoriaEntity categoriaEntity = validateData(categoria, categoriaId);
		// Se actualiza el categoria
		categoriaDao.updateCategoria(categoriaEntity, categoriaId);
		// Obtenemos los nuevos valores del categoria
		CategoriaEntity updatedCategoria = categoriaDao.findById(categoriaId);
		return map(updatedCategoria, Categoria.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteCategoriaById(Long categoriaId, String usuario, Date date) {
		// Validamos si existe el categoria
		existsCategoria(categoriaId);
		CategoriaEntity categoria = new CategoriaEntity();
		categoria.setCategoriaId(categoriaId);
		categoria.setDeleteUser(usuario);
		categoria.setDeleteDate(date);
		categoriaDao.deleteCategoriaById(categoria);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Categoria getCategoria(Long categoriaId) {
		CategoriaEntity categoria = categoriaDao.findById(categoriaId);
		if (categoria == null) {
			throw new EntityNotFoundException("No existe el categoria con el identificador: " + categoriaId);
		}
		return map(categoria, Categoria.class);
	}

	/**
	 * Si existe el categoria.
	 *
	 * @param categoriaId el identificador del categoria
	 * @return true, si existe
	 */
	private boolean existsCategoria(Long categoriaId) {
		if (categoriaDao.findById(categoriaId) == null) {
			throw new EntityNotFoundException("No existe el categoria con el identificador: " + categoriaId);
		}
		return Boolean.TRUE;
	}

	/**
	 * Valida los datos.
	 *
	 * @param categoria   el categoria
	 * @param categoriaId el identificador de la categoria
	 * @return la entidad de la categoria
	 */
	private CategoriaEntity validateData(Categoria categoria, Long categoriaId) {
		// Validamos si existe el categoria
		existsCategoria(categoriaId);

		if (!categoriaId.equals(categoria.getCategoriaId())) {
			throw new InvalidEntityException("La categoria de la ruta es distinto al del objeto");
		}

		return validateData(categoria);
	}

	/**
	 * Valida los datos.
	 *
	 * @param categoria el categoria
	 * @return la entidad de la categoria
	 */
	private CategoriaEntity validateData(Categoria categoria) {

		Configuration<?> config = Validation.byDefaultProvider().configure();
		ValidatorFactory factory = config.buildValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Categoria>> validaciones = validator.validate(categoria);
		if (!CollectionUtils.isEmpty(validaciones)) {
			throw new InvalidEntityException("No ha informado alguno de los campos");
		}

		if (categoria.getCategoriaName() == null) {
			throw new InvalidEntityException("No ha informado el nombre");
		}

		return map(categoria, CategoriaEntity.class);
	}

}
