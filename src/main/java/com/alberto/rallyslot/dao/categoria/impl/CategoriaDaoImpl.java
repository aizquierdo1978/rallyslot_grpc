package com.alberto.rallyslot.dao.categoria.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alberto.rallyslot.dao.categoria.CategoriaDao;
import com.alberto.rallyslot.entity.categoria.CategoriaEntity;
import com.alberto.rallyslot.mapper.categoria.CategoriaMapper;

/**
 * Implementación del dao de Categoría.
 */
@Repository
public class CategoriaDaoImpl implements CategoriaDao {

	@Autowired
	private CategoriaMapper categoriaMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoriaEntity findById(Long categoriaId) {
		return categoriaMapper.getCategoria(categoriaId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoriaEntity saveCategoria(CategoriaEntity categoria) {
		categoriaMapper.insertCategoria(categoria);
		return categoria;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoriaEntity> fetchCategoriaList() {
		return categoriaMapper.getCategorias();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoriaEntity updateCategoria(CategoriaEntity categoria, Long categoriaId) {
		categoriaMapper.updateCategoria(categoria);
		return categoria;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteCategoriaById(CategoriaEntity categoria) {
		categoriaMapper.deleteCategoria(categoria);
	}


}
