package gestorDeVenta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
	private String nombre;
	private List<Pedido> listaPedidos;
	private float dineroGastado;
	private float coste;
	private float beneficioCliente;
	
	public Cliente() {
		listaPedidos = new ArrayList<Pedido>();
	}
	
	public Cliente(String nombre) {
		listaPedidos = new ArrayList<Pedido>();
		this.nombre = nombre;
		dineroGastado = 0;
		coste = 0;
		beneficioCliente = 0;
	}
	
	public void cargar(Scanner entrada) {
		nombre = entrada.nextLine();
		int contador = Integer.parseInt(entrada.nextLine());
		for (int i = 0; i < contador; i++) {
			Pedido nuevoPedido = new Pedido();
			nuevoPedido.cargar(entrada);
			listaPedidos.add(nuevoPedido);
		}
		dineroGastado = 0;
		coste = 0;
		for (int i = 0; i < listaPedidos.size(); i++) {
			dineroGastado += listaPedidos.get(i).getBeneficioBruto();
			coste += listaPedidos.get(i).getCoste();
		}
		beneficioCliente = dineroGastado - coste;
	}
	
	public void guardar(FileWriter salida) throws IOException {
		salida.write(nombre + "\r\n" + listaPedidos.size() + "\r\n");
		for (int i = 0; i < listaPedidos.size(); i++) {
			listaPedidos.get(i).guardar(salida);
		}
	}
	
	/**
	 * @return Devuelve el nombre del cliente
	 */
	public String toString () {return nombre;}
	
	/**
	 * @return Devuelve el beneficio bruto del cliente
	 */
	public float getBeneficioBruto() {return dineroGastado;}
	
	/**
	 * @return Devuelve el coste
	 */
	public float getCoste() {return coste;}
	
	/**
	 * @return Devuelve el beneficio neto
	 */
	public float getBeneficioNeto() {return beneficioCliente;}
	
	public List<Pedido> getListaPedidos() {return listaPedidos;}
}
