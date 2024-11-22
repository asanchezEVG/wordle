package wordle.programacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import wordle.interfaz.InterfazWordle;

/**
 * Clase que contiene la lógica del juego Wordle.
 */

public class Programacion implements InterfazWordle {
    String Solucion;

    /**
     * Genera una palabra aleatoria a partir de un fichero.
     */
    
    public void generar() {
        Random random = new Random();
        try {
            File file = new File("src/diccionario.txt");
            Scanner scanner = new Scanner(file);
            List<String> words = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                words.add(word);
            }
            scanner.close();
            if (!words.isEmpty()) {
                int randomIndex = random.nextInt(words.size());
                System.out.println("palabra = " + words.get(randomIndex));
                Solucion = words.get(randomIndex);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprueba la respuesta del jugador y actualiza la interfaz gráfica en consecuencia.
     *
     * @param respuesta  la respuesta del jugador.
     * @param jlbCelda   la matriz de etiquetas de letras en la interfaz gráfica.
     * @param fila       la fila actual en la que se está jugando.
     * @param intentos   el número de intentos realizados.
     * @return true si la respuesta es correcta y se ha ganado el juego, false de lo contrario.
     */
    
    public boolean comprobar(String respuesta, JLabel jlbCelda[][], int fila, int intentos) {
        char palabra[] = respuesta.toCharArray();
        char solucion[] = Solucion.toCharArray();

        for (int i = 0; i < 5; i++) {
            jlbCelda[fila][i].setText("" + palabra[i]);
        }

        for (int i = 0; i < palabra.length; i++) {
            for (int j = 0; j < palabra.length; j++) {
                if (palabra[i] == solucion[j]) {
                    jlbCelda[fila][i].setBackground(COLOR_LETRA_DESCOLOCADA);
                }
            }
        }
        for (int i = 0; i < palabra.length; i++) {
            if (palabra[i] == solucion[i]) {
                jlbCelda[fila][i].setBackground(COLOR_LETRA_ACIERTO);
                if (ganar(jlbCelda, fila, intentos, null))
                    return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si se ha ganado el juego, es decir, si se han acertado todas las letras en la fila actual.
     *
     * @param jlbCelda  la matriz de etiquetas de letras en la interfaz gráfica.
     * @param fila      la fila actual en la que se está jugando.
     * @param intentos  el número de intentos realizados.
     * @param objP      el panel de la interfaz gráfica.
     * @return true si se ha ganado el juego, false de lo contrario.
     */
    
    public boolean ganar(JLabel jlbCelda[][], int fila, int intentos, JPanel objP) {
        int verde = 0;
        int mensaje = 5;
        for (int i = 0; i < 5; i++) {
            if (jlbCelda[fila][i].getBackground().equals(COLOR_LETRA_ACIERTO) == true) {
                verde++;
            }
            if (verde == 5) {
                mensaje = JOptionPane.showOptionDialog(objP, "¿Jugar de nuevo?", "¡Has ganado!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            }
            if (mensaje == JOptionPane.YES_OPTION) {
                nuevo(jlbCelda, fila, intentos);
                return true;
            } else if (mensaje == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
        return false;
    }

    /**
     * Reinicia el juego con una nueva palabra y reinicia la interfaz gráfica.
     *
     * @param jlbCelda  la matriz de etiquetas de letras en la interfaz gráfica.
     * @param fila      la fila actual en la que se está jugando.
     * @param intentos  el número de intentos realizados.
     */
    
    private void nuevo(JLabel[][] jlbCelda, int fila, int intentos) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                jlbCelda[i][j].setText(null);
                jlbCelda[i][j].setBackground(COLOR_LETRA_FONDO);
            }
        }
        fila = 0;
        intentos = 0;
        generar();
    }

    /**
     * Realiza una acción cuando se agotan los intentos del jugador.
     *
     * @param intentos  el número de intentos realizados.
     * @param jlbCelda  la matriz de etiquetas de letras en la interfaz gráfica.
     * @param objP      el panel de la interfaz gráfica.
     */
    
    public void intentos(int intentos, JLabel[][] jlbCelda, JPanel objP) {
        intentos++;
        int respuesta = 5;

        if (intentos == 5) {
            respuesta = JOptionPane.showOptionDialog(objP, "¿Jugar de nuevo?", "SI-NO",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        }

        if (respuesta == JOptionPane.YES_OPTION) {
            nuevo(jlbCelda, respuesta, respuesta);
        } else if (respuesta == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
}