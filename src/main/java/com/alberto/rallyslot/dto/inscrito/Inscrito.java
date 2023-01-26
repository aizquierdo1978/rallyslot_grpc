package com.alberto.rallyslot.dto.inscrito;


import java.io.Serializable;

import com.alberto.rallyslot.dto.common.CommonDto;

/**
 * La clase Inscrito.
 */
public class Inscrito extends CommonDto implements Serializable {

	private static final long serialVersionUID = -3312872036840571791L;

	private Long inscritoId;

	private Long pruebaId;

	private Long pilotoId;

	private Long categoriaId;

	/**
	 * Obtiene el identificador de inscrito.
	 *
	 * @return el identificador de inscrito
	 */
	public Long getInscritoId() {
		return inscritoId;
	}

	/**
	 * Establece el identificador de inscrito.
	 *
	 * @param inscritoId nuevo identificador de inscrito
	 */
	public void setInscritoId(Long inscritoId) {
		this.inscritoId = inscritoId;
	}

	/**
	 * Obtiene el identificador de la prueba.
	 *
	 * @return el identificador de la prueba
	 */
	public Long getPruebaId() {
		return pruebaId;
	}

	/**
	 * Establece el identificador de la prueba.
	 *
	 * @param pruebaId nuevo identificador de la prueba
	 */
	public void setPruebaId(Long pruebaId) {
		this.pruebaId = pruebaId;
	}

	/**
	 * Obtiene el identificador del piloto.
	 *
	 * @return el identificador del piloto
	 */
	public Long getPilotoId() {
		return pilotoId;
	}

	/**
	 * Establece el identificador del piloto.
	 *
	 * @param pilotoId nuevo identificador del piloto
	 */
	public void setPilotoId(Long pilotoId) {
		this.pilotoId = pilotoId;
	}

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

}
