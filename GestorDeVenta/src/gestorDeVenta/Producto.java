package gestorDeVenta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Producto {
	private String nombre;
	private String talla;
	private float precioBruto;
	private float ganancia;
	private float coste;
	private String codSeguimiento;
	
	/**
	 * Inicializa un nuevo producto vacío (por ejemplo para cargarlo de un archivo)
	 */
	public Producto() {}
	
	/**
	 * Inicializa un nuevo producto
	 * @param nombre
	 * @param talla
	 * @param precioBruto
	 * @param coste
	 * @param codSeguimiento
	 */
	public Producto(String nombre, String talla, float precioBruto, float coste, String codSeguimiento) {
		this.nombre = nombre;
		if (talla != null) {
			this.talla = talla;
		} else {
			this.talla = "-";
		}
		this.precioBruto = precioBruto;
		this.coste = coste;
		ganancia = this.precioBruto - this.coste;
		ganancia = (float) (Math.round(ganancia * 100.0)/100.0);
		if (codSeguimiento != null) {
			this.codSeguimiento = codSeguimiento;
		} else {
			this.codSeguimiento = "-";
		}
	}
	
	/**
	 * Carga los datos de un pedido desde un fichero
	 * @param entrada
	 */
	public void cargar(Scanner entrada) {
		nombre = entrada.nextLine();
		talla = entrada.nextLine();
		precioBruto = Float.parseFloat(entrada.nextLine());
		coste = Float.parseFloat(entrada.nextLine());
		codSeguimiento = entrada.nextLine();
		ganancia = precioBruto - coste;
		ganancia = (float) (Math.round(ganancia * 100.0)/100.0);
	}
	
	/**
	 * Guarda los datos del producto
	 * @param salida Objeto que escribe la salida
	 * @throws IOException
	 */
	public void guardar(FileWriter salida) throws IOException {
		salida.write(nombre + "\r\n" + talla + "\r\n" + precioBruto + "\r\n"
				+ coste + "\r\n" + codSeguimiento + "\r\n");
	}
	
	/**
	 * Si el usuario ha modificado la información de un producto, se pasa toda la información y se modifica
	 * @param nombre
	 * @param talla
	 * @param precioBruto
	 * @param coste
	 * @param codSeguimiento
	 */
	public void setModificaciones(String nombre, String talla, float precioBruto, float coste, String codSeguimiento) {
		this.nombre = nombre;
		if (talla != null) {
			this.talla = talla;
		} else {
			this.talla = "-";
		}
		this.precioBruto = precioBruto;
		this.coste = coste;
		ganancia = this.precioBruto - this.coste;
		ganancia = (float) (Math.round(ganancia * 100.0)/100.0);
		if (codSeguimiento != null) {
			this.codSeguimiento = codSeguimiento;
		} else {
			this.codSeguimiento = "-";
		}
	}
	
	/**
	 * @return Devuelve la talla del producto
	 */
	public String getTalla() { return talla;}
	
	/**
	 * @return Devuelve el beneficio bruto
	 */
	public float getPrecioBruto() { return precioBruto;}
	
	/**
	 * @return Devuelve el precio de coste
	 */
	public float getCoste() { return coste;}
	
	/**
	 * @return Devuelve el beneficio neto
	 */
	public float getGanancia() { return ganancia;}
	
	/**
	 * @return Devuelve el codigo de seguimiento
	 */
	public String getCodSeguimiento() { return codSeguimiento;}
	
	/**
	 * @return Devuelve el nombre del producto
	 */
	public String toString() {
		return nombre;
	}
}
