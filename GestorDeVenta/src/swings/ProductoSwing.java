package swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestorDeVenta.Producto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProductoSwing extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Producto producto;
	ProductoSwingListener listener;
	
	// Datos apartado producto
	private JPanel info; 
	private JButton modificar;
	private JButton atras;
	private JLabel lblNombre;
	private JLabel lblTalla;
	private JLabel lblBruto;
	private JLabel lblCostes;
	private JLabel lblneto;
	private JLabel lblCodSeguimiento;
		
	// Datos apartado modificar producto
	private JPanel modific;
	private JTextField nombre;
	private JTextField talla;
	private JTextField bruto;
	private JTextField costes;
	private JTextField codseguimiento;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public ProductoSwing (Producto producto, ProductoSwingListener listener) {
		this.producto = producto;
		this.listener = listener;
		setLayout(null);
		inicializarInfo();
		inicializarModificar();
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
				vistaModificar();
			}
		});
		
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.atras();
			}
		});
	}
	
	private void inicializarModificar() {
		modific = new JPanel();
		modific.setBounds(5, 0, 675, 455);
		modific.setLayout(null);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(300, 400, 140, 20);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(490, 400, 140, 20);
		modific.add(btnAceptar);
		modific.add(btnCancelar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 31, 100, 14);
		JLabel lblTalla = new JLabel("Talla");
		lblTalla.setBounds(31, 61, 100, 14);
		JLabel lblPrecio = new JLabel("Precio de venta");
		lblPrecio.setBounds(31, 91, 100, 14);
		JLabel lblCoste = new JLabel("Precio de coste");
		lblCoste.setBounds(31, 121, 100, 14);
		JLabel lblCSeguimiento = new JLabel("Código Postal");
		lblCSeguimiento.setBounds(31, 151, 100, 14);
		modific.add(lblNombre);
		modific.add(lblTalla);
		modific.add(lblPrecio);
		modific.add(lblCoste);
		modific.add(lblCSeguimiento);
		
		nombre = new JTextField(producto.toString());
		nombre.setBounds(150, 31, 270, 20);
		talla = new JTextField(producto.getTalla());
		talla.setBounds(150, 61, 270, 20);
		bruto = new JTextField(Float.toString(producto.getPrecioBruto()));
		bruto.setBounds(150, 91, 270, 20);
		costes = new JTextField(Float.toString(producto.getCoste()));
		costes.setBounds(150, 121, 270, 20);
		codseguimiento = new JTextField(producto.getCodSeguimiento());
		codseguimiento.setBounds(150, 151, 270, 20);
		modific.add(nombre);
		modific.add(talla);
		modific.add(bruto);
		modific.add(costes);
		modific.add(codseguimiento);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nombre.getText().equals("")) {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "El campo nombre está vacío.");
				} else {
					float precio, cost;
					try {
						precio = Float.parseFloat(bruto.getText());
						cost = Float.parseFloat(costes.getText());
						vistaInfo();
						listener.modificarProducto(nombre.getText(), talla.getText(), precio,
								cost, codseguimiento.getText(), producto);
					} catch (Exception e) {
						JFrame error = new JFrame();
						JOptionPane.showMessageDialog(error, "El precio y/o coste es incorrecto");
					}
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaInfo();
			}
		});
	}
	
	public interface ProductoSwingListener {
		public void atras();
		public void modificarProducto(String nombre, String talla, float precioBruto, 
				float coste, String codSeguimiento, Producto producto);
	}
	
	private void vistaModificar() {
		remove(info);
		add(modific);
		repaint();
	}
	
	private void vistaInfo() {
		remove(modific);
		add(info);
		repaint();
	}
	
	public void update () {
		if (lblNombre != null) {
			info.remove(lblNombre); info.remove(lblTalla); info.remove(lblBruto);
			info.remove(lblCostes); info.remove(lblneto); info.remove(lblCodSeguimiento);
		}
		lblNombre = new JLabel("Descripción del producto: " + producto.toString());
		lblNombre.setBounds(20, 20, 300, 14);
		info.add(lblNombre);
		lblTalla = new JLabel("Talla: " + producto.getTalla());
		lblTalla.setBounds(20, 50, 300, 14);
		info.add(lblTalla);
		lblBruto = new JLabel("Precio de venta: " + Float.toString(producto.getPrecioBruto()) + " €");
		lblBruto.setBounds(20, 80, 300, 14);
		info.add(lblBruto);
		lblCostes = new JLabel("Coste: " + Float.toString(producto.getCoste()) + " €");
		lblCostes.setBounds(20, 110, 300, 14);
		info.add(lblCostes);
		lblneto = new JLabel("Ganancia neta: " + Float.toString(producto.getGanancia()) + " €");
		lblneto.setBounds(20, 140, 300, 14);
		info.add(lblneto);
		lblCodSeguimiento = new JLabel("Código de seguimiento: " + producto.getCodSeguimiento());
		lblCodSeguimiento.setBounds(20, 170, 300, 14);
		info.add(lblCodSeguimiento);
		info.repaint();
	}
}
