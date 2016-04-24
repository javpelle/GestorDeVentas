package swings;

import java.awt.BorderLayout;
import java.util.List;

import gestorDeVenta.Cliente;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ClientesSwing extends JPanel implements ListSelectionListener {
	private JList<Cliente> clientes;
	private JButton nuevoCliente;
	private JButton seleccionarCliente;
	
	public ClientesSwing(List<Cliente> listaClientes) {
		listaClientes.add(new Cliente("Mariano"));
		String nombres[] = new String[listaClientes.size()];
		
		
		for (int i = 0; i < listaClientes.size(); i++) {
			 nombres[i] = listaClientes.get(i).toString();
		}
		clientes = new JList(nombres);
		clientes.setVisibleRowCount(10);
		clientes.setFixedCellHeight(20);
		clientes.setFixedCellWidth(140);
		clientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clientes.addListSelectionListener(this);
		
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Clientes"));
		add(new JScrollPane(clientes));
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
