package gestorDeVenta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestor {
	private List<Cliente> clientes;
	private String archivo;
	private float netoTotal;
	private float costes;
	private float brutoTotal;
	
	private int pos;
	
	public Gestor () {}
	
	/**
	 *  Inicializa el gestor e intenta arrancarlo cargando la información del archivo
	 *  que se le pasa como parámetro. Para ello inicializará y cargará la lista de
	 *  clientes y de pedidos de dicho archivo. Si no existe el archivo inicia un nuevo
	 *  fichero.
	 * @param archivo
	 */
	public Gestor (String archivo) {
		this.archivo = archivo;
		clientes = new ArrayList<Cliente>();
		File ficheroEntrada = new File(archivo);
		Scanner entrada = null;
		netoTotal = 0;
		costes = 0;
		brutoTotal = 0;
		try {
			cargar(ficheroEntrada, entrada);
		} catch (FileNotFoundException e) {
			System.out.print(e + " Archivo no encontrado...\n");
		}
	}
	
	private void cargar(File ficheroEntrada, Scanner entrada) throws FileNotFoundException {
		if (!ficheroEntrada.exists()) {
			throw new FileNotFoundException();
		} else {
			entrada = new Scanner(ficheroEntrada);
			int contador = Integer.parseInt(entrada.nextLine());
			
			for (int i = 0; i < contador; i++) {
				clientes.add(new Cliente());
				clientes.get(i).cargar(entrada);
			}
			
			for (int i = 0; i < clientes.size() ; i++) {
				brutoTotal += clientes.get(i).getBeneficioBruto();
				costes += clientes.get(i).getCoste();
			}
			netoTotal = brutoTotal - costes;
			entrada.close();
		}
	}
	
	public void guardar() {
		try {
			File ficheroSalida = new File (archivo);
			FileWriter salida = new FileWriter (ficheroSalida);
			salida.write(clientes.size() + "\r\n");
			for(int i = 0; i < clientes.size(); i++) {
				clientes.get(i).guardar(salida);
			}
			salida.close();
		} catch (IOException e) {
			System.out.print(e);
		}
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	/**
	 * Esta operación apaga el gestor y guarda para ello las listas de clientes
	 * del archivo. Los nombres de los archivos son los mismos que al arrancar.
	 */
	public void apagar() {
		
	}
	
	/**
	 * Lee los datos de usuario necesarios para crear un cliente y si no existe lo crea.
	 */
	public boolean crearCliente(String nombre) {
		if(!busquedaBinaria(nombre)) {
			clientes.add(pos, new Cliente(nombre));
			return true;
		}
		return false;
	}
	
	private boolean busquedaBinaria (String name) {
		int ini = 0, fin = clientes.size() - 1;
		int mitad = ini;
		boolean encontrado = false;
		while ((ini <= fin) && !encontrado) {
			mitad = (ini + fin) / 2;
			int comparacion = name.compareTo(clientes.get(mitad).toString());
			if (comparacion < 0) {
				fin = mitad - 1;
			} else if (comparacion > 0) {
				ini = mitad + 1;
			} else {
				encontrado = true;
			}
		}
		if (encontrado) {
			pos = mitad; // en la posición mitad
		} else {
			pos = ini; 
		}
		return encontrado;
	}
	
	/**
	 * @return Devuelve el beneficio bruto
	 */
	public float getBeneficioBruto() {return brutoTotal;}
	
	/**
	 * @return Devuelve el coste
	 */
	public float getCoste() {return costes;}
	
	/**
	 * @return Devuelve el beneficio neto
	 */
	public float getBeneficioNeto() {return netoTotal;}
}