package swings;

import gestorDeVenta.Cliente;
import gestorDeVenta.Gestor;

import java.awt.Dimension;

import javax.swing.JFrame;

import swings.ClientesSwing.ClientesSwingListener;

public class Swing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ClientesSwing clientesSwing;
	private PedidosSwing pedidosSwing;
	
	public Swing (String archivo, final Gestor gestor) {
		super ("Gestor de ventas: " + archivo);
		setSize(new Dimension(700, 500));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		clientesSwing = new ClientesSwing(gestor.getClientes(), new ClientesSwingListener() {

			@Override
			public void seleccionar(Cliente cliente) {
				vistaCliente(cliente);
			}

			@Override
			public void aceptarNuevoCliente(String cliente) {
				gestor.crearCliente(cliente);
				clientesSwing.update(gestor.getClientes());
			}
		});
		clientesSwing.setBounds(0, 0, 684, 461);
		getContentPane().add(clientesSwing);
		setVisible(true);
	}
	
	private void vistaCliente(Cliente cliente) {
		remove(clientesSwing);
		pedidosSwing = new PedidosSwing(cliente);
		pedidosSwing.setBounds(0, 0, 684, 461);
		getContentPane().add(pedidosSwing);
		repaint();
	}
}
