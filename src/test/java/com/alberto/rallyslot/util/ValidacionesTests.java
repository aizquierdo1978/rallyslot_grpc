package com.alberto.rallyslot.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;

@ActiveProfiles("test")
class ValidacionesTests {

	private static final Log LOG = LogFactory.getLog(ValidacionesTests.class);
	
	private static final String TLF = "TLF:";
	private static final String PREFIJO_ES = "34";
	private static final String PREFIJO_ES_MAS = "+34";
	private static final String PREFIJO_ES_CEROS = "0034";
	private static final String PREFIJO_ES_UNOS = "1134";
	private static final String PREFIJO_PT = "351:";
	private static final String PREFIJO_PT_MAS = "+351";
	private static final String PREFIJO_PT_CEROS = "00351";
	private static final String PREFIJO_PT_UNOS = "11351";
	private static final String COD_PAIS_ES = "ES";
	private static final String COD_PAIS_PT = "PT";
	private static final String MSG_TELEFONO_NO_VALIDO = "Se esperaba un telefono vacio";
	private static final String TELEFONO_COMUN = "00340351";
	private static final String SEIS = "6";
	private static final String OCHO = "8";
	private static final String NUEVE = "9";

	/**
	 * Test de teléfono nulo
	 */
	@Test
	void validateTelefonoNulo() {
		String assertMessage = "Test de telefono nulo. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(null, COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono nulo
	 */
	@Test
	void validateTelefonoVacio() {
		String assertMessage = "Test de telefono vacio. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono("", COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono no válido
	 */
	@Test
	void validateTelefonoPrefijoNoValidoES() {
		String assertMessage = "Test de telefono no valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono("1234567", COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono no válido
	 */
	@Test
	void validateTelefonoPrefijoNoValido2ES() {
		String assertMessage = "Test de telefono no valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono("123456722222", COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono no válido
	 */
	@Test
	void validateTelefonoPrefijoNoValido3ES() {
		String assertMessage = "Test de telefono no valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_PT + getTelefono(SEIS), COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono no válido
	 */
	@Test
	void validateTelefonoPrefijoNoValido4ES() {
		String assertMessage = "Test de telefono no valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_ES + getTelefono(OCHO), COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono válido
	 */
	@Test
	void validateTelefonoValidoES() {
		String assertMessage = "Test de telefono valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_ES + getTelefono(SEIS), COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono válido
	 */
	@Test
	void validateTelefonoValido2ES() {
		String assertMessage = "Test de telefono valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_ES_CEROS + getTelefono(SEIS), COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono válido
	 */
	@Test
	void validateTelefonoValido3ES() {
		String assertMessage = "Test de telefono valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_ES_UNOS + getTelefono(SEIS), COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono válido
	 */
	@Test
	void validateTelefonoPrefijoValid4ES() {
		String assertMessage = "Test de telefono valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_ES_MAS + getTelefono(SEIS), COD_PAIS_ES);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono no válido
	 */
	@Test
	void validateTelefonoPrefijoNoValidoPT() {
		String assertMessage = "Test de telefono no valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono("1234567", COD_PAIS_PT);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de prefijo no válido
	 */
	@Test
	void validateTelefonoPrefijoNoValido2PT() {
		String assertMessage = "Test de telefono no valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_PT + getTelefono(SEIS), COD_PAIS_PT);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono no válido
	 */
	@Test
	void validateTelefonoPrefijoNoValido3PT() {
		String assertMessage = "Test de telefono no valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_PT + getTelefono(SEIS), COD_PAIS_PT);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono válido
	 */
	@Test
	void validateTelefonoValidoPT() {
		String assertMessage = "Test de telefono valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_PT + getTelefono(String.valueOf(NUEVE)),
					COD_PAIS_PT);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono válido
	 */
	@Test
	void validateTelefonoValido2PT() {
		String assertMessage = "Test de telefono valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_PT_CEROS + getTelefono(String.valueOf(NUEVE)),
					COD_PAIS_PT);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono válido
	 */
	@Test
	void validateTelefonoValido3PT() {
		String assertMessage = "Test de telefono valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_PT_UNOS + getTelefono(String.valueOf(NUEVE)),
					COD_PAIS_PT);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	/**
	 * Test de teléfono válido
	 */
	@Test
	void validateTelefonoPrefijoValid4PT() {
		String assertMessage = "Test de telefono valido. ";
		Exception ex = null;
		try {
			String telefonoValidado = Validaciones.validaTelefono(PREFIJO_PT_MAS + getTelefono(String.valueOf(NUEVE)),
					COD_PAIS_PT);
			assertNotNull(telefonoValidado, assertMessage + ConstantesTests.ERROR_NULL);
			assertTrue(!StringUtils.isEmpty(telefonoValidado), assertMessage + MSG_TELEFONO_NO_VALIDO);
		} catch (Exception e) {
			LOG.error(assertMessage + ConstantesTests.ERROR_NO_CONTROLADO, e);
			ex = e;
		}

		assertNull(ex, assertMessage + ConstantesTests.ERROR_NO_CONTROLADO);
	}

	private String getTelefono(String primeraCifra) {
		return primeraCifra + TELEFONO_COMUN;
	}

}
