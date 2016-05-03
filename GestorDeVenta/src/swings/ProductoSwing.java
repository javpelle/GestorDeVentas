package swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestorDeVenta.Pedido;
import gestorDeVenta.Producto;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ProductoSwing extends JPanel {
	
	private Producto producto;
	ProductoSwingListener listener;
	
	// Datos apartado producto
	private JPanel info; 
	private JButton modificar;
	private JButton atras;
	private JLabel lblnombre;
	private JLabel lblTalla;
	private JLabel lblBruto;
	private JLabel lblCostes;
	private JLabel lblneto;
	private JLabel lblCodSeguimiento;
		
	// Datos apartado nuevo Cliente
	private JPanel nuevoPedid;
	private JTextField nombre;
	private JTextField talla;
	private JTextField bruto;
	private JTextField costes;
	private JTextField neto;
	private JTextField codseguimiento;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public ProductoSwing (Producto producto, ProductoSwingListener listener) {
		this.producto = producto;
		this.listener = listener;
		inicializarInfo();
		add(info);
	}
	
	private void inicializarInfo() {
		info = new JPanel();
		info.setBounds(5, 0, 675, 455);
		info.setLayout(null);
		
		modificar = new JButton("Modificar");
		modificar.setBounds(490, 80, 140, 20);
		atras = new JButton("Atrás");
		atras.setBounds(490, 110, 140, 20);
		info.add(modificar);
		info.add(atras);	
		
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//vistaNuevoPedido();
			}
		});
		
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.atras();
			}
		});
	}
	
	public interface ProductoSwingListener {
		public void atras();
	}
}
