package com.alberto.rallyslot.mapper.prueba;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alberto.rallyslot.entity.prueba.PruebaEntity;

/**
 * La interfaz que mapea los pruebas.
 */
public interface PruebaMapper {

	/**
	 * Obtiene la prueba.
	 *
	 * @param pruebaId la prueba id
	 * @return la prueba
	 */
	@Select("SELECT pruebaId, clubId, ano, pruebaName,secciones,tramos,creationDate,creationUser,modificationDate,modificationUser FROM PRUEBA WHERE pruebaId = #{pruebaId} AND deleteDate IS NULL")
	PruebaEntity getPrueba(@Param("pruebaId") Long pruebaId);

	/**
	 * Obtiene el listado de pruebas.
	 *
	 * @return la pruebas
	 */
	@Select("SELECT pruebaId, clubId, ano, pruebaName,secciones,tramos,creationDate,creationUser,modificationDate,modificationUser FROM PRUEBA WHERE deleteDate IS NULL")
	List<PruebaEntity> getPruebas();

	/**
	 * Inserta la prueba.
	 *
	 * @param prueba la prueba
	 * @return the int
	 */
	@Insert("INSERT INTO PRUEBA(clubId,ano,pruebaName,secciones,tramos,creationDate,creationUser) VALUES (#{clubId}, #{ano}, #{pruebaName}, #{secciones}, #{tramos}, #{creationDate}, #{creationUser})")
	@Options(useGeneratedKeys = true, keyProperty = "pruebaId", keyColumn = "pruebaId")
	public long insertPrueba(PruebaEntity prueba);

	/**
	 * Actualiza la prueba.
	 *
	 * @param prueba la prueba
	 * @return the int
	 */
	@Update("Update PRUEBA set clubId=#{clubId}, ano=#{ano}, pruebaName=#{pruebaName}, secciones=#{secciones}, tramos=#{tramos}, modificationDate=#{modificationDate}, modificationUser=#{modificationUser} where pruebaId = #{pruebaId}")
	public int updatePrueba(PruebaEntity prueba);

	/**
	 * Borra la prueba.
	 *
	 * @param prueba la prueba
	 * @return the int
	 */
	@Update("Update PRUEBA set deleteDate=#{deleteDate}, deleteUser=#{deleteUser} where pruebaId = #{pruebaId}")
	public int deletePrueba(PruebaEntity prueba);

}
