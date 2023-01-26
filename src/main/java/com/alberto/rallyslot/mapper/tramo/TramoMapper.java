package com.alberto.rallyslot.mapper.tramo;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alberto.rallyslot.entity.tramo.TramoEntity;

/**
 * La interfaz que mapea los tramoes.
 */
public interface TramoMapper {

	/**
	 * Obtiene el tramo.
	 *
	 * @param tramoId the tramo id
	 * @return el tramo
	 */
	@Select("SELECT tramoId, tramoPruebaId, inscritoId, tiempo,creationDate,creationUser,modificationDate,modificationUser FROM TRAMO WHERE tramoId = #{tramoId} AND deleteDate IS NULL")
	TramoEntity getTramo(@Param("tramoId") Long tramoId);

	/**
	 * Obtiene el listado de tramos.
	 *
	 * @return el tramos
	 */
	@Select("SELECT tramoId, tramoPruebaId, inscritoId, tiempo,creationDate,creationUser,modificationDate,modificationUser FROM TRAMO WHERE deleteDate IS NULL")
	List<TramoEntity> getTramos();

	/**
	 * Inserta el tramo.
	 *
	 * @param tramo el tramo
	 * @return the int
	 */
	@Insert("INSERT INTO TRAMO(tramoPruebaId, inscritoId, tiempo,creationDate,creationUser) VALUES (#{tramoPruebaId}, #{inscritoId}, #{tiempo}, #{creationDate}, #{creationUser})")
	@Options(useGeneratedKeys = true, keyProperty = "tramoId", keyColumn = "tramoId")
	public long insertTramo(TramoEntity tramo);

	/**
	 * Actualiza el tramo.
	 *
	 * @param tramo the tramo
	 * @return the int
	 */
	@Update("Update TRAMO set tramoPruebaId=#{tramoPruebaId}, inscritoId=#{inscritoId}, tiempo=#{tiempo}, modificationDate=#{modificationDate}, modificationUser=#{modificationUser} where tramoId = #{tramoId}")
	public int updateTramo(TramoEntity tramo);

	/**
	 * Borra el tramo.
	 *
	 * @param tramo the tramo
	 * @return the int
	 */
	@Update("Update TRAMO set deleteUser=#{deleteUser}, deleteDate=#{deleteDate} where tramoId = #{tramoId}")
	public int deleteTramo(TramoEntity tramoEntity);

	/**
	 * Borra el tramo.
	 *
	 * @param tramo the tramo
	 * @return the int
	 */
	@Update("Update TRAMO set deleteUser=#{deleteUser}, deleteDate=#{deleteDate} where inscritoId = #{inscritoId} AND deleteDate IS NULL")
	public int deleteAllTramosByInscritoId(TramoEntity tramoEntity);

	@Insert({ "<script>", "INSERT INTO TRAMO(tramoPruebaId, inscritoId, tiempo,creationDate,creationUser)",
			"VALUES" + "<foreach item='each_item_name' collection='tramos' open='' separator=',' close=''>" + "("
					+ "#{each_item_name.tramoPruebaId,jdbcType=NUMERIC},",
			"#{each_item_name.inscritoId,jdbcType=NUMERIC},", "#{each_item_name.tiempo,jdbcType=NUMERIC},",
			"CURRENT_TIMESTAMP,", "#{each_item_name.creationUser,jdbcType=VARCHAR}" + ")" + "</foreach>",
			"</script>" })
	void insertBatchTramo(@Param("tramos") List<TramoEntity> tramos);

	/**
	 * Obtiene el listado de tramos.
	 *
	 * @return el tramos
	 */
	@Select("SELECT tramoId, tramoPruebaId, inscritoId, tiempo,creationDate,creationUser,modificationDate,modificationUser FROM TRAMO WHERE inscritoId = #{inscritoId} AND deleteDate IS NULL")
	List<TramoEntity> getTramosByInscrito(@Param("inscritoId") Long inscritoId);

	/**
	 * Borra el tramo.
	 *
	 * @param tramo the tramo
	 * @return the int
	 */
	@Update("Update TRAMO set tiempo=#{tiempo}, modificationUser=#{modificationUser}, modificationDate = CURRENT_TIMESTAMP where tiempo is null")
	public int initializeTramos(@Param("tiempo") BigDecimal tiempo, @Param("modificationUser") String modificationUser);

}
