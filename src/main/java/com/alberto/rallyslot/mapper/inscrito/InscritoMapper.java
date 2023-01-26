package com.alberto.rallyslot.mapper.inscrito;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alberto.rallyslot.entity.inscrito.InscritoEntity;

/**
 * La interfaz que mapea los inscritos.
 */
public interface InscritoMapper {

	/**
	 * Obtiene el inscrito.
	 *
	 * @param inscritoId el identificador de inscrito
	 * @return el inscrito
	 */
	@Select("SELECT inscritoId, pruebaId, pilotoId, categoriaId, creationDate,creationUser,modificationDate,modificationUser FROM INSCRITO WHERE inscritoId = #{inscritoId} AND deleteDate IS NULL")
	InscritoEntity getInscrito(@Param("inscritoId") Long inscritoId);

	/**
	 * Obtiene el listado de inscritos.
	 *
	 * @return el inscritos
	 */
	@Select("SELECT inscritoId, pruebaId, pilotoId, categoriaId, creationDate,creationUser,modificationDate,modificationUser FROM INSCRITO WHERE deleteDate IS NULL")
	List<InscritoEntity> getInscritos();

	/**
	 * Inserta el inscrito.
	 *
	 * @param inscrito el inscrito
	 * @return the int
	 */
	@Insert("INSERT INTO INSCRITO(pruebaId,pilotoId, categoriaId,creationDate,creationUser) VALUES (#{pruebaId}, #{pilotoId}, #{categoriaId}, #{creationDate}, #{creationUser})")
	@Options(useGeneratedKeys = true, keyProperty = "inscritoId", keyColumn = "inscritoId")
	public long insertInscrito(InscritoEntity inscrito);

	/**
	 * Actualiza el inscrito.
	 *
	 * @param inscrito el inscrito
	 * @return the int
	 */
	@Update("Update INSCRITO set pruebaId=#{pruebaId}, pilotoId=#{pilotoId}, categoriaId=#{categoriaId}, modificationDate=#{modificationDate}, modificationUser=#{modificationUser} where inscritoId = #{inscritoId}")
	public int updateInscrito(InscritoEntity inscrito);

	/**
	 * Borra el inscrito.
	 *
	 * @param inscrito el inscrito
	 * @return the int
	 */
	@Update("Update INSCRITO set deleteDate=#{deleteDate}, deleteUser=#{deleteUser} where inscritoId = #{inscritoId}")
	public int delete(InscritoEntity inscrito);

	/**
	 * Obtiene el listado de inscritos.
	 *
	 * @return el inscritos
	 */
	@Select("SELECT inscritoId, pruebaId, pilotoId, categoriaId, creationDate,creationUser,modificationDate,modificationUser FROM INSCRITO I WHERE deleteDate IS NULL AND NOT EXISTS ( SELECT T.inscritoId from TRAMO T WHERE T.inscritoId = I.inscritoId )")
	List<InscritoEntity> getInscritosSinTramo();

}
