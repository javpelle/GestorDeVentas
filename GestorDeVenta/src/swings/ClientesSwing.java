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

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;

import swings.NuevoCliente.NuevoClienteListener;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Color;

public class ClientesSwing extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton seleccionarCliente = new JButton("Seleccionar");
	private JButton nuevoCliente = new JButton("Nuevo Cliente");
	@SuppressWarnings("rawtypes")
	private JList list;
	private final JScrollPane scrollPane_1;
	
	public ClientesSwing(List<Cliente> listaClientes, final NuevoClienteListener listenerNuevo) {
		setBackground(Color.LIGHT_GRAY);
		setBorder(new TitledBorder(null, "Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 24, 1111, 595);
		add(scrollPane_1);
		
		list = new JList(listaClientes.toArray());
		scrollPane_1.setViewportView(list);
		nuevoCliente.setBounds(977, 630, 130, 23);
		nuevoCliente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new NuevoCliente(listenerNuevo);
			}
		});
		add(nuevoCliente);
		seleccionarCliente.setBounds(829, 630, 125, 23);
		seleccionarCliente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.print(list.getSelectedValue() + "\n");
			}
		});
		add(seleccionarCliente);
	
	}

	public void update(List<Cliente> listaClientes) {
		remove(list);
		list = new JList(listaClientes.toArray());
		scrollPane_1.setViewportView(list);
		nuevoCliente.setBounds(977, 630, 130, 23);
	}
}
