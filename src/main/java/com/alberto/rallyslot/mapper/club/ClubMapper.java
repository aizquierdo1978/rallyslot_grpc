package com.alberto.rallyslot.mapper.club;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alberto.rallyslot.entity.club.ClubEntity;

/**
 * La interfaz que mapea los clubes.
 */
public interface ClubMapper {

	/**
	 * Obtiene el club.
	 *
	 * @param clubId the club id
	 * @return el club
	 */
	@Select("SELECT clubId, clubName,creationDate,creationUser,modificationDate,modificationUser FROM CLUB WHERE clubId = #{clubId} AND deleteDate IS NULL")
	ClubEntity getClub(@Param("clubId") Long clubId);

	/**
	 * Obtiene el listado de clubs.
	 *
	 * @return el clubs
	 */
	@Select("SELECT clubId, clubName,creationDate,creationUser,modificationDate,modificationUser FROM CLUB WHERE deleteDate IS NULL")
	List<ClubEntity> getClubs();

	/**
	 * Inserta el club.
	 *
	 * @param club el club
	 * @return the int
	 */
	@Insert("INSERT INTO CLUB(clubName,creationDate,creationUser) VALUES (#{clubName}, #{creationDate}, #{creationUser})")
	@Options(useGeneratedKeys = true, keyProperty = "clubId", keyColumn = "clubId")
	public long insertClub(ClubEntity club);

	/**
	 * Actualiza el club.
	 *
	 * @param club the club
	 * @return the int
	 */
	@Update("Update CLUB set clubName=#{clubName}, modificationDate=#{modificationDate}, modificationUser=#{modificationUser} where clubId = #{clubId}")
	public int updateClub(ClubEntity club);

	/**
	 * Borra el club.
	 *
	 * @param club the club
	 * @return the int
	 */
	@Update("Update CLUB set deleteDate=#{deleteDate}, deleteUser=#{deleteUser} where clubId = #{clubId}")
	public int deleteClub(ClubEntity club);

}
