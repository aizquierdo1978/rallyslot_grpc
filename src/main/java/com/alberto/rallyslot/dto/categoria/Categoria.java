package com.alberto.rallyslot.dto.categoria;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.alberto.rallyslot.common.ValueOfEnum;
import com.alberto.rallyslot.dto.common.CommonDto;

/**
 * La clase Categoria.
 */
public class Categoria extends CommonDto implements Serializable {	

	private static final long serialVersionUID = -7705094587816027795L;

	private Long categoriaId;

	@NotBlank(message = "No ha informado el código de la categoría")
	@ValueOfEnum(enumClass = CategoriaEnum.class)
	private String categoriaCodigo;

	@NotBlank(message = "No ha informado el nombre de la categoría")
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
