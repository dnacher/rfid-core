package com.software.rfid.core.enums.Logger;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Daniel
 */
public enum Codigo {

	//Login											000 - 020
	OK("OK", 0, HttpStatus.OK),
	ERROR_LOGIN_GENERAL("Hubo un error General", 1, HttpStatus.BAD_REQUEST),
	ERROR_LOGIN_FALTAN_DATOS_NOMBRE("Falta Ingresar el nombre de usuario", 2, HttpStatus.BAD_REQUEST),
	ERROR_LOGIN_FALTAN_DATOS_PASS("Falta Ingresar la contraseña", 3, HttpStatus.BAD_REQUEST),
	ERROR_LOGIN_DATOS_INCORRECTOS("El nombre o contraseña son incorrectos", 4, HttpStatus.BAD_REQUEST),
	ERROR_GENERAL("Hubo un error inesperado", 5, HttpStatus.BAD_REQUEST),
	ERROR_SET_PASSWORD("El usuario no tiene permisos para setear el nuevo password",6,HttpStatus.BAD_REQUEST),

	//Persona										021 - 040
	FALTA_NOMBRE("Falta nombre",21,HttpStatus.BAD_REQUEST),
	FALTA_DESCRIPCION("Falta descripcion",21,HttpStatus.BAD_REQUEST),
	FALTA_APELLIDO("Falta apellido",22,HttpStatus.BAD_REQUEST),
	FALTA_FECHA_NACIMIENTO("Falta fecha nacimiento",23,HttpStatus.BAD_REQUEST),
	FALTA_UID("Falta Tarjeta Id",24,HttpStatus.BAD_REQUEST),
	PERSONA_CON_ID_NO_SE_PUEDE_GUARDAR("La persona no puede tener un valor Id si es nueva", 25, HttpStatus.BAD_REQUEST),
	PERSONA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("La persona debe tener un id asociado para poderse actualizar",26, HttpStatus.BAD_REQUEST),

	//Clase - Materia
	CLASE_CON_ID_NO_SE_PUEDE_GUARDAR("La clase no puede tener un valor Id si es nueva", 25, HttpStatus.BAD_REQUEST),
	CLASE_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("La clase debe tener un id asociado para poderse actualizar",26, HttpStatus.BAD_REQUEST),
	MATERIA_CON_ID_NO_SE_PUEDE_GUARDAR("La materia no puede tener un valor Id si es nueva", 25, HttpStatus.BAD_REQUEST),
	MATERIA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("La materia debe tener un id asociado para poderse actualizar",26, HttpStatus.BAD_REQUEST),

	SIN_PERMISOS("Sin permisos, contacte al administrador", 403, HttpStatus.BAD_REQUEST),
	LOGUEO_ERROR ("Credenciales incorrectas",0, HttpStatus.BAD_REQUEST),
	VERIFICAR("Verificar", 1, HttpStatus.BAD_REQUEST),
	FALTA_TIPO_MONEDA("Falta tipo de moneda", 2, HttpStatus.BAD_REQUEST),
	FALTA_CONCEPTO("Falta concepto", 5, HttpStatus.BAD_REQUEST),
	FECHA_FIN_MENOR_INICIO("La fecha fin no puede ser menor a la de comienzo", 7, HttpStatus.BAD_REQUEST),
	ERROR("Error", 8, HttpStatus.BAD_REQUEST),
	WARNING("Warning", 9, HttpStatus.BAD_REQUEST);

	private final String error;
	private final int codigoError;
	private final HttpStatus httpStatus;

	Codigo(String error, int codigoError, HttpStatus httpStatus) {
		this.error = error;
		this.codigoError = codigoError;
		this.httpStatus = httpStatus;
	}

	public String getError() {
		return "[" + codigoError + "] " + error;
	}

	public int getCodigoError() {
		return codigoError;
	}

	public HttpStatus getHttpStatus() { return httpStatus; }

}
