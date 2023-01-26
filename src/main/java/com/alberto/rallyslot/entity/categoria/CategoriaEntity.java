package com.alberto.rallyslot.entity.categoria;


import java.io.Serializable;

import com.alberto.rallyslot.entity.common.CommonEntity;

/**
 * La clase CategoriaEntity.
 */

public class CategoriaEntity extends CommonEntity implements Serializable {
 
    
	private static final long serialVersionUID = -5771719732366972305L;

	private Long categoriaId;

	private String categoriaCodigo;

	private String categoriaName;

	/**
	 * Obtiene el identificador del categoria.
	 *
	 * @return el identificador del categoria
	 */
	public Long getCategoriaId() {
		return categoriaId;
	}

	/**
	 * Establece el identificador del categoria.
	 *
	 * @param categoriaId identificador del categoria
	 */
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	/**
	 * Obtiene el nombre del categoria.
	 *
	 * @return el nombre del categoria
	 */
	public String getCategoriaName() {
		return categoriaName;
	}

	/**
	 * Establece el nombre del categoria.
	 *
	 * @param categoriaName nuevo nombre del categoria
	 */
	public void setCategoriaName(String categoriaName) {
		this.categoriaName = categoriaName;
	}

	/**
	 * Obtiene el categoria codigo.
	 *
	 * @return el categoria codigo
	 */
	public String getCategoriaCodigo() {
		return categoriaCodigo;
	}

	/**
	 * Establece el categoria codigo.
	 *
	 * @param categoriaCodigo nuevo categoria codigo
	 */
	public void setCategoriaCodigo(String categoriaCodigo) {
		this.categoriaCodigo = categoriaCodigo;
	}
}
