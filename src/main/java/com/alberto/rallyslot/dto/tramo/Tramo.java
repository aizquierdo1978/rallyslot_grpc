package com.alberto.rallyslot.dto.tramo;


import java.io.Serializable;
import java.math.BigDecimal;

import com.alberto.rallyslot.dto.common.CommonDto;

/**
 * La clase Tramo.
 */
public class Tramo extends CommonDto implements Serializable {

	private static final long serialVersionUID = 2897473566172521913L;

	private Long tramoId;

	private Long tramoPruebaId;

	private Long inscritoId;

	private BigDecimal tiempo;

	/**
	 * Obtiene el tramo id.
	 *
	 * @return el tramo id
	 */
	public Long getTramoId() {
		return tramoId;
	}

	/**
	 * Establece el tramo id.
	 *
	 * @param tramoId nuevo tramo id
	 */
	public void setTramoId(Long tramoId) {
		this.tramoId = tramoId;
	}

	/**
	 * Obtiene el tramo prueba id.
	 *
	 * @return el tramo prueba id
	 */
	public Long getTramoPruebaId() {
		return tramoPruebaId;
	}

	/**
	 * Establece el tramo prueba id.
	 *
	 * @param tramoPruebaId nuevo tramo prueba id
	 */
	public void setTramoPruebaId(Long tramoPruebaId) {
		this.tramoPruebaId = tramoPruebaId;
	}

	/**
	 * Obtiene el inscrito id.
	 *
	 * @return el inscrito id
	 */
	public Long getInscritoId() {
		return inscritoId;
	}

	/**
	 * Establece el inscrito id.
	 *
	 * @param inscritoId nuevo inscrito id
	 */
	public void setInscritoId(Long inscritoId) {
		this.inscritoId = inscritoId;
	}

	/**
	 * Obtiene el tiempo.
	 *
	 * @return el tiempo
	 */
	public BigDecimal getTiempo() {
		return tiempo;
	}

	/**
	 * Establece el tiempo.
	 *
	 * @param tiempo nuevo tiempo
	 */
	public void setTiempo(BigDecimal tiempo) {
		this.tiempo = tiempo;
	}

}
