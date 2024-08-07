package com.software.rfid.core.constantes;

/*
 * @author Dani-Fla-Mathi
 */
public final class Constantes {

	private Constantes(){}

	/*
     *                          RUTAS
	 */
	public static final String PAGINA_ROOT = "/fragments/";
	public static final String IMAGEN_ROOT = "/images/";
	public static final String LOGO = "/images/icono.png";

	/*
     *                          MENUS
	 */
	public static final String EXTENSION_FXML = ".fxml";
	public static final String EXTENSION_PNG = ".png";
	public static final String MENU_SEGURIDAD = "MenuSeguridad";
	public static final String MENU_CONFIGURACION = "MenuConfiguracion";
	public static final String MENU_PRINCIPAL = "MenuPrincipal";
	public static final String MENU_ESTADOS = "MenuEstados";
	public static final String MENU_TIPOS = "MenuTipos";

	public static final String PAGINA_INI = Constantes.PAGINA_ROOT + "splash" + Constantes.EXTENSION_FXML;
	public static final String PAGINA_LOGIN = Constantes.PAGINA_ROOT + "login" + Constantes.EXTENSION_FXML;

	/*
     *                         PAGINAS
	 */
	public static final String PAGINA_FORM_MENU = Constantes.PAGINA_ROOT + "formMenu" + Constantes.EXTENSION_FXML;
	public static final String PAGINA_MAIN = Constantes.PAGINA_ROOT + "Inicio" + Constantes.EXTENSION_FXML;

	/*
     *                         IMAGENES
	 */
	public static final String VERDE = IMAGEN_ROOT + "verde" + EXTENSION_PNG;
	public static final String AMARILLO = IMAGEN_ROOT + "amarillo" + EXTENSION_PNG;
	public static final String ROJO = IMAGEN_ROOT + "rojo" + EXTENSION_PNG;
	public static final String AZUL = IMAGEN_ROOT + "azul" + EXTENSION_PNG;


	public static final String SBN = "SBN Group";

	/*
     *                             OTROS
	 */
	public static final String EXCEL = "Libro Excel 97-2003";
	public static final String EXTENSION_EXCEL = "*.xls";
	public static final String ESTADO = "El estado esta asociado en otro registro";

}
