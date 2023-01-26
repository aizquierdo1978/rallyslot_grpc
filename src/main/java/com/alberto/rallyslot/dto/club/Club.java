package com.alberto.rallyslot.dto.club;


import java.io.Serializable;

import com.alberto.rallyslot.dto.common.CommonDto;

/**
 * La clase Club.
 */
public class Club extends CommonDto implements Serializable {

	private static final long serialVersionUID = -5771719732366972305L;

    private Long clubId;
    
    private String clubName;

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
	 * @param clubId identificador del club
	 */
	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	/**
	 * Obtiene el nombre del club.
	 *
	 * @return el nombre del club
	 */
	public String getClubName() {
		return clubName;
	}

	/**
	 * Establece el nombre del club.
	 *
	 * @param clubName nuevo nombre del club
	 */
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

}
