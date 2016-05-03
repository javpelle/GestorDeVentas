package swings;

import gestorDeVenta.Cliente;
import gestorDeVenta.Gestor;
import gestorDeVenta.Pedido;
import gestorDeVenta.Producto;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import swings.GestorSwing.GestorSwingListener;
import swings.ClienteSwing.ClienteSwingListener;
import swings.PedidoSwing.PedidoSwingListener;
import swings.ProductoSwing.ProductoSwingListener;

public class Swing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GestorSwing gestorSwing;
	private ClienteSwing clienteSwing;
	private PedidoSwing pedidoSwing;
	private ProductoSwing productoSwing;
	private Gestor gestor;
	
	public Swing (String archivo, final Gestor gestor) {
		super ("Gestor de ventas: " + archivo);
		this. gestor = gestor;
		setSize(new Dimension(700, 500));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		gestorSwing = new GestorSwing(gestor, new GestorSwingListener() {

			@Override
			public void seleccionar(Cliente cliente) {
				if (cliente != null) {
					remove(gestorSwing);
					vistaCliente(cliente);
					clienteSwing.setBounds(0, 0, 684, 461);
					add(clienteSwing);
					clienteSwing.update();
					repaint();
				}
			}

			@Override
			public void aceptarNuevoCliente(String cliente) {
				if (gestor.crearCliente(cliente)) {
					gestorSwing.update();
					gestor.guardar();
				} else {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "El cliente ya existe.");
				}
				
			}

			@Override
			public void eliminarCliente(Cliente cliente) {
				if (gestor.eliminarCliente(cliente)) {
					gestorSwing.update();
					gestor.guardar();
				}
			}
		});
		gestorSwing.setBounds(0, 0, 684, 461);
		add(gestorSwing);
		gestorSwing.update();
		setVisible(true);
	}
	
	private void vistaCliente(Cliente cliente) {
		clienteSwing = new ClienteSwing(cliente, new ClienteSwingListener(){

			public void atras() {
				remove(clienteSwing);
				gestor.actulizarPrecios();
				add(gestorSwing);
				gestorSwing.update();
				repaint();
			}

			@Override
			public void crearPedido(String direccion, Cliente cliente) {
				cliente.crearPedido(direccion);
				clienteSwing.update();
				gestor.guardar();
			}

			@Override
			public void eliminarPedido(Pedido pedido, Cliente cliente) {
				if(cliente.eliminarPedido(pedido)) {
					clienteSwing.update();
					gestor.guardar();
				}
			}

			@Override
			public void seleccionarPedido(Pedido pedido) {
				if (pedido != null) {
					remove(clienteSwing);
					vistaPedido(pedido);
					pedidoSwing.setBounds(0, 0, 684, 461);
					add(pedidoSwing);
					pedidoSwing.update();
					repaint();
				
				}
			}
			
		});
	}
	
	private void vistaPedido(Pedido pedido) {
		pedidoSwing = new PedidoSwing(pedido, new PedidoSwingListener(){

			@Override
			public void seleccionar(Producto producto) {
				if (producto != null) {
					remove(pedidoSwing);
					vistaProducto(producto);
					productoSwing.setBounds(0, 0, 684, 461);
					add(productoSwing);
					//productoSwing.update();
					repaint();
				}
			}

			@Override
			public void eliminarProducto(Producto producto, Pedido pedido) {
				if(pedido.eliminarProducto(producto)) {
					pedido.actulizarPrecios();
					pedidoSwing.update();
					gestor.guardar();
				}
				
			}

			@Override
			public void aceptarNuevoProducto(String nombre, String talla,
					float precioBruto, float coste,	String codSeguimiento, Pedido pedido) {
				
					pedido.nuevoProducto(new Producto(nombre, talla, precioBruto, coste,
					codSeguimiento));
					pedido.actulizarPrecios();
					pedidoSwing.update();
					gestor.guardar();
			}

			@Override
			public void atras() {
				remove(pedidoSwing);
				clienteSwing.getCliente().actulizarPrecios();
				add(clienteSwing);
				clienteSwing.update();
				repaint();
			}
			
		});
	}
	
	private void vistaProducto(Producto producto) {
		productoSwing = new ProductoSwing(producto, new ProductoSwingListener() {

			@Override
			public void atras() {
				remove(productoSwing);
				pedidoSwing.getPedido().actulizarPrecios();
				add(pedidoSwing);
				pedidoSwing.update();
				repaint();
			}
			
		});
	}
}
