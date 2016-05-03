package swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import gestorDeVenta.Cliente;
import gestorDeVenta.Pedido;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ClienteSwing extends JPanel {
	// elementos comunes
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private List<Pedido> pedidosCliente;
	private ClienteSwingListener listener;
	
	// Datos apartado cliente
	private JPanel info; 
	private JButton seleccionarPedido;
	private JButton eliminarPedido;
	private JButton nuevoPedido;
	private JButton atras;
	private JList<Object> list;
	private JScrollPane scrollPanePedidos;
	private JLabel lblBruto;
	private JLabel lblCostes;
	private JLabel lblneto;	
	
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
	
	public ClienteSwing(Cliente cliente, ClienteSwingListener listener) {
		this.cliente = cliente;
		pedidosCliente = cliente.getListaPedidos();
		this.listener = listener;
		setLayout(null);
		inicializarInfo();
		inicializarNuevoPedido();
		add(info);
	}
	
	private void inicializarInfo() {
		info = new JPanel();
		info.setBounds(5, 0, 675, 455);
		info.setLayout(null);
		info.setBorder(new TitledBorder(null, cliente.toString(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		scrollPanePedidos = new JScrollPane();
		scrollPanePedidos.setBounds(8, 20, 460, 420);
		
		info.add(scrollPanePedidos);
		
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
				listener.seleccionarPedido((Pedido) list.getSelectedValue());
			}
		});
		
		eliminarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.eliminarPedido((Pedido)list.getSelectedValue(), cliente);
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
				String direccion = nombre.getText() + ";" + direccion1.getText() +
				";" + direccion2.getText() + ";" + localidad.getText() +
				";" + cPostal.getText() + ";" + provincia.getText();
				vistaInfo();
				listener.crearPedido(direccion, cliente);
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
	
	public interface ClienteSwingListener {
		
		public void atras();
		
		public void crearPedido(String direccion, Cliente cliente);
		
		public void eliminarPedido(Pedido pedido, Cliente cliente);
		
		public void seleccionarPedido(Pedido pedido);
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

	public void update() {
		if (list != null) {
			scrollPanePedidos.remove(list);
			info.remove(lblBruto); info.remove(lblCostes); info.remove(lblneto);
		}
			
		list = new JList<Object>(pedidosCliente.toArray());
		list.setSelectedIndex(0);
		scrollPanePedidos.setViewportView(list);
		lblBruto = new JLabel("Beneficios brutos = " + Float.toString(cliente.getBeneficioBruto()) + " €");
		lblBruto.setBounds(480, 300, 180, 14);
		info.add(lblBruto);
		lblCostes = new JLabel("Costes = " + Float.toString(cliente.getCoste()) + " €");
		lblCostes.setBounds(480, 340, 180, 14);
		info.add(lblCostes);
		lblneto = new JLabel("Beneficios brutos = " + Float.toString(cliente.getBeneficioNeto()) + " €");
		lblneto.setBounds(480, 380, 180, 14);
		info.add(lblneto);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	listener.seleccionarPedido((Pedido) list.getSelectedValue());
		        }
		    }
		});
		info.repaint();
	}
	
	public Cliente getCliente() {return cliente;}
}
