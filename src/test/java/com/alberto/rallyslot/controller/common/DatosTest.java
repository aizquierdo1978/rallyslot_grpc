package com.alberto.rallyslot.controller.common;

import java.io.Serializable;

public class DatosTest implements Serializable {

	private static final long serialVersionUID = -8366765018751734562L;

	private Long inscritoId;

	private Long clubId;

	private Long pilotoId;

	private Long pruebaId;

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
	 * Obtiene el club id.
	 *
	 * @return el club id
	 */
	public Long getClubId() {
		return clubId;
	}

	/**
	 * Establece el club id.
	 *
	 * @param clubId nuevo club id
	 */
	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	/**
	 * Obtiene el piloto id.
	 *
	 * @return el piloto id
	 */
	public Long getPilotoId() {
		return pilotoId;
	}

	/**
	 * Establece el piloto id.
	 *
	 * @param pilotoId nuevo piloto id
	 */
	public void setPilotoId(Long pilotoId) {
		this.pilotoId = pilotoId;
	}

	/**
	 * Obtiene el prueba id.
	 *
	 * @return el prueba id
	 */
	public Long getPruebaId() {
		return pruebaId;
	}

	/**
	 * Establece el prueba id.
	 *
	 * @param pruebaId nuevo prueba id
	 */
	public void setPruebaId(Long pruebaId) {
		this.pruebaId = pruebaId;
	}
	
	

}
