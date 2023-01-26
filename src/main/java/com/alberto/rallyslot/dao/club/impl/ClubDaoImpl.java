package com.alberto.rallyslot.dao.club.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alberto.rallyslot.dao.club.ClubDao;
import com.alberto.rallyslot.entity.club.ClubEntity;
import com.alberto.rallyslot.mapper.club.ClubMapper;

/**
 * Implementación del servicio de Club.
 */
@Repository
public class ClubDaoImpl implements ClubDao {

	@Autowired
	private ClubMapper clubMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClubEntity findById(Long clubId) {
		return clubMapper.getClub(clubId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClubEntity saveClub(ClubEntity club) {
		clubMapper.insertClub(club);
		return club;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ClubEntity> fetchClubList() {
		return clubMapper.getClubs();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClubEntity updateClub(ClubEntity club, Long clubId) {
		clubMapper.updateClub(club);
		return club;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteClubById(ClubEntity club) {
		clubMapper.deleteClub(club);
	}


}
