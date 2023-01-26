package com.alberto.rallyslot.dto.categoria;

import java.util.HashMap;
import java.util.Map;

import com.alberto.rallyslot.common.EnumBase;

public enum TipoTraccionEnum implements EnumBase {

	DELANTERA(1, "D", "Delantera"), TRASERA(2, "T", "Trasera"), TOTAL(3, "4", "TOTAL");

	private int id;
	private String codigo;
	private String nombre;

	private static final Map<Integer, TipoTraccionEnum> mapId;
	private static final Map<String, TipoTraccionEnum> mapCodigo;

	static {
		mapId = new HashMap<>();
		for (TipoTraccionEnum v : TipoTraccionEnum.values()) {
			mapId.put(v.getId(), v);
		}
		mapCodigo = new HashMap<>();
		for (TipoTraccionEnum v : TipoTraccionEnum.values()) {
			mapCodigo.put(v.getCodigo(), v);
		}
	}

	/**
	 * Instantiates a new categoria enum.
	 *
	 * @param id     el id
	 * @param nombre el nombre
	 */
	private TipoTraccionEnum(int id, String codigo, String nombre) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Find por id.
	 *
	 * @param id the id
	 * @return the tipo traccion enum
	 */
	public static TipoTraccionEnum findPorId(Integer id) {
		return mapId.get(id);
	}

	/**
	 * Find por codigo.
	 *
	 * @param id the id
	 * @return the tipo traccion enum
	 */
	public static TipoTraccionEnum codigo(String codigo) {
		return mapCodigo.get(codigo);
	}

	@Override
	public Enum buscaPorValidacion(Object busqueda) {
		return mapCodigo.get(busqueda);
	}



}
