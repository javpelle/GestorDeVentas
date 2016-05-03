package swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import gestorDeVenta.Pedido;
import gestorDeVenta.Producto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PedidoSwing extends JPanel {

	// elementos comunes
		private static final long serialVersionUID = 1L;
		private Pedido pedido;
		private List<Producto> productosPedido;
		private PedidoSwingListener listener;
		
		// Datos apartado Pedido
		private JPanel info; 
		private JButton seleccionarProducto;
		private JButton eliminarProducto;
		private JButton nuevoProducto;
		private JButton atras;
		private JList<Object> list;
		private JScrollPane scrollPaneProductos;
		private JLabel lblBruto;
		private JLabel lblCostes;
		private JLabel lblneto;	
			
		// Datos apartado nuevo Producto
		private JPanel nuevoProduct;
		private JTextField nombre;
		private JTextField talla;
		private JTextField precioBruto;
		private JTextField coste;
		private JTextField codSeguimiento;
		private JButton btnAceptar;
		private JButton btnCancelar;

	public PedidoSwing (Pedido pedido, PedidoSwingListener listener) {
		this.pedido = pedido;
		productosPedido = pedido.getListaProductos();
		this.listener = listener;
		setLayout(null);
		inicializarInfo();
		inicializarNuevoProducto();
		add(info);
	}
	
	private void inicializarInfo() {
		info = new JPanel();
		info.setBounds(5, 0, 675, 455);
		info.setLayout(null);
		info.setBorder(new TitledBorder(null, pedido.toString(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(8, 20, 460, 220);
		
		info.add(scrollPaneProductos);
		
		seleccionarProducto = new JButton("Seleccionar");
		seleccionarProducto.setBounds(490, 20, 140, 20);
		eliminarProducto = new JButton("Eliminar");
		eliminarProducto.setBounds(490, 50, 140, 20);
		nuevoProducto = new JButton("Nuevo Producto");
		nuevoProducto.setBounds(490, 80, 140, 20);
		atras = new JButton("Atrás");
		atras.setBounds(490, 110, 140, 20);
		info.add(nuevoProducto);
		info.add(seleccionarProducto);
		info.add(eliminarProducto);
		info.add(atras);
		
		String aux = "<html>" + "<u><b>Dirección de envío:</b></u><br><br>" +
				pedido.getDireccion().replaceAll(";", "<br>") + "</html>";
		JLabel direccion = new JLabel(aux);
		direccion.setBounds(30, 250, 300, 130);
		info.add(direccion);
		
		nuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaNuevoPedido();
			}
		});
		
		seleccionarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.seleccionar((Producto) list.getSelectedValue());
			}
		});
		
		eliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.eliminarProducto((Producto)list.getSelectedValue(), pedido);
			}
		});
		
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.atras();
			}
		});
	}
	
	private void inicializarNuevoProducto() {
		nuevoProduct = new JPanel();
		nuevoProduct.setLayout(null);
		nuevoProduct.setBounds(5, 0, 675, 455);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (nombre.getText().equals("")) {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "El campo nombre está vacío.");
				} else {
					float precio, cost;
					try {
						precio = Float.parseFloat(precioBruto.getText());
						cost = Float.parseFloat(coste.getText());
						vistaInfo();
						listener.aceptarNuevoProducto(nombre.getText(), talla.getText(), precio,
								cost, codSeguimiento.getText(), pedido);
					} catch (Exception e) {
						JFrame error = new JFrame();
						JOptionPane.showMessageDialog(error, "El precio y/o coste es incorrecto");
					}
				}
			}
		});
		btnAceptar.setBounds(190, 200, 89, 23);
		nuevoProduct.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(285, 200, 89, 23);
		nuevoProduct.add(btnCancelar);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vistaInfo();
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre del producto");
		lblNombre.setBounds(31, 11, 150, 14);
		JLabel lblTalla = new JLabel("Talla");
		lblTalla.setBounds(31, 41, 150, 14);
		JLabel lblPrecioVenta = new JLabel("Precio de venta");
		lblPrecioVenta.setBounds(31, 71, 150, 14);
		JLabel lblCoste = new JLabel("Coste");
		lblCoste.setBounds(31, 101, 150, 14);
		JLabel lblCSeguimiento = new JLabel("Código de seguimiento");
		lblCSeguimiento.setBounds(31, 131, 150, 14);
		nuevoProduct.add(lblNombre);
		nuevoProduct.add(lblTalla);
		nuevoProduct.add(lblPrecioVenta);
		nuevoProduct.add(lblCoste);
		nuevoProduct.add(lblCSeguimiento);
		
		nombre = new JTextField();
		nombre.setBounds(200, 11, 270, 20);
		talla = new JTextField();
		talla.setBounds(200, 41, 270, 20);
		precioBruto = new JTextField();
		precioBruto.setBounds(200, 71, 270, 20);
		coste = new JTextField();
		coste.setBounds(200, 101, 270, 20);
		codSeguimiento = new JTextField();
		codSeguimiento.setBounds(200, 131, 270, 20);
		nuevoProduct.add(nombre);
		nuevoProduct.add(talla);
		nuevoProduct.add(precioBruto);
		nuevoProduct.add(coste);
		nuevoProduct.add(codSeguimiento);
	}
	
	public interface PedidoSwingListener {
		
		public void seleccionar(Producto producto);
		
		public void eliminarProducto(Producto producto, Pedido pedido);
		
		public void aceptarNuevoProducto(String nombre, String talla, float precioBruto, 
				float coste, String codSeguimiento, Pedido pedido);
		
		public void atras();
	}

	public void update() {
		if (list != null) {
			scrollPaneProductos.remove(list);
			info.remove(lblBruto); info.remove(lblCostes); info.remove(lblneto);
		}
		list = new JList<Object>(productosPedido.toArray());
		list.setSelectedIndex(0);
		scrollPaneProductos.setViewportView(list);
		
		lblBruto = new JLabel("Beneficios brutos = " + Float.toString(pedido.getBeneficioBruto()) + " €");
		lblBruto.setBounds(480, 150, 180, 14);
		info.add(lblBruto);
		lblCostes = new JLabel("Costes = " + Float.toString(pedido.getCoste()) + " €");
		lblCostes.setBounds(480, 190, 180, 14);
		info.add(lblCostes);
		lblneto = new JLabel("Beneficios brutos = " + Float.toString(pedido.getBeneficioNeto()) + " €");
		lblneto.setBounds(480, 230, 180, 14);
		info.add(lblneto);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	listener.seleccionar((Producto) list.getSelectedValue());
		        }
		    }
		});
		info.repaint();
	}
	
	private void vistaInfo() {
		remove(nuevoProduct);
		add(info);
		repaint();
	}
	
	private void vistaNuevoPedido() {
		remove(info);
		add(nuevoProduct);
		repaint();
	}
	
	public Pedido getPedido() {return pedido;}
}
