package com.alberto.rallyslot.service.club.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alberto.rallyslot.dao.club.ClubDao;
import com.alberto.rallyslot.dto.club.Club;
import com.alberto.rallyslot.entity.club.ClubEntity;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.exception.InvalidEntityException;
import com.alberto.rallyslot.service.club.ClubService;
import com.alberto.rallyslot.service.common.impl.CommonServiceImpl;

/**
 * Implementación del servicio de Club.
 */
@Service
public class ClubServiceImpl extends CommonServiceImpl implements ClubService {
	
	/** The club dao. */
	@Autowired
	private ClubDao clubDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Club saveClub(Club club) {
		ClubEntity clubEntity = validateData(club);
		ClubEntity createdClub = clubDao.saveClub(clubEntity);
		return map(createdClub, Club.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Club> fetchClubList() {
		List<ClubEntity> clubEntityList = clubDao.fetchClubList();
		return mapList(clubEntityList, Club.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Club updateClub(Club club, Long clubId) {
		// Se valida y parsea el club
		ClubEntity clubEntity = validateData(club, clubId);
		// Se actualiza el club
		clubDao.updateClub(clubEntity, clubId);
		// Obtenemos los nuevos valores del club
		ClubEntity updatedClub = clubDao.findById(clubId);
		return map(updatedClub, Club.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteClubById(Long clubId, String usuario, Date date) {
		// Validamos si existe el club
		existsClub(clubId);
		ClubEntity club = new ClubEntity();
		club.setClubId(clubId);
		club.setDeleteUser(usuario);
		club.setDeleteDate(date);
		clubDao.deleteClubById(club);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Club getClub(Long clubId) {
		ClubEntity club = clubDao.findById(clubId);
		if (club == null) {
			throw new EntityNotFoundException("No existe el club con el identificador: " + clubId);
		}
		return map(club, Club.class);
	}

	/**
	 * Si existe el club.
	 *
	 * @param clubId el identificador del club
	 * @return true, si existe
	 */
	private boolean existsClub(Long clubId) {
		if (clubDao.findById(clubId) == null) {
			throw new EntityNotFoundException("No existe el club con el identificador: " + clubId);
		}
		return Boolean.TRUE;
	}

	/**
	 * Valida los datos.
	 *
	 * @param club   el club
	 * @param clubId el identificador de el club
	 * @return la entidad de el club
	 */
	private ClubEntity validateData(Club club, Long clubId) {
		// Validamos si existe el club
		existsClub(clubId);

		if (!clubId.equals(club.getClubId())) {
			throw new InvalidEntityException("La club de la ruta es distinto al del objeto");
		}

		return validateData(club);
	}

	/**
	 * Valida los datos.
	 *
	 * @param club el club
	 * @return la entidad de el club
	 */
	private ClubEntity validateData(Club club) {
		if (club.getClubName() == null) {
			throw new InvalidEntityException("No ha informado el nombre");
		}

		return map(club, ClubEntity.class);
	}

}
