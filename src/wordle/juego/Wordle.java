package wordle.juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import wordle.interfaz.InterfazWordle;
import wordle.programacion.Programacion;

/**
 * Clase que representa el juego Wordle.
 */

@SuppressWarnings("serial")
public class Wordle extends JFrame implements InterfazWordle {

	JPanel jpnPanel = null;
	JPanel jpnPanelFondo = null;
	JLabel jlbCelda[][] = null;
	JTextField jtxtRespuesta = null;
	int fila = 0;
	int intentos = 0;
	boolean restarting = false;
	Programacion obj = new Programacion();
	JPanel objP = null;
		
	/**
     * Constructor de la clase Wordle.
     */
	
	public Wordle() {
		disenio();
		obj.generar();
		setVisible(true);
	}
	
	private void disenio() {
		setSize(550, 550);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("WORDLE");
		
		jpnPanelFondo = new JPanel();
		jpnPanelFondo.setLayout(null);
		jpnPanelFondo.setBounds(0, 0, 550, 550);
		jpnPanelFondo.setBackground(COLOR_FONDO);
		add(jpnPanelFondo);
		
		jpnPanel = new JPanel();
		jpnPanel.setSize(550, 550);
		jpnPanel.setLayout(new GridLayout(5,5,2,2));
		jpnPanel.setBounds(65, 10, 400, 400);
		jpnPanel.setBackground(COLOR_FONDO);
		jpnPanelFondo.add(jpnPanel);

		Font fuente = new Font("SansSerif", Font.BOLD, 35);
		
		jlbCelda = new JLabel[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				jlbCelda[i][j] = new JLabel();
				jlbCelda[i][j].setBackground(COLOR_LETRA_FONDO);
				jlbCelda[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				jlbCelda[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				jlbCelda[i][j].setFont(fuente);
				jlbCelda[i][j].setOpaque(true);
				jpnPanel.add(jlbCelda[i][j]);
			}
		}
		
		jtxtRespuesta = new JTextField();
		jtxtRespuesta.setBounds(165, 425, 200, 65);
		jtxtRespuesta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		jtxtRespuesta.setFont(fuente);
		jtxtRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		jpnPanelFondo.add(jtxtRespuesta);
		jtxtRespuesta.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				 char c = e.getKeyChar();
			        if (Character.isLetter(c)) {
			            String text = jtxtRespuesta.getText();
			            if (text.length() < 5) {
			                jtxtRespuesta.setText(text + Character.toUpperCase(c));
			            }
			        }
			        e.consume();
			}
			
			public void keyReleased(KeyEvent e) {
				String respuesta = jtxtRespuesta.getText();
				char[] letra = respuesta.toCharArray();
				
				if(jtxtRespuesta.getText().length()==5) {
					if(e.getKeyChar()==KeyEvent.VK_ENTER) {
						for(int j=0; j<5; j++) {
							jlbCelda[fila][j].setText(String.valueOf(letra[j]));
							jtxtRespuesta.setText(null);
						}
						if (!obj.comprobar(respuesta, jlbCelda, fila, intentos)) {
							obj.intentos(fila, jlbCelda, objP);
							fila++;
						}
						else {
							fila=0;
						}
					}
			}
		}
			
			public void keyPressed(KeyEvent e) {

			}
		});
	}
}

