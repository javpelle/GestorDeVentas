package gestorDeVenta;

import swings.Swing;

public class Main {
	
	
	public static void main(String[] args) {
		
		String archivo = "datos.txt";
		Gestor gestor = new Gestor(archivo);
		new Swing (archivo, gestor);
	}
}