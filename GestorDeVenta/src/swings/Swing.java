package swings;

import gestorDeVenta.Gestor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Swing extends JFrame {
	
	public Swing (String archivo, Gestor gestor) {
		super ("Gestor de ventas: " + archivo);
		setSize(new Dimension(1150, 700));
	    setLayout(new BorderLayout());      
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new ClientesSwing(gestor.getClientes()));
		setVisible(true);
	}
}
