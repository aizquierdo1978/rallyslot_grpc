package com.alberto.rallyslot.entity.prueba;


import java.io.Serializable;

import com.alberto.rallyslot.dto.common.CommonDto;

/**
 * La clase PruebaEntity.
 */
public class PruebaEntity extends CommonDto implements Serializable {

	private static final long serialVersionUID = 4576497674892982047L;

	private Long pruebaId;

	private Long clubId;

	private Integer ano;
    
	private String pruebaName;

	private Integer secciones;

	private Integer tramos;

	/**
	 * Obtiene el identificador del prueba.
	 *
	 * @return el identificador del prueba
	 */
	public Long getPruebaId() {
		return pruebaId;
	}

	/**
	 * Establece el identificador del prueba.
	 *
	 * @param pruebaId nuevo prueba id
	 */
	public void setPruebaId(Long pruebaId) {
		this.pruebaId = pruebaId;
	}

	/**
	 * Obtiene el identificador del club.
	 *
	 * @return el identificador del club
	 */
	public Long getClubId() {
		return clubId;
	}

	/**
	 * Establece el identificador del club.
	 *
	 * @param clubId nuevo identificador del club
	 */
	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	/**
	 * Obtiene el año.
	 *
	 * @return el año
	 */
	public Integer getAno() {
		return ano;
	}

	/**
	 * Establece el año.
	 *
	 * @param ano nuevo año
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	/**
	 * Obtiene el nombre de la prueba.
	 *
	 * @return el nombre de la prueba
	 */
	public String getPruebaName() {
		return pruebaName;
	}

	/**
	 * Establece el nombre de la prueba.
	 *
	 * @param pruebaName nuevo nombre de la prueba
	 */
	public void setPruebaName(String pruebaName) {
		this.pruebaName = pruebaName;
	}

	/**
	 * Obtiene las secciones.
	 *
	 * @return las secciones
	 */
	public Integer getSecciones() {
		return secciones;
	}

	/**
	 * Establece las secciones.
	 *
	 * @param secciones nuevas secciones
	 */
	public void setSecciones(Integer secciones) {
		this.secciones = secciones;
	}

	/**
	 * Obtiene los tramos.
	 *
	 * @return los tramos
	 */
	public Integer getTramos() {
		return tramos;
	}

	/**
	 * Establece los tramos.
	 *
	 * @param tramos nuevos tramos
	 */
	public void setTramos(Integer tramos) {
		this.tramos = tramos;
	}

}
