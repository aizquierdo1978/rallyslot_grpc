package com.alberto.rallyslot.entity.club;


import java.io.Serializable;

import com.alberto.rallyslot.entity.common.CommonEntity;

/**
 * La clase ClubEntity.
 */

public class ClubEntity extends CommonEntity implements Serializable {
 
    
	private static final long serialVersionUID = -5771719732366972305L;

	/** The club id. */
    private Long clubId;
    
    /** The club name. */
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
