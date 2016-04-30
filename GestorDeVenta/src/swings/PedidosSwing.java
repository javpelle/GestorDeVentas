package swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gestorDeVenta.Cliente;

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
	private Cliente cliente;
	
	// Datos apartado cliente
		private JPanel info; 
		private JButton seleccionarPedido;
		private JButton nuevoPedido;
		private JList list;
		private JScrollPane scrollPane;
		
		// Datos apartado nuevo Cliente
		private JPanel nuevoPedid;
		private JTextField textField;
		private JButton btnAceptar;
		private JButton btnCancelar;
	
	public PedidosSwing(Cliente cliente) {
		this.cliente = cliente;
		setLayout(null);
		inicializarInfo();
		inicializarNuevoPedido();
		add(info);
	}
	
	private void inicializarInfo() {
		info = new JPanel();
		info.setBounds(5, 0, 675, 455);
		info.setBorder(new TitledBorder(null, cliente.toString(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		info.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 20, 460, 420);
		info.add(scrollPane);
		
		list = new JList(cliente.getListaPedidos().toArray());
		scrollPane.setViewportView(list);
		
		seleccionarPedido = new JButton("Seleccionar");
		seleccionarPedido.setBounds(490, 20, 140, 20);
		nuevoPedido = new JButton("Nuevo Cliente");
		nuevoPedido.setBounds(490, 50, 140, 20);
		info.add(nuevoPedido);
		info.add(seleccionarPedido);
		
		nuevoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//vistaNuevoCliente();
			}
		});
		
		seleccionarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// listener.seleccionar((Cliente) list.getSelectedValue());
			}
		});
		
	}
	
	private void inicializarNuevoPedido() {
		nuevoPedid = new JPanel();
		nuevoPedid.setLayout(null);
		nuevoPedid.setBounds(5, 0, 675, 455);
		textField = new JTextField();
		textField.setBounds(106, 11, 270, 20);
		nuevoPedid.add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String client = textField.getText();
				if (!client.equals("")) {
					//vistaInfo();
					//listener.aceptarNuevoCliente(textField.getText());
				} else {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "El campo nombre está vacío.");
				}
			}
		});
		btnAceptar.setBounds(190, 63, 89, 23);
		nuevoPedid.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(285, 63, 89, 23);
		nuevoPedid.add(btnCancelar);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//vistaInfo();
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 14, 46, 14);
		nuevoPedid.add(lblNombre);
		setVisible(true);
	}

	private static final long serialVersionUID = 1L;
}
