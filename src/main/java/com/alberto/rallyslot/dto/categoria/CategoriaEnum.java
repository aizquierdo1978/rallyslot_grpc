package com.alberto.rallyslot.dto.categoria;

import java.util.HashMap;
import java.util.Map;

import com.alberto.rallyslot.common.EnumBase;

public enum CategoriaEnum implements EnumBase {

	WRC(1, "WRC", "WRC"), A(2, "A", "A"), K(3, "K", "K"), N(4, "N", "N");

	private int id;
	private String codigo;
	private String nombre;

	private static final Map<String, CategoriaEnum> map;

	static {
		map = new HashMap<>();
		for (CategoriaEnum v : CategoriaEnum.values()) {
			map.put(v.getCodigo(), v);
		}
	}

	/**
	 * Instantiates a new categoria enum.
	 *
	 * @param id     el id
	 * @param codigo el codigo
	 * @param nombre el nombre
	 */
	private CategoriaEnum(int id, String codigo, String nombre) {
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

	public static CategoriaEnum findPorCodigo(String codigo) {
		return map.get(codigo);
	}

	@Override
	public Enum buscaPorValidacion(Object busqueda) {
		return map.get(busqueda);
	}

}
