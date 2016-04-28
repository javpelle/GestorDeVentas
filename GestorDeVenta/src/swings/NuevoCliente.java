package swings;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class NuevoCliente extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	
	public NuevoCliente(final NuevoClienteListener listener) {
		super ("Gestor de ventas: Nuevo cliente");
		setSize(new Dimension(400, 150));
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(106, 11, 184, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeWindow();
				listener.insertarNuevoCliente(textField.getText());
			}
		});
		btnAceptar.setBounds(190, 63, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(285, 63, 89, 23);
		getContentPane().add(btnCancelar);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeWindow();
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 14, 46, 14);
		getContentPane().add(lblNombre);
		setVisible(true);
	}
	
	public interface NuevoClienteListener {
		void insertarNuevoCliente(String nombre);
	}
	
	private void closeWindow(){
	    WindowEvent close = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(close);
	}
}
