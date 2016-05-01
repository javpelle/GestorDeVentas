package swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gestorDeVenta.Cliente;
import gestorDeVenta.Pedido;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PedidosSwing extends JPanel {
	// elementos comunes
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private PedidosSwingListener listener;
	
	// Datos apartado cliente
		private JPanel info; 
		private JButton seleccionarPedido;
		private JButton eliminarPedido;
		private JButton nuevoPedido;
		private JButton atras;
		private JList list;
		private JScrollPane scrollPane;
		
		// Datos apartado nuevo Cliente
		private JPanel nuevoPedid;
		private JTextField nombre;
		private JTextField direccion1;
		private JTextField direccion2;
		private JTextField localidad;
		private JTextField cPostal;
		private JTextField provincia;
		private JButton btnAceptar;
		private JButton btnCancelar;
	
	public PedidosSwing(Cliente cliente, PedidosSwingListener listener) {
		this.cliente = cliente;
		this.listener = listener;
		setLayout(null);
		//this.cliente.listaPedidos.add(new Pedido("hola"));
		inicializarInfo();
		inicializarNuevoPedido();
		add(info);
		repaint();
	}
	
	private void inicializarInfo() {
		info = new JPanel();
		info.setBounds(5, 0, 675, 455);
		info.setBorder(new TitledBorder(null, cliente.toString(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		info.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 20, 460, 420);
		
		list = new JList(cliente.getListaPedidos().toArray());
		list.setSelectedIndex(0);
		scrollPane.setViewportView(list);
		info.add(scrollPane);
		
		seleccionarPedido = new JButton("Seleccionar");
		seleccionarPedido.setBounds(490, 20, 140, 20);
		eliminarPedido = new JButton("Eliminar");
		eliminarPedido.setBounds(490, 50, 140, 20);
		nuevoPedido = new JButton("Nuevo Pedido");
		nuevoPedido.setBounds(490, 80, 140, 20);
		atras = new JButton("Atrás");
		atras.setBounds(490, 110, 140, 20);
		info.add(nuevoPedido);
		info.add(seleccionarPedido);
		info.add(eliminarPedido);
		info.add(atras);	
		
		nuevoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaNuevoPedido();
			}
		});
		
		seleccionarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// listener.seleccionar((Cliente) list.getSelectedValue());
			}
		});
		
		eliminarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Eliminamos un pedido
			}
		});
		
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.atras();
			}
		});
		
	}
	
	private void inicializarNuevoPedido() {
		nuevoPedid = new JPanel();
		nuevoPedid.setLayout(null);
		nuevoPedid.setBounds(5, 0, 675, 455);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnAceptar.setBounds(190, 200, 89, 23);
		nuevoPedid.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(285, 200, 89, 23);
		nuevoPedid.add(btnCancelar);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vistaInfo();
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 11, 100, 14);
		JLabel lblDireccion1 = new JLabel("Dirección 1");
		lblDireccion1.setBounds(31, 41, 100, 14);
		JLabel lblDireccion2 = new JLabel("Dirección 2");
		lblDireccion2.setBounds(31, 71, 100, 14);
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(31, 101, 100, 14);
		JLabel lblCPostal = new JLabel("Código Postal");
		lblCPostal.setBounds(31, 131, 100, 14);
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(31, 161, 100, 14);
		nuevoPedid.add(lblNombre);
		nuevoPedid.add(lblDireccion1);
		nuevoPedid.add(lblDireccion2);
		nuevoPedid.add(lblLocalidad);
		nuevoPedid.add(lblCPostal);
		nuevoPedid.add(lblProvincia);
		
		nombre = new JTextField();
		nombre.setBounds(150, 11, 270, 20);
		direccion1 = new JTextField();
		direccion1.setBounds(150, 41, 270, 20);
		direccion2 = new JTextField();
		direccion2.setBounds(150, 71, 270, 20);
		localidad = new JTextField();
		localidad.setBounds(150, 101, 270, 20);
		cPostal = new JTextField();
		cPostal.setBounds(150, 131, 270, 20);
		provincia = new JTextField();
		provincia.setBounds(150, 161, 270, 20);
		nuevoPedid.add(nombre);
		nuevoPedid.add(direccion1);
		nuevoPedid.add(direccion2);
		nuevoPedid.add(localidad);
		nuevoPedid.add(cPostal);
		nuevoPedid.add(provincia);
		
	}
	
	public interface PedidosSwingListener {
		public void atras();
	}
	
	private void vistaNuevoPedido() {
		remove(info);
		add(nuevoPedid);
		repaint();
	}
	
	private void vistaInfo() {
		remove(nuevoPedid);
		add(info);
		repaint();
	}
}
