package gestorDeVenta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Pedido {
	private int numeroPedido;
	private String direccion;
	private float precio;
	private float coste;
	private float beneficio;
	private List<Producto> listaProductos;
	
	/**
	 * Inicializa un nuevo pedido vacío (por ejemplo para cargarlo de un archivo)
	 */
	public Pedido() {
		listaProductos = new ArrayList<Producto>();
	}
	
	/**
	 * Crea un nuevo pedido, toma la fecha en segundos desde 1970 como numero de pedido
	 * @param direccion
	 */
	public Pedido(String direccion) {
		numeroPedido = (int) (Calendar.getInstance().getTimeInMillis() / 1000);
		listaProductos = new ArrayList<Producto>();
		this.direccion = direccion;
		precio = 0;
		coste = 0;
		beneficio = 0;
	}
	
	/**
	 * Carga un pedido de un fichero
	 * @param entrada
	 */
	public void cargar (Scanner entrada) {
		numeroPedido = Integer.parseInt(entrada.nextLine());
		direccion = entrada.nextLine();
		int contador = Integer.parseInt(entrada.nextLine());
		for (int i = 0; i < contador; i++) {
			Producto nuevoProducto = new Producto();
			nuevoProducto.cargar(entrada);
			listaProductos.add(nuevoProducto);
		}
		precio = 0;
		coste = 0;
		for (int i = 0; i < listaProductos.size(); i++) {
			precio += listaProductos.get(i).getPrecioBruto();
			coste += listaProductos.get(i).getCoste();
		}
		beneficio = precio - coste;
	}
	
	/**
	 * Guarda los datos de un pedido
	 * @param salida
	 * @throws IOException
	 */
	public void guardar(FileWriter salida) throws IOException {
		salida.write(numeroPedido + "\r\n" + direccion + "\r\n" + listaProductos.size() + "\r\n");
		for (int i = 0; i < listaProductos.size(); i++) {
			listaProductos.get(i).guardar(salida);
		}
	}
	
	/**
	 * Actualiza los datos de beneficios y costes de un pedido,
	 * por ejemplo si se ha insertado un nuevo producto o modificado.
	 */
	public void actulizarPrecios() {
		precio = 0;
		coste = 0;
		for (int i = 0; i < listaProductos.size(); i++) {
			precio += listaProductos.get(i).getPrecioBruto();
			coste += listaProductos.get(i).getCoste();
		}
		beneficio = precio - coste;
	}

	/**
	 * @return Devuelve el numero de pedido
	 */
	public int getNumeroPedido() {return numeroPedido;}
	
	/**
	 * @return Devuelve los datos de envio
	 */
	public String getDireccion () {return direccion;}
	
	/**
	 * @return Devuelve el beneficio bruto
	 */
	public float getBeneficioBruto() {return precio;}
	
	/**
	 * @return Devuelve el coste
	 */
	public float getCoste() {return coste;}
	
	/**
	 * @return Devuelve el beneficio neto
	 */
	public float getBeneficioNeto() {return beneficio;}
	
	/**
	 * Cambia la direcon de un pedido
	 * @param direccion direccion nueva
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String toString() {
		return Integer.toString(numeroPedido);
	}
}


