package com.alberto.rallyslot.service.club;

import java.util.Date;
import java.util.List;

import com.alberto.rallyslot.dto.club.Club;

/**
 * The Interface ClubService.
 */
public interface ClubService {

	/**
	 * Guarda el club.
	 *
	 * @param club el club
	 * @return el club
	 */
	Club saveClub(Club club);
	 
    /**
	 * Obtiene el listado de clubes.
	 *
	 * @return el listado de clubes
	 */
	List<Club> fetchClubList();
 
    /**
	 * Update club.
	 *
	 * @param club   el club
	 * @param clubId el identificador del club
	 * @return el club
	 */
	Club updateClub(Club club, Long clubId);
 
    /**
	 * Delete club by id.
	 *
	 * @param clubId el identificador del club
	 */
	void deleteClubById(Long clubId, String usuario, Date date);

	/**
	 * Obtiene el club.
	 *
	 * @param club   el club
	 * @param clubId el identificador del club
	 * @return el club
	 */
	Club getClub(Long clubId);
		
}
