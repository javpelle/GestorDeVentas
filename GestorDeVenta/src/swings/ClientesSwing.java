package swings;

import java.util.List;

import gestorDeVenta.Cliente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientesSwing extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Datos comunes
	private List<Cliente> listaClientes;
	private ClientesSwingListener listener;
	
	// Datos apartado clientes
	private JPanel info; 
	private JButton seleccionarCliente;
	private JButton nuevoCliente;
	private JList list;
	private JScrollPane scrollPane;
	
	// Datos apartado nuevo Cliente
	private JPanel nuevoClient;
	private JTextField textField;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public ClientesSwing(List<Cliente> listaClientes, ClientesSwingListener listener) {
		this.listaClientes = listaClientes;
		this.listener = listener;
		setLayout(null);
		inicializarInfo();
		inicializarNuevoCliente();
		add(info);
	}
	
	private void inicializarInfo() {
		info = new JPanel();
		info.setBounds(5, 0, 675, 455);
		info.setBorder(new TitledBorder(null, "Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		info.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 20, 460, 420);
		
		list = new JList(listaClientes.toArray());
		list.setSelectedIndex(0);
		scrollPane.setViewportView(list);
		info.add(scrollPane);
		
		seleccionarCliente = new JButton("Seleccionar");
		seleccionarCliente.setBounds(490, 20, 140, 20);
		nuevoCliente = new JButton("Nuevo Cliente");
		nuevoCliente.setBounds(490, 50, 140, 20);
		info.add(nuevoCliente);
		info.add(seleccionarCliente);
		
		nuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaNuevoCliente();
			}
		});
		
		seleccionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.seleccionar((Cliente) list.getSelectedValue());
			}
		});
		
	}
	
	private void inicializarNuevoCliente() {
		nuevoClient = new JPanel();
		nuevoClient.setLayout(null);
		nuevoClient.setBounds(5, 0, 675, 455);
		textField = new JTextField();
		textField.setBounds(106, 11, 270, 20);
		nuevoClient.add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String client = textField.getText();
				if (!client.equals("")) {
					vistaInfo();
					listener.aceptarNuevoCliente(textField.getText());
				} else {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "El campo nombre está vacío.");
				}
			}
		});
		btnAceptar.setBounds(190, 63, 89, 23);
		nuevoClient.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(285, 63, 89, 23);
		nuevoClient.add(btnCancelar);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vistaInfo();
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 14, 46, 14);
		nuevoClient.add(lblNombre);
	}

	public void update(List<Cliente> listaClientes) {
		scrollPane.remove(list);
		list = new JList(listaClientes.toArray());
		list.setSelectedIndex(0);
		scrollPane.setViewportView(list);
	}
	
	public interface ClientesSwingListener {
		
		public void seleccionar(Cliente cliente);
		
		public void aceptarNuevoCliente(String cliente);
	}

	private void vistaNuevoCliente() {
		remove(info);
		add(nuevoClient);
		repaint();
	}
	
	private void vistaInfo() {
		remove(nuevoClient);
		add(info);
		repaint();
	}
	
}
