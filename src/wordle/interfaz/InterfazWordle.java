package wordle.interfaz;

import java.awt.Color;
import java.awt.Font;

/**
 * Interfaz que contiene las constantes de colores y fuentes utilizadas en el juego Wordle.
 */

public interface InterfazWordle {

    /**
     * Color de fondo utilizado en el juego.
     */
	
	final static Color COLOR_FONDO = new Color (134, 225, 225);
	
	/**
     * Color de las letras utilizadas en el juego.
     */
	
	final static Color COLOR_LETRA = new Color (0, 0, 0);
	
	 /**
     * Fuente utilizada en los cuadros de texto del juego.
     */
	
	final static Font LETRA_CUADRO_TEXTO = new Font("Verdana", Font.BOLD, 15);
	/**
     * Color del título utilizado en el juego.
     */
	
	final static Color COLOR_TITULO = new Color (122, 212, 64);
	
	/**
     * Color de fondo de las letras en el juego.
     */
	
	final static Color COLOR_LETRA_FONDO = new Color (255, 255, 255);
	
	/**
     * Color de las letras en los intentos del juego.
     */
	
	final static Color COLOR_LETRA_INTENTO = new Color (135, 138, 133);
	
	/**
     * Color de las letras descolocadas en el juego.
     */
	
	final static Color COLOR_LETRA_DESCOLOCADA = new Color (218, 218, 50);	
	
	 /**
     * Color de las letras acertadas en el juego.
     */
	
	final static Color COLOR_LETRA_ACIERTO = new Color (109, 216, 88);
  
}
