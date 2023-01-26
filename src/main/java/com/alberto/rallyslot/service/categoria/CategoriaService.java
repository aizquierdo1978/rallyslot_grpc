package com.alberto.rallyslot.service.categoria;

import java.util.Date;
import java.util.List;

import com.alberto.rallyslot.dto.categoria.Categoria;

/**
 * La interfaz del servicio de categorías.
 */
public interface CategoriaService {

	/**
	 * Guarda la categoría.
	 *
	 * @param categoria la categoría
	 * @return la categoría
	 */
	Categoria saveCategoria(Categoria categoria);
	 
    /**
	 * Obtiene el listado de categorías.
	 *
	 * @return el listado de categorías
	 */
	List<Categoria> fetchCategoriaList();
 
    /**
	 * Actualiza la categoría.
	 *
	 * @param categoria   la categoría
	 * @param categoriaId el identificador de la categoría
	 * @return la categoría
	 */
	Categoria updateCategoria(Categoria categoria, Long categoriaId);
 
    /**
	 * Delete categoria by id.
	 *
	 * @param categoriaId el identificador de la categoría
	 */
	void deleteCategoriaById(Long categoriaId, String usuario, Date date);

	/**
	 * Obtiene la categoría.
	 *
	 * @param categoria   la categoría
	 * @param categoriaId el identificador de la categoría
	 * @return la categoría
	 */
	Categoria getCategoria(Long categoriaId);
		
}
