package com.alberto.rallyslot.dao.club;

import java.util.List;

import com.alberto.rallyslot.entity.club.ClubEntity;

/**
 * La interfaz ClubDao.
 */
public interface ClubDao {

	/**
	 * Busca el club por id
	 *
	 * @param clubId el identificador del club
	 * @return el club
	 */
	ClubEntity findById(Long clubId);

	/**
	 * Guarda el club.
	 *
	 * @param club el club
	 * @return el club
	 */
	ClubEntity saveClub(ClubEntity club);
	 
    /**
	 * Obtiene el listado de clubes.
	 *
	 * @return el listado de clubes
	 */
    List<ClubEntity> fetchClubList();
 
    /**
	 * Actualiza el club.
	 *
	 * @param club   el club
	 * @param clubId el identificador del club
	 * @return el club
	 */
    // Update operation
    ClubEntity updateClub(ClubEntity club, Long clubId);
 
    /**
	 * Borra el club por su identificador
	 *
	 * @param club el club
	 */
    // Delete operation
	void deleteClubById(ClubEntity club);
		
}
