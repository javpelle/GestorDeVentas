package swings;

import gestorDeVenta.Gestor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import swings.NuevoCliente.NuevoClienteListener;

public class Swing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ClientesSwing clientesSwing;
	
	public Swing (String archivo, final Gestor gestor) {
		super ("Gestor de ventas: " + archivo);
		setSize(new Dimension(1150, 700));
	    getContentPane().setLayout(new BorderLayout());      
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientesSwing = new ClientesSwing(gestor.getClientes(), new NuevoClienteListener() {

			@Override
			public void insertarNuevoCliente(String nombre) {
				if (gestor.crearCliente(nombre)) {
					clientesSwing.update(gestor.getClientes());
				}
			}
			
		});
		getContentPane().add(clientesSwing);
		clientesSwing.setLayout(null);
		setVisible(true);
	}
}
