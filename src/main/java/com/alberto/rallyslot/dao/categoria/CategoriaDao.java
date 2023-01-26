package com.alberto.rallyslot.dao.categoria;

import java.util.List;

import com.alberto.rallyslot.entity.categoria.CategoriaEntity;

/**
 * La interfaz CategoriaDao.
 */
public interface CategoriaDao {

	/**
	 * Busca la categoria por id
	 *
	 * @param categoriaId el identificador dla categoria
	 * @return la categoria
	 */
	CategoriaEntity findById(Long categoriaId);

	/**
	 * Guarda la categoria.
	 *
	 * @param categoria la categoria
	 * @return la categoria
	 */
	CategoriaEntity saveCategoria(CategoriaEntity categoria);
	 
    /**
	 * Obtiene el listado de ategorias.
	 *
	 * @return el listado de ategorias
	 */
	List<CategoriaEntity> fetchCategoriaList();
 
    /**
	 * Actualiza la categoria.
	 *
	 * @param categoria   la categoria
	 * @param categoriaId el identificador dla categoria
	 * @return la categoria
	 */
    // Update operation
	CategoriaEntity updateCategoria(CategoriaEntity categoria, Long categoriaId);
 
    /**
	 * Borra la categoria por su identificador
	 *
	 * @param categoria la categoria
	 */
    // Delete operation
	void deleteCategoriaById(CategoriaEntity categoria);
		
}
