package swings;

import gestorDeVenta.Cliente;
import gestorDeVenta.Gestor;
import gestorDeVenta.Pedido;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import swings.ClientesSwing.ClientesSwingListener;
import swings.PedidosSwing.PedidosSwingListener;

public class Swing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ClientesSwing clientesSwing;
	private PedidosSwing pedidosSwing;
	private Gestor gestor;
	
	public Swing (String archivo, final Gestor gestor) {
		super ("Gestor de ventas: " + archivo);
		this. gestor = gestor;
		setSize(new Dimension(700, 500));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		clientesSwing = new ClientesSwing(gestor, new ClientesSwingListener() {

			@Override
			public void seleccionar(Cliente cliente) {
				if (cliente != null) {
					remove(clientesSwing);
					vistaCliente(cliente);
					pedidosSwing.setBounds(0, 0, 684, 461);
					add(pedidosSwing);
					repaint();
					// pedidosSwing.update();
				}
			}

			@Override
			public void aceptarNuevoCliente(String cliente) {
				if (gestor.crearCliente(cliente)) {
					clientesSwing.update();
					gestor.guardar();
				} else {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "El campo nombre está vacío.");
				}
				
			}
		});
		clientesSwing.setBounds(0, 0, 684, 461);
		add(clientesSwing);
		setVisible(true);
	}
	
	private void vistaCliente(Cliente cliente) {
		pedidosSwing = new PedidosSwing(cliente, new PedidosSwingListener(){

			public void atras() {
				remove(pedidosSwing);
				add(clientesSwing);
				repaint();
			}

			@Override
			public void crearPedido(String direccion, Cliente cliente) {
				cliente.crearPedido(direccion);
				pedidosSwing.update();
				gestor.guardar();
			}

			@Override
			public void eliminarPedido(Pedido pedido, Cliente cliente) {
				cliente.eliminarPedido(pedido);
				pedidosSwing.update();
				gestor.guardar();
			}
			
		});
	}
}
