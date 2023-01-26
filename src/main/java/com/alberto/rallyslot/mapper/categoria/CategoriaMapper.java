package com.alberto.rallyslot.mapper.categoria;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alberto.rallyslot.entity.categoria.CategoriaEntity;

/**
 * La interfaz que mapea las categorías.
 */
public interface CategoriaMapper {

	/**
	 * Obtiene la categoría.
	 *
	 * @param categoriaId the categoria id
	 * @return la categoría
	 */
	@Select("SELECT categoriaId, categoriaName,creationDate,creationUser,modificationDate,modificationUser FROM CATEGORIA WHERE categoriaId = #{categoriaId} AND deleteDate IS NULL")
	CategoriaEntity getCategoria(@Param("categoriaId") Long categoriaId);

	/**
	 * Obtiene el listado de categorias.
	 *
	 * @return la categorías
	 */
	@Select("SELECT categoriaId, categoriaName,creationDate,creationUser,modificationDate,modificationUser FROM CATEGORIA WHERE deleteDate IS NULL")
	List<CategoriaEntity> getCategorias();

	/**
	 * Inserta la categoría.
	 *
	 * @param categoria la categoría
	 * @return the int
	 */
	@Insert("INSERT INTO CATEGORIA(categoriaName,creationDate,creationUser) VALUES (#{categoriaName}, #{creationDate}, #{creationUser})")
	@Options(useGeneratedKeys = true, keyProperty = "categoriaId", keyColumn = "categoriaId")
	public long insertCategoria(CategoriaEntity categoria);

	/**
	 * Actualiza la categoría.
	 *
	 * @param categoria the categoria
	 * @return the int
	 */
	@Update("Update CATEGORIA set categoriaName=#{categoriaName}, modificationDate=#{modificationDate}, modificationUser=#{modificationUser} where categoriaId = #{categoriaId}")
	public int updateCategoria(CategoriaEntity categoria);

	/**
	 * Borra la categoría.
	 *
	 * @param categoria the categoria
	 * @return the int
	 */
	@Update("Update CATEGORIA set deleteDate=#{deleteDate}, deleteUser=#{deleteUser} where categoriaId = #{categoriaId}")
	public int deleteCategoria(CategoriaEntity categoria);

}
